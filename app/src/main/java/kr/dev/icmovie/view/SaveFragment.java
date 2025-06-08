package kr.dev.icmovie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.MusicAdapter;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.databinding.FragmentMusicBinding;
import kr.dev.icmovie.databinding.FragmentSaveBinding;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MovieDao;
import kr.dev.icmovie.room.dao.MusicDao;
import kr.dev.icmovie.room.entity.Movie;
import kr.dev.icmovie.room.entity.Music;

public class SaveFragment extends Fragment implements OnclickItemPo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Music music;



    private List<Music> musicList;
    private List<Movie> movieList;

    private FragmentSaveBinding binding;
    private MusicAdapter adapter;

    public SaveFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaveBinding.inflate(getLayoutInflater(),container, false);
        return  binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Music", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        musicList = new ArrayList<>();
        adapter = new MusicAdapter(musicList,this);

        binding.rvSave.setAdapter(adapter);

        loadMusic();


    }

    private  void  loadMusic(){
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MusicDao musicDao = db.musicDao();
        MovieDao movieDao = db.movieDao();

        new Thread(() -> {

            List<Music> list = musicDao.getAllSavedMusic();
            requireActivity().runOnUiThread(() -> {
                musicList.clear();
                musicList.addAll(list);
                adapter.notifyDataSetChanged();

            });
        }).start();
    }

    @Override
    public void clickItem(int position) {

        Music clickedMusic = musicList.get(position);
        String json = new Gson().toJson(clickedMusic);

        editor.putString("musicdata",json);
        editor.apply();

        try {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.musicDetatilFragment);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Navigation error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("NAV_ERROR", "Error navigating: ", e);
        }
    }
}