package kr.dev.icmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.dev.icmovie.databinding.ItemMusicBinding;
import kr.dev.icmovie.models.AvtoData;
import kr.dev.icmovie.models.MusicData;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Vh> {

    List<MusicData> musicDataList;
    OnclickItemPo onclickItemPopular;

    public MusicAdapter(List<MusicData> musicDataList, OnclickItemPo onclickItemPopular) {
        this.musicDataList = musicDataList;
        this.onclickItemPopular = onclickItemPopular;
    }

    public MusicAdapter(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(ItemMusicBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        MusicData musicData = musicDataList.get(position);
        holder.binding.ivImageMusic.setImageResource(musicData.getMusicImage());
        holder.binding.tvMusicName.setText(musicData.getMusicName());
        holder.binding.ivImageAccount.setImageResource(musicData.getAccountImage());
        holder.binding.tvAccountName.setText(musicData.getAccountName());
        holder.binding.tvViewNum.setText(musicData.getViewNum());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickItemPopular.clickItem(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return musicDataList.size();
    }

    class Vh extends RecyclerView.ViewHolder{

        ItemMusicBinding binding;
        public Vh(@NonNull ItemMusicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
