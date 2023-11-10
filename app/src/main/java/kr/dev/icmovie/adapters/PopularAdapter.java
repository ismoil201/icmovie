package kr.dev.icmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.dev.icmovie.databinding.ItemPopularBinding;
import kr.dev.icmovie.models.PopularData;

public class PopularAdapter  extends RecyclerView.Adapter<PopularAdapter.Vh> {

    List<PopularData> popularDataList;
    OnclickItemPo onclickItemPopular;


    public PopularAdapter(List<PopularData> popularDataList, OnclickItemPo onclickItemPopular) {
        this.popularDataList = popularDataList;
        this.onclickItemPopular = onclickItemPopular;
    }

    public PopularAdapter(List<PopularData> popularDataList) {
        this.popularDataList = popularDataList;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(ItemPopularBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        PopularData popularData = popularDataList.get(position);

        holder.binding.ivImage.setImageResource(popularData.getFilmImage());
        holder.binding.tvFilmName.setText(popularData.getName());
        holder.binding.tvGenre.setText(popularData.getGenre());
        holder.binding.tvRating.setText(popularData.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickItemPopular.clickItem(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return popularDataList.size();
    }

    class  Vh extends RecyclerView.ViewHolder{

        ItemPopularBinding binding;
        public Vh(@NonNull ItemPopularBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

}
