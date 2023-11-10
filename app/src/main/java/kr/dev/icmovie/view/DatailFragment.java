package kr.dev.icmovie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.HomeAdapter;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.adapters.PopularAdapter;
import kr.dev.icmovie.databinding.FragmentDatailBinding;
import kr.dev.icmovie.models.PopularData;

public class DatailFragment extends Fragment implements OnclickItemPo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PopularData popularData ;
    private FragmentDatailBinding binding;
    private PopularAdapter popularAdapter;

    private List<PopularData> popularDataList;
    public DatailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        binding = FragmentDatailBinding.inflate(inflater,container, false);

        setupRawVideo();

        return binding.getRoot();

    }
    private void setupRawVideo() {
        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.drakon;
//                Uri video = Uri.parse("https://www.youtube.com/watch?v=E7RUXX_I6q0");
                Uri uri = Uri.parse(videoPath);
                MediaController mediaController = new MediaController(getActivity());
                binding.ivImageDetail.setMediaController(mediaController);
                mediaController.setAnchorView(binding.ivImageDetail);
                binding.ivImageDetail.requestFocus();
                binding.ivImageDetail.setVideoURI(uri);
                binding.ivImageDetail.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        mediaPlayer.start();
                    }
                });
            }
        });

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Test", Context.MODE_PRIVATE);
        editor =  sharedPreferences.edit();


        loadList();

        setDetailItem();
        popularAdapter = new PopularAdapter(popularDataList,this);

        binding.rvDatail.setAdapter(popularAdapter);

    }


    public void setDetailItem(){
        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("Test", Context.MODE_PRIVATE);
//
        Type token = new TypeToken<PopularData>(){}.getType();
        popularData= gson.fromJson(sharedPreferences.getString("data", "{}"),token);

        binding.tvFilmName1.setText(popularData.getName());
        binding.tvGenreDetail.setText(popularData.getGenre());
        binding.tvInfoTitle.setText(popularData.getAbout());
        binding.tvRatingDetail.setText(popularData.getRating());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true /* Enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.homeFragment);
        }


    };
    private    void loadList(){

        popularDataList =  new ArrayList<>();

        for (int i = 0; i < 2; i++) {


            popularDataList.add(new PopularData(R.drawable.img,"Tungi Poyezd Premyera","6.7",
                    "Jangari","Ragnarning Vikinglar otryadining hikoyasi. U Viking qabilalarining " +
                    "qiroli bo'lish uchun ko'tarildi. Norvegiya afsonasiga ko'ra, u urush va " +
                    "jangchilar xudosi Odinning bevosita avlodi bo'lgan."));

            popularDataList.add(new PopularData(R.drawable.rohiba,"Rohibaning la'nati Ujas kino",
                    "7.1","Qo'rqinchli",
                    "Ruminiyada tanho monastirda yosh nun o'z joniga qasd qilganda, " +
                            "Vatikan ruhoniyning hodisasini tumanli o'tmish bilan va qaytib kelmaydigan" +
                            " va'dalar ostonasida yangi boshlovchi bilan tekshiradi. Faqat hayotlarini emas, " +
                            "balki ruhlarini xavf ostiga qo'yib, ular shaytoniy rohibaning ko'rinishini qabul " +
                            "qilRuminiyada tanho monastirda yosh nun o'z joniga qasd qilganda, Vatikan " +
                            "ruhoniyning hodisasini tumanli o'tmish bilan va qaytib kelmaydigan va'dalar " +
                            "ostonasida yangi boshlovchi bilan tekshiradi. Faqat hayotlarini emas, balki " +
                            "ruhlarini xavf ostiga qo'yib, ular shaytoniy rohibaning ko'rinishini qabul" +
                            " qilgan yovuz kuchga duch kelishadi va monastir tirik va la'natlangan jang maydoniga " +
                            "aylanadi.gan yovuz kuchga duch kelishadi va monastir tirik va la'natlangan " +
                            "jang maydoniga aylanadi."));
            popularDataList.add(new PopularData(R.drawable.blue,"Moviy qo'ng'iz","7.3",
                    "Fantastika","Meksikalik o'smir Xayme Reyes" +
                    " unga super kuchlar beradigan begona kostyumni oladi."));

            popularDataList.add(new PopularData(R.drawable.super_heroes,
                    "Super-qahramonlar ligasi Multfilm ","6.0","Multfilm",
                    "Super-qahramonlar ligasi Multfilm Uzbek tilida 2022 O'zbekcha tarjima HD"));
            popularDataList.add(new PopularData(R.drawable.super_hayvon,
                    "Super uy hayvonlari ligasi DC","6.7","Multfilm",
                    "Kripto iti Supermenning eng yaxshi do'sti bo'lib, uning xo'jayini kabi " +
                            "yerdan tashqari kuchlarga ega. Ular birgalikda Metropolisda jinoyatchilikka " +
                            "qarshi jasorat bilan kurashadilar. Ammo Supermen va Adolat ligasining" +
                            " boshqa a'zolari noma'lum yovuz odamlar tomonidan o'g'irlab ketilganda, " +
                            "Kripto yangi yordamchilarni, ya'ni to'satdan super kuchga ega bo'lgan " +
                            "boshpanadagi turli xil hayvonlarni o'rgatishi kerak. Super uy hayvonlarining" +
                            " mo'ynali ligasi Supermenni va butun dunyoni qutqara oladimi?"));

            popularDataList.add(new PopularData(R.drawable.viking,"Vikinglar: Valhalla",
                    "7.2","Tarix | Jangari","Vikinglarning Angliyani zabt " +
                    "etishga urinishi, Leif Erikssonning Amerikaga sayohati va vikinglar orasida " +
                    "nasroniylikning tarqalishi fonida sevgi, adovat va qasos haqidagi " +
                    "dramatik hikoya."));
            popularDataList.add(new PopularData(R.drawable.img_2,"Аватар 2: Путь воды 2022 ",
                    "7.5","Fantastika | Fentezi","Askarning avatar qiyofasini " +
                    "olganidan so'ng, Jeyk Sulli Navi xalqining etakchisiga aylanadi va yangi " +
                    "do'stlarni Yerdan kelgan yollanma tadbirkorlardan himoya qilish vazifasini " +
                    "o'z zimmasiga oladi. Endi uning uchun kurashadigan odam bor - Jeyk, uning " +
                    "go'zal sevgilisi Neytiri bilan. Og'ir qurollangan yerliklar Pandoraga qaytganda," +
                    " Jeyk javob berishga tayyor."));

            popularDataList.add(new PopularData(R.drawable.john,"John Wick: Chapter 4",
                    "6.4","Action","ohn Wick uncovers a path to defeating The" +
                    " High Table. But before he can earn his freedom, Wick must face off against" +
                    " a new enemy with powerful alliances across the globe and forces that turn old " +
                    "friends into foes."));
            popularDataList.add(new PopularData(R.drawable.wakanda,"Black Panther: Wakanda Forever",
                    "6.7","Fantastika | Jangari","После смерти короля Т`Чаллы" +
                    " королева Рамонда, Шури, М`Баку, Окойе и Дора Милаж сражаются, чтобы " +
                    "защитить Ваканду от мировых держав."));

            popularDataList.add(new PopularData(R.drawable.drama,
                    "Five Feet Apart watch online in Tas-ix","6.1","Drama",
                    "The space in which they exist, cruel dictates the condition — the " +
                            "lovers must be no closer than a meter from each other they can't " +
                            "even touch. But true love knows no boundaries, and the stronger the " +
                            "feelings, the greater the temptation to break the rules…"));
            popularDataList.add(new PopularData(R.drawable.baymaks,"BayMax","7.3",
                    "Multfilm","Mehribon va sezgir tibbiy robot Baymax va uning " +
                    "do'stlarining sarguzashtlari haqida."));

        }


    }

    @Override
    public void clickItem(int position) {
        editor.remove("data");
        Gson gson = new Gson();
        popularData = popularDataList.get(position);
        editor.putString("data",gson.toJson(popularData));

        editor.commit();

        Log.i("TAG", popularData.getName());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.detailFragment2);
    }
}