package kr.dev.icmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.dev.icmovie.databinding.ItemCarsBinding;
import kr.dev.icmovie.databinding.ItemMusicBinding;
import kr.dev.icmovie.models.AvtoData;
import kr.dev.icmovie.models.MusicData;

public class AvtoAdapter  extends RecyclerView.Adapter<AvtoAdapter.Vh> {
    List<AvtoData> avtoDataList;
    OnclickItemPo onclickItemPopular;


    public AvtoAdapter(List<AvtoData> avtoDataList, OnclickItemPo onclickItemPopular) {
        this.avtoDataList = avtoDataList;
        this.onclickItemPopular = onclickItemPopular;
    }

    public AvtoAdapter(List<AvtoData> avtoDataList) {
        this.avtoDataList = avtoDataList;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AvtoAdapter.Vh(ItemCarsBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {


        AvtoData avtoData = avtoDataList.get(position);
        holder.binding.ivImageCar.setImageResource(avtoData.getMusicImage());
        holder.binding.tvCarsName.setText(avtoData.getMusicName());
        holder.binding.ivImageAccount.setImageResource(avtoData.getAccountImage());
        holder.binding.tvAccountName.setText(avtoData.getAccountName());
        holder.binding.tvViewNum.setText(avtoData.getViewNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickItemPopular.clickItem(position);

            }
        });
//
    }

    @Override
    public int getItemCount() {
        return avtoDataList.size();
    }



    class Vh extends RecyclerView.ViewHolder{

        ItemCarsBinding binding;
        public Vh(@NonNull ItemCarsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
