package kr.dev.icmovie.view;

import android.app.AlertDialog;
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

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.adapters.PopularAdapter;
import kr.dev.icmovie.databinding.FragmentDatailBinding;
import kr.dev.icmovie.databinding.FragmentDetail2Binding;
import kr.dev.icmovie.models.PopularData;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MovieDao;
import kr.dev.icmovie.room.entity.Movie;

public class DetailFragment2 extends Fragment implements OnclickItemPo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private FragmentDetail2Binding binding;
    private PopularAdapter adapter;
    private boolean isFullScreen = false;
    private ViewGroup.LayoutParams originalParams;
    private FrameLayout.LayoutParams fullscreenParams;
    private List<Movie> movieList;
    private List<PopularData> popularDataList;


    public DetailFragment2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        binding = FragmentDetail2Binding.inflate(inflater,container, false);

        movieList = new ArrayList<>();
        loadMovies();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Test", Context.MODE_PRIVATE);

        editor =  sharedPreferences.edit();

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);


        setDetailItem();
        adapter = new PopularAdapter(movieList,this);


        binding.rvDatail.setAdapter(adapter);

//

    }


    private void setupRawVideo(Movie movie) {
        binding.btnPlay.setOnClickListener(view -> {
            Uri videoUri = Uri.parse("android.resource://"
                    + getActivity().getPackageName() + "/" +movie.getMovie());
            MediaController mediaController = new MediaController(getActivity());

            binding.ivImageDetail.setVideoURI(videoUri);
            binding.ivImageDetail.setMediaController(mediaController);
            mediaController.setAnchorView(binding.ivImageDetail);
            binding.ivImageDetail.start();
        });

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
            // Handle the back button event

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
            }        }


    };
    public void setDetailItem(){
        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("Test", Context.MODE_PRIVATE);
//
        Type token = new TypeToken<Movie>(){}.getType();
        Movie currectMovie = gson.fromJson(sharedPreferences.getString("data", "{}"),token);

        binding.tvFilmName1.setText(currectMovie.getName());
        binding.tvGenreDetail.setText(currectMovie.getGenre());
        binding.tvInfoTitle.setText(currectMovie.getDescription());
        binding.tvRatingDetail.setText(currectMovie.getRating());
        setupRawVideo(currectMovie);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void loadMovies() {
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MovieDao movieDao = db.movieDao();

        new Thread(() -> {
            movieList = movieDao.getAllMovies();

            Collections.shuffle(movieList);


            requireActivity().runOnUiThread(() -> {
                adapter = new PopularAdapter(movieList, this);
                binding.rvDatail.setAdapter(adapter);
                adapter.notifyDataSetChanged(); // Yangilanishni bildirish
            });
        }).start();
    }


    @Override
    public void clickItem(int position) {
        editor.remove("data");
        Movie clickedMovie = movieList.get(position);
        String json = new Gson().toJson(clickedMovie);
        editor.putString("data", json);
        editor.apply();


        editor.commit();

        Navigation.findNavController(binding.getRoot()).navigate(R.id.datailFragment);
    }
}