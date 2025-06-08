package kr.dev.icmovie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.MusicAdapter;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.databinding.FragmentDetail2Binding;
import kr.dev.icmovie.databinding.FragmentMusicDetail2Binding;
import kr.dev.icmovie.databinding.FragmentMusicDetatilBinding;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MusicDao;
import kr.dev.icmovie.room.entity.Music;

public class MusicDetail2Fragment extends Fragment  implements OnclickItemPo {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private FragmentMusicDetail2Binding binding;
    private  MusicAdapter adapter;

    private boolean isFullScreen = false;
    private ViewGroup.LayoutParams originalParams;

    private FrameLayout.LayoutParams fullscreenParams;


    private List<Music> musicList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);

        binding = FragmentMusicDetail2Binding.inflate(inflater, container,false);
        musicList = new ArrayList<>();
        loadMusics();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Music", Context.MODE_PRIVATE);
        editor =  sharedPreferences.edit();


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

        setItemDetail();
        adapter = new MusicAdapter(musicList, this);


        binding.rvDatail.setAdapter(adapter);

    }

    private void setupRawVideo(Music music) {

        Uri videoUri = Uri.parse("android.resource://"
                + getActivity().getPackageName() + "/" +music.getMusicVideo());
        MediaController mediaController = new MediaController(getActivity());

        binding.vvMusic.setVideoURI(videoUri);
        binding.vvMusic.setMediaController(mediaController);
        mediaController.setAnchorView(binding.vvMusic);
        binding.vvMusic.start();


        binding.btnFullscreen.setOnClickListener(v -> toggleFullscreen());
    }

    private void toggleFullscreen() {
        if (isFullScreen) {
            // Return to portrait
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            binding.videoContainer.setLayoutParams(originalParams);
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        } else {
            // Go to landscape
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            if (originalParams == null) {
                originalParams = binding.videoContainer.getLayoutParams();
            }

            fullscreenParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            fullscreenParams.gravity = Gravity.CENTER;
            binding.videoContainer.setLayoutParams(fullscreenParams);

            requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }

        isFullScreen = !isFullScreen;
    } //full screen

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true /* Enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            if (isFullScreen) {
                // Fullscreen rejimdan chiqish uchun ekran orientatsiyasini portretga o'zgartiramiz
                requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                // Video konteynerni asl holatga qaytamiz
                if (originalParams != null) {
                    binding.videoContainer.setLayoutParams(originalParams);
                }

                // Tizim UI ni ko'rsatamiz
                requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                isFullScreen = false; // holatni yangilaymiz
            } else {
                // Agar fullscreen bo'lmasa, oddiy navigatsiya amalga oshadi
                Navigation.findNavController(binding.getRoot()).navigate(R.id.homeFragment);
            }
        }

    };


    private  void setItemDetail(){
        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("Music", Context.MODE_PRIVATE);
//
        Type token = new TypeToken<Music>(){}.getType();
        Music currentMusic = gson.fromJson(sharedPreferences.getString("musicdata", "{}"),token);

//        binding.vvMusic.setImageResource(musicData.getMusicImage());
        binding.tvMusicName1.setText(currentMusic.getMusicName());
        binding.tvRatingDetail.setText(currentMusic.getViewNum());
        binding.tvGenreDetail.setText(currentMusic.getAccountName());
        setupRawVideo(currentMusic);

        // 🔽 isSaved tekshiruv — iconni o‘rnatish
        if (currentMusic.isSaved()) {
            binding.btnSave.setImageResource(R.drawable.saved_svg); // saqlangan holat
        } else {
            binding.btnSave.setImageResource(R.drawable.saved_svg1); // saqlanmagan holat
        }


        binding.btnSave.setOnClickListener(view -> {
            AppDataBase db = AppDataBase.getInstance(requireContext());
            MusicDao musicDao = db.musicDao();

            new Thread(() -> {
                // isSaved holatini teskari qilamiz
                boolean newState = !currentMusic.isSaved();
                currentMusic.setSaved(newState);

                // Yangilangan musicni bazaga saqlash
                musicDao.updateMusic(currentMusic);

                // UI yangilash
                requireActivity().runOnUiThread(() -> {
                    if (newState) {
                        binding.btnSave.setImageResource(R.drawable.saved_svg);
                        Toast.makeText(requireContext(), "Saved ✅" + currentMusic.getMusicName(), Toast.LENGTH_SHORT).show();
                    } else {
                        binding.btnSave.setImageResource(R.drawable.saved_svg1);
                        Toast.makeText(requireContext(), "Removed ❎" + currentMusic.getMusicName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }

    private  void  loadMusics(){
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MusicDao musicDao = db.musicDao();

        new Thread(() -> {


            musicList = musicDao.getAllMusics();

            Collections.shuffle(musicList);

            requireActivity().runOnUiThread(() -> {
                adapter = new MusicAdapter(musicList, this);
                binding.rvDatail.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            });
        }).start();
    }

    @Override
    public void clickItem(int position) {

        editor.remove("musicdata");
        Music  clickedMusic = musicList.get(position);
        String json = new Gson().toJson(clickedMusic);
        editor.putString("musicdata",json);
        editor.apply();

        editor.commit();

        Navigation.findNavController(binding.getRoot()).navigate(R.id.musicDetatilFragment);
    }




}