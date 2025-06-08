package kr.dev.icmovie.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.dev.icmovie.databinding.ItemMusicBinding;
import kr.dev.icmovie.room.entity.Music;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Vh> {

    List<Music> musicList;
    OnclickItemPo onclickItemPopular;

    public MusicAdapter(List<Music> musicList, OnclickItemPo onclickItemPopular) {
        this.musicList = musicList;
        this.onclickItemPopular = onclickItemPopular;
    }

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(ItemMusicBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        Music music = musicList.get(position);
        Bitmap smallBitmap = decodeSampledBitmapFromResource(
                holder.itemView.getResources(),
                music.getMusicImage(),
                100, 100
        );
        holder.binding.ivImageMusic.setImageBitmap(smallBitmap);
//        holder.binding.ivImageMusic.setImageResource(music.getMusicImage());
        holder.binding.tvMusicName.setText(music.getMusicName());
        holder.binding.ivImageAccount.setImageResource(music.getAccountImage());
        holder.binding.tvAccountName.setText(music.getAccountName());
        holder.binding.tvViewNum.setText(music.getViewNum());

        holder.itemView.setOnClickListener(view -> {
            if (onclickItemPopular != null) {
                onclickItemPopular.clickItem(holder.getAdapterPosition());
            }

        });

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class Vh extends RecyclerView.ViewHolder{

        ItemMusicBinding binding;
        public Vh(@NonNull ItemMusicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
