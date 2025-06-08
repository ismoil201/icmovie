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
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.adapters.PopularAdapter;
import kr.dev.icmovie.databinding.FragmentPopularBinding;
import kr.dev.icmovie.models.PopularData;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MovieDao;
import kr.dev.icmovie.room.entity.Movie;

public class PopularFragment extends Fragment implements OnclickItemPo {


    private FragmentPopularBinding binding;
    private PopularAdapter adapter;

    private       List<Movie> movieList;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPopularBinding.inflate(getLayoutInflater(), container, false);


        // Ma'lumotlarni yuklash

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Test", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        movieList = new ArrayList<>();
        adapter = new PopularAdapter(movieList, this);
        binding.rvPopular.setAdapter(adapter);
        loadMovies();

    }

    private void loadMovies() {
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MovieDao movieDao = db.movieDao();

        new Thread(() -> {
            List<Movie> list = movieDao.getAllMovies();
            requireActivity().runOnUiThread(() -> {
                movieList.clear();
                movieList.addAll(list);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
    @Override
    public void clickItem(int position) {

        Movie clickedMovie = movieList.get(position);
        String json = new Gson().toJson(clickedMovie);
        editor.putString("data", json);
        editor.apply();

        try {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.datailFragment);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Navigation error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("NAV_ERROR", "Error navigating: ", e);
        }
    }

}
