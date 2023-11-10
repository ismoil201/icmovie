package kr.dev.icmovie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.MusicAdapter;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.databinding.FragmentMusicDetatilBinding;
import kr.dev.icmovie.models.MusicData;
import kr.dev.icmovie.models.PopularData;

public class MusicDetatilFragment extends Fragment implements OnclickItemPo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private MusicData musicData;
    private  MusicAdapter musicAdapter;
    private FragmentMusicDetatilBinding binding;

    private List<MusicData> musicDataList;

    public MusicDetatilFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);

        binding = FragmentMusicDetatilBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("Music", Context.MODE_PRIVATE);
        editor =  sharedPreferences.edit();
        loadList();

        setItemDetail();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                DividerItemDecoration.VERTICAL);
        binding.rvDatail.addItemDecoration(dividerItemDecoration);


        musicAdapter = new MusicAdapter(musicDataList,this);

        binding.rvDatail.setAdapter(musicAdapter);

    }


    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true /* Enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.homeFragment);
        }


    };
    private    void loadList(){

        musicDataList =  new ArrayList<>();

        for (int i = 0; i < 20; i++) {


            musicDataList.add(new MusicData(R.drawable.img,"Tungi Poyezd Premyera","6.7",
                    "Ragnarning Vikinglar otryadining hikoyasi. U Viking qabilalarining " +
                            "qiroli bo'lish uchun ko'tarildi. Norvegiya afsonasiga ko'ra, u urush va " +
                            "jangchilar xudosi Odinning bevosita avlodi bo'lgan.",R.drawable.drama));

            musicDataList.add(new MusicData(R.drawable.black_pink,"Rohibaning la'nati Ujas kino",
                    "7.1",
                    "Ruminiyada tanho monastirda yosh nun o'z joniga qasd qilganda, "

                    ,R.drawable.avatar));
            musicDataList.add(new MusicData(R.drawable.img_3,"Moviy qo'ng'iz","7.3",
                    "Meksikalik o'smir Xayme Reyes" +
                            " unga super kuchlar beradigan begona kostyumni oladi.",R.drawable.apple_icon));

            musicDataList.add(new MusicData(R.drawable.img_4,
                    "Super-qahramonlar ligasi Multfilm ","6.0",
                    "Super-qahramonlar ligasi Multfilm Uzbek tilida 2022 O'zbekcha tarjima HD",R.drawable.super_hayvon));
            musicDataList.add(new MusicData(R.drawable.img_5,
                    "Super uy hayvonlari ligasi DC","6.7",
                    "Kripto iti Supermenning eng yaxshi do'sti bo'lib, uning xo'jayini kabi " +
                            "yerdan tashqari kuchlarga ega. Ular birgalikda Metropolisda jinoyatchilikka " +
                            "qarshi jasorat bilan kurashadilar. Ammo Supermen va Adolat ligasining" +
                            " boshqa a'zolari noma'lum yovuz odamlar tomonidan o'g'irlab ketilganda, " +
                            "Kripto yangi yordamchilarni, ya'ni to'satdan super kuchga ega bo'lgan " +
                            "boshpanadagi turli xil hayvonlarni o'rgatishi kerak. Super uy hayvonlarining" +
                            " mo'ynali ligasi Supermenni va butun dunyoni qutqara oladimi?",R.drawable.super_heroes));

            musicDataList.add(new MusicData(R.drawable.img_6,"Vikinglar: Valhalla",
                    "7.2","Vikinglarning Angliyani zabt " +
                    "etishga urinishi, Leif Erikssonning Amerikaga sayohati va vikinglar orasida " +
                    "nasroniylikning tarqalishi fonida sevgi, adovat va qasos haqidagi " +
                    "dramatik hikoya.",R.drawable.viking));
            musicDataList.add(new MusicData(R.drawable.img_7,"Аватар 2: Путь воды 2022 ",
                    "7.5","Askarning avatar qiyofasini " +
                    "olganidan so'ng, Jeyk Sulli Navi xalqining etakchisiga aylanadi va yangi " +
                    "do'stlarni Yerdan kelgan yollanma tadbirkorlardan himoya qilish vazifasini " +
                    "o'z zimmasiga oladi. Endi uning uchun kurashadigan odam bor - Jeyk, uning " +
                    "go'zal sevgilisi Neytiri bilan. Og'ir qurollangan yerliklar Pandoraga qaytganda," +
                    " Jeyk javob berishga tayyor.",R.drawable.wakanda));

            musicDataList.add(new MusicData(R.drawable.img_8,"John Wick: Chapter 4",
                    "6.4","ohn Wick uncovers a path to defeating The" +
                    " High Table. But before he can earn his freedom, Wick must face off against" +
                    " a new enemy with powerful alliances across the globe and forces that turn old " +
                    "friends into foes.",R.drawable.baymaks));
            musicDataList.add(new MusicData(R.drawable.img_9,"Black Panther: Wakanda Forever",
                    "6.7","После смерти короля Т`Чаллы" +
                    " королева Рамонда, Шури, М`Баку, Окойе и Дора Милаж сражаются, чтобы " +
                    "защитить Ваканду от мировых держав.",R.drawable.rohiba));

            musicDataList.add(new MusicData(R.drawable.img_10,
                    "Five Feet Apart watch online in Tas-ix","6.1",
                    "The space in which they exist, cruel dictates the condition — the " +
                            "lovers must be no closer than a meter from each other they can't " +
                            "even touch. But true love knows no boundaries, and the stronger the " +
                            "feelings, the greater the temptation to break the rules…",R.drawable.john));
            musicDataList.add(new MusicData(R.drawable.img_11,"BayMax","7.3",
                    "Mehribon va sezgir tibbiy robot Baymax va uning " +
                            "do'stlarining sarguzashtlari haqida.",R.drawable.img_1));

        }


    }


    private  void setItemDetail(){
        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("Music", Context.MODE_PRIVATE);
//
        Type token = new TypeToken<MusicData>(){}.getType();
        musicData= gson.fromJson(sharedPreferences.getString("musicdata", "{}"),token);

//        binding.vvMusic.setImageResource(musicData.getMusicImage());
        binding.tvMusicName1.setText(musicData.getMusicName());
        binding.tvInfoTitle.setText(musicData.getAccountName());

    }

    @Override
    public void clickItem(int position) {

        editor.remove("musicdata");
        Gson gson = new Gson();
        musicData  = musicDataList.get(position);
        editor.putString("musicdata",gson.toJson(musicData));
        editor.commit();
        Toast.makeText(binding.getRoot().getContext(), "Clicked"+musicData.toString(), Toast.LENGTH_SHORT).show();


        Navigation.findNavController(binding.getRoot()).navigate(R.id.musicDetail2Fragment);
    }


}