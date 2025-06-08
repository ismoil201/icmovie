package kr.dev.icmovie.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.AvtoAdapter;
import kr.dev.icmovie.adapters.MusicAdapter;
import kr.dev.icmovie.adapters.PopularAdapter;
import kr.dev.icmovie.databinding.FragmentSearchBinding;
import kr.dev.icmovie.models.AvtoData;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MusicDao;
import kr.dev.icmovie.room.entity.Music;
import kr.dev.icmovie.models.PopularData;

public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSearchBinding binding;

    private List<Music> musicList;
    private List<PopularData> popularDataList;
    private List<AvtoData> avtoDataList;

    private MusicAdapter adapter;
    private AvtoAdapter avtoAdapter;
    private PopularAdapter popularAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadMusics();
        loadAvto();
        loadPopular();

        adapter = new MusicAdapter(musicList);
        binding.rvMusic.setAdapter(adapter);

        avtoAdapter = new AvtoAdapter(avtoDataList);
        binding.rvAvto.setAdapter(avtoAdapter);

//        popularAdapter = new PopularAdapter(popularDataList);
        binding.rvMovie.setAdapter(popularAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
    private  void  loadMusics(){
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MusicDao musicDao = db.musicDao();

        new Thread(() -> {


            musicList = musicDao.getAllMusics();

            Collections.shuffle(musicList);

            requireActivity().runOnUiThread(() -> {
                adapter = new MusicAdapter(musicList);
                binding.rvMusic.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            });
        }).start();
    }

    private    void loadAvto(){

        avtoDataList =  new ArrayList<>();

        for (int i = 0; i < 20; i++) {


            avtoDataList.add(new AvtoData(R.drawable.img_15,"Tungi Poyezd Premyera","6.7",
                    "Ragnarning Vikinglar otryadining hikoyasi. U Viking qabilalarining " +
                            "qiroli bo'lish uchun ko'tarildi. Norvegiya afsonasiga ko'ra, u urush va " +
                            "jangchilar xudosi Odinning bevosita avlodi bo'lgan.",R.drawable.apple_icon));

            avtoDataList.add(new AvtoData(R.drawable.img_12,"Rohibaning la'nati Ujas kino",
                    "7.1",
                    "Ruminiyada tanho monastirda yosh nun o'z joniga qasd qilganda, "

                    ,R.drawable.avatar));
            avtoDataList.add(new AvtoData(R.drawable.img_16,"Moviy qo'ng'iz","7.3",
                    "Meksikalik o'smir Xayme Reyes" +
                            " unga super kuchlar beradigan begona kostyumni oladi.",R.drawable.apple_icon));

            avtoDataList.add(new AvtoData(R.drawable.img_17,
                    "Super-qahramonlar ligasi Multfilm ","6.0",
                    "Super-qahramonlar ligasi Multfilm Uzbek tilida 2022 O'zbekcha tarjima HD",R.drawable.super_hayvon));
            avtoDataList.add(new AvtoData(R.drawable.img_15,
                    "Super uy hayvonlari ligasi DC","6.7",
                    "Kripto iti Supermenning eng yaxshi do'sti bo'lib, uning xo'jayini kabi " +
                            "yerdan tashqari kuchlarga ega. Ular birgalikda Metropolisda jinoyatchilikka " +
                            "qarshi jasorat bilan kurashadilar. Ammo Supermen va Adolat ligasining" +
                            " boshqa a'zolari noma'lum yovuz odamlar tomonidan o'g'irlab ketilganda, " +
                            "Kripto yangi yordamchilarni, ya'ni to'satdan super kuchga ega bo'lgan " +
                            "boshpanadagi turli xil hayvonlarni o'rgatishi kerak. Super uy hayvonlarining" +
                            " mo'ynali ligasi Supermenni va butun dunyoni qutqara oladimi?",R.drawable.super_heroes));

            avtoDataList.add(new AvtoData(R.drawable.img_16,"Vikinglar: Valhalla",
                    "7.2","Vikinglarning Angliyani zabt " +
                    "etishga urinishi, Leif Erikssonning Amerikaga sayohati va vikinglar orasida " +
                    "nasroniylikning tarqalishi fonida sevgi, adovat va qasos haqidagi " +
                    "dramatik hikoya.",R.drawable.viking));
            avtoDataList.add(new AvtoData(R.drawable.img_17,"Аватар 2: Путь воды 2022 ",
                    "7.5","Askarning avatar qiyofasini " +
                    "olganidan so'ng, Jeyk Sulli Navi xalqining etakchisiga aylanadi va yangi " +
                    "do'stlarni Yerdan kelgan yollanma tadbirkorlardan himoya qilish vazifasini " +
                    "o'z zimmasiga oladi. Endi uning uchun kurashadigan odam bor - Jeyk, uning " +
                    "go'zal sevgilisi Neytiri bilan. Og'ir qurollangan yerliklar Pandoraga qaytganda," +
                    " Jeyk javob berishga tayyor.",R.drawable.wakanda));

            avtoDataList.add(new AvtoData(R.drawable.img_18,"John Wick: Chapter 4",
                    "6.4","ohn Wick uncovers a path to defeating The" +
                    " High Table. But before he can earn his freedom, Wick must face off against" +
                    " a new enemy with powerful alliances across the globe and forces that turn old " +
                    "friends into foes.",R.drawable.baymaks));
            avtoDataList.add(new AvtoData(R.drawable.img_19,"Black Panther: Wakanda Forever",
                    "6.7","После смерти короля Т`Чаллы" +
                    " королева Рамонда, Шури, М`Баку, Окойе и Дора Милаж сражаются, чтобы " +
                    "защитить Ваканду от мировых держав.",R.drawable.rohiba));

            avtoDataList.add(new AvtoData(R.drawable.img_20,
                    "Five Feet Apart watch online in Tas-ix","6.1",
                    "The space in which they exist, cruel dictates the condition — the " +
                            "lovers must be no closer than a meter from each other they can't " +
                            "even touch. But true love knows no boundaries, and the stronger the " +
                            "feelings, the greater the temptation to break the rules…",R.drawable.john));
            avtoDataList.add(new AvtoData(R.drawable.img_21,"BayMax","7.3",
                    "Mehribon va sezgir tibbiy robot Baymax va uning " +
                            "do'stlarining sarguzashtlari haqida.",R.drawable.img_1));

        }


    }
    private    void loadPopular(){

        popularDataList =  new ArrayList<>();

        for (int i = 0; i < 20; i++) {


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
            popularDataList.add(new PopularData(R.drawable.avatar,"Аватар 2: Путь воды 2022 ",
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


}