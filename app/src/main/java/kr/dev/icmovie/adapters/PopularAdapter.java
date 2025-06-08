package kr.dev.icmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kr.dev.icmovie.databinding.ItemPopularBinding;
import kr.dev.icmovie.models.PopularData;
import kr.dev.icmovie.room.entity.Movie;

public class PopularAdapter   extends  RecyclerView.Adapter<PopularAdapter.Vh> {

    List<Movie> popularDataList;
    OnclickItemPo onclickItemPopular;

    public PopularAdapter(List<Movie> popularDataList, OnclickItemPo onclickItemPopular) {
        this.popularDataList = popularDataList;
        this.onclickItemPopular = onclickItemPopular;
    }

    public PopularAdapter(List<Movie> popularDataList) {
        this.popularDataList = popularDataList;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new Vh(ItemPopularBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        Movie movie = popularDataList.get(position);
//        holder.binding.ivImage.setImageResource(movie.());
////        Picasso.get().load(movie.getImageUrl()).into(holder.binding.ivImage);
//        holder.binding.tvGenre.setText(movie.getGenre());
//        holder.binding.tvFilmName.setText(movie.getAbout());
//        holder.binding.tvRating.setText(String.valueOf(movie.getRating()));
//        holder.itemView.setOnClickListener(v -> {
//            onclickItemPopular.clickItem(position);
//        });

        holder.binding.ivImage.setImageResource(movie.getFilmImage());
        holder.binding.tvGenre.setText(movie.getGenre());
        holder.binding.tvFilmName.setText(movie.getName());
        holder.binding.tvRating.setText(movie.getRating());

        holder.itemView.setOnClickListener(v -> {
            if (onclickItemPopular != null) {
                onclickItemPopular.clickItem(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularDataList.size();
    }


    class Vh extends RecyclerView.ViewHolder{

        ItemPopularBinding binding;
        public Vh(@NonNull ItemPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding; // ✅ TO‘G‘RI TAYINLASH
        }

    }


}
