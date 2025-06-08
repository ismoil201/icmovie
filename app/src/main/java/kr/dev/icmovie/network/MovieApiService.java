package kr.dev.icmovie.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {
    @GET("movies")
    Call<List<Movie>> getAllMovies();
}
