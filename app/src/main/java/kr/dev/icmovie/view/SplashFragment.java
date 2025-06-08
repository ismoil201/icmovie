package kr.dev.icmovie.view;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.databinding.FragmentSplashBinding;
import kr.dev.icmovie.models.PopularData;
import kr.dev.icmovie.room.AppDataBase;
import kr.dev.icmovie.room.dao.MovieDao;
import kr.dev.icmovie.room.dao.MusicDao;
import kr.dev.icmovie.room.entity.Movie;
import kr.dev.icmovie.room.entity.Music;

public class SplashFragment extends Fragment {


    private FragmentSplashBinding binding;


    public SplashFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentSplashBinding.inflate(getLayoutInflater(),container,false);


        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.registerFragment);

            }
        },2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Navigation.findNavController(binding.getRoot()).popBackStack(R.id.splashFragment,
                true);

    }


    private void loadList() {
        AppDataBase db = AppDataBase.getInstance(requireContext());
        MovieDao movieDao = db.movieDao();
        MusicDao musicDao = db.musicDao();

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (movieDao.getCount() == 0) {
                    movieDao.insertMovie(new Movie(R.drawable.img, "Night Train Premiere", "6.7",
                            "Action", "The story of Ragnar's Viking squad. " +
                            "He rose to become the king of the Viking tribes. According " +
                            "to Norwegian legend, he was a direct descendant of Odin, the g" +
                            "od of war and warriors.",
                            R.raw.night1, false));
                    movieDao.insertMovie(new Movie(R.drawable.rohiba, "The Nun's Curse Horror Movie",
                            "7.1", "Horror",
                            "When a young nun takes her own life at a secluded monastery in " +
                                    "Romania, the Vatican sends a priest with a haunted past and a novice " +
                                    "on the threshold of her final vows to investigate. Risking not only" +
                                    " their lives but their souls, they confront a malevolent force that" +
                                    " has taken the form of a demonic nun. The monastery soon becomes a " +
                                    "living and cursed battleground.", R.raw.rohiba2, false));
                    movieDao.insertMovie(new Movie(R.drawable.blue, "Blue Beetle", "7.3",
                            "Science Fiction",
                            "A Mexican teenager, Jaime Reyes, comes into possession" +
                                    " of an alien suit that grants him superpowers.", R.raw.blue
                            , false));
                    movieDao.insertMovie(new Movie(R.drawable.super_heroes,
                            "Justice League Animated", "6.0", "Animation",
                            "Justice League Animated in English language, 2022, English-dubbed in HD.",
                            R.raw.justle4, false));
                    movieDao.insertMovie(new Movie(R.drawable.super_hayvon,
                            "DC League of Super-Pets", "6.7", "Animation",
                            "Krypto the Super-Dog is Superman's best friend and possesses " +
                                    "the same otherworldly powers as his owner. Together, they " +
                                    "courageously fight crime in Metropolis. But when Superman and " +
                                    "the rest of the Justice League are kidnapped by unknown villains, " +
                                    "Krypto must train a group of shelter animals who unexpectedly gain" +
                                    " superpowers. Can this furry league of super-pets save Superman " +
                                    "and the entire world?", R.raw.dog, false));
                    movieDao.insertMovie(new Movie(R.drawable.viking,
                            "Vikings: Valhalla", "7.2", "History | Action",
                            "A dramatic story of love, rivalry, and revenge set against " +
                                    "the backdrop of the Vikings’ attempt to conquer England, " +
                                    "Leif Erikson’s journey to America, and the spread of " +
                                    "Christianity among the Viking people.", R.raw.viking, false));


                    movieDao.insertMovie(new Movie(R.drawable.avatar,
                            "Avatar 2: The Way of Water (2022)",
                            "7.5", "Science Fiction | Fantasy",
                            "After taking on the form of an avatar," +
                                    " Jake Sully becomes the leader of the Na'vi " +
                                    "people and takes on the responsibility of protecting his" +
                                    " new friends from mercenary entrepreneurs from Earth. Now " +
                                    "he has someone to fight for — Jake and his beloved Neytiri." +
                                    " When heavily armed humans return to Pandora, Jake is ready " +
                                    "to defend it.", R.raw.avatar, false));

                    movieDao.insertMovie(new Movie(R.drawable.john, "John Wick: Chapter 4",
                            "6.4", "Action", "ohn Wick uncovers a path to defeating The" +
                            " High Table. But before he can earn his freedom, Wick must face off against" +
                            " a new enemy with powerful alliances across the globe and forces that turn old " +
                            "friends into foes.",
                            R.raw.john, false));


                    //todo musicDao

                    musicDao.insertMusic(new Music(R.drawable.img_23,
                            "Korean Kpop","859.4M",
                            "ROSÉ & Bruno Mars - APT. (Official Music Video)",
                            R.drawable.black_pink,R.raw.rose,false));

                    musicDao.insertMusic(new Music(R.drawable.trips,
                            "tripleS", "453.2M",
                            "tripleS(트리플에스) '깨어' (Are You Alive) Official MV",
                            R.drawable.img_6,R.raw.triple,false));
                    musicDao.insertMusic(new Music(R.drawable.img_24,
                            "WEVERSE MUZ","533.9M",
                            "BABYMONSTER - 'DRIP' M/V",
                            R.drawable.blue,R.raw.baby,false));

                    musicDao.insertMusic(new Music(
                            R.drawable.jennie, "Jennie Official",
                            "234.6M","JENNIE - like JENNIE " +
                            "(Official Live Performance Video l NPOP LIMITED EDITION - SIDE A)",
                            R.drawable.img_24,R.raw.jennie,false
                    ));


                    musicDao.insertMusic(
                            new Music(R.drawable.img_3,"Moviy qo'ng'iz","75.3K",
                                    "Meksikalik o'smir Xayme Reyes" +
                                            " unga super kuchlar beradigan begona kostyumni oladi."
                                    ,R.drawable.apple_icon,R.raw.baby,false
                    ));

                    musicDao.insertMusic(
                            new Music(R.drawable.img_6,"John Wick: Chapter 4",
                                    "6.4","ohn Wick uncovers a path to defeating The" +
                                    " High Table. But before he can earn his freedom, Wick must face off against" +
                                    " a new enemy with powerful alliances across the globe and forces that turn old " +
                                    "friends into foes.",
                                    R.drawable.baymaks,R.raw.john,false)
                    );

                    musicDao.insertMusic(
                            new Music(R.drawable.img_10,
                                    "Five Feet Apart watch online in Tas-ix","6.1",
                                    "The space in which they exist, cruel dictates the condition — the " +
                                            "lovers must be no closer than a meter from each other they can't " +
                                            "even touch. But true love knows no boundaries, and the stronger the " +
                                            "feelings, the greater the" +
                                            " temptation to break the rules…",
                                    R.drawable.john,R.raw.night1,false)
                    );




                }

            }
        }).start();



    }


//    private    void loadList(){
//
//        musicList =  new ArrayList<>();
//
//        for (int i = 0; i < 20; i++) {
//
//
//            musicList.add(new Music(R.drawable.img,"Tungi Poyezd Premyera","6.7",
//                    "Ragnarning Vikinglar otryadining hikoyasi. U Viking qabilalarining " +
//                            "qiroli bo'lish uchun ko'tarildi. Norvegiya afsonasiga ko'ra, u urush va " +
//                            "jangchilar xudosi Odinning bevosita avlodi bo'lgan.",R.drawable.drama));
//
//            musicList.add(new Music(R.drawable.black_pink,"Rohibaning la'nati Ujas kino",
//                    "7.1",
//                    "Ruminiyada tanho monastirda yosh nun o'z joniga qasd qilganda, "
//
//                    ,R.drawable.avatar));
//            musicList.add(new Music(R.drawable.img_3,"Moviy qo'ng'iz","7.3",
//                    "Meksikalik o'smir Xayme Reyes" +
//                            " unga super kuchlar beradigan begona kostyumni oladi.",R.drawable.apple_icon));
//
//            musicList.add(new Music(R.drawable.img_4,
//                    "Super-qahramonlar ligasi Multfilm ","6.0",
//                    "Super-qahramonlar ligasi Multfilm Uzbek tilida 2022 O'zbekcha tarjima HD",R.drawable.super_hayvon));
//            musicList.add(new Music(R.drawable.img_5,
//                    "Super uy hayvonlari ligasi DC","6.7",
//                    "Kripto iti Supermenning eng yaxshi do'sti bo'lib, uning xo'jayini kabi " +
//                            "yerdan tashqari kuchlarga ega. Ular birgalikda Metropolisda jinoyatchilikka " +
//                            "qarshi jasorat bilan kurashadilar. Ammo Supermen va Adolat ligasining" +
//                            " boshqa a'zolari noma'lum yovuz odamlar tomonidan o'g'irlab ketilganda, " +
//                            "Kripto yangi yordamchilarni, ya'ni to'satdan super kuchga ega bo'lgan " +
//                            "boshpanadagi turli xil hayvonlarni o'rgatishi kerak. Super uy hayvonlarining" +
//                            " mo'ynali ligasi Supermenni va butun dunyoni qutqara oladimi?",R.drawable.super_heroes));
//
//            musicList.add(new Music(R.drawable.img_6,"Vikinglar: Valhalla",
//                    "7.2","Vikinglarning Angliyani zabt " +
//                    "etishga urinishi, Leif Erikssonning Amerikaga sayohati va vikinglar orasida " +
//                    "nasroniylikning tarqalishi fonida sevgi, adovat va qasos haqidagi " +
//                    "dramatik hikoya.",R.drawable.viking));
//            musicList.add(new Music(R.drawable.img_7,"Аватар 2: Путь воды 2022 ",
//                    "7.5","Askarning avatar qiyofasini " +
//                    "olganidan so'ng, Jeyk Sulli Navi xalqining etakchisiga aylanadi va yangi " +
//                    "do'stlarni Yerdan kelgan yollanma tadbirkorlardan himoya qilish vazifasini " +
//                    "o'z zimmasiga oladi. Endi uning uchun kurashadigan odam bor - Jeyk, uning " +
//                    "go'zal sevgilisi Neytiri bilan. Og'ir qurollangan yerliklar Pandoraga qaytganda," +
//                    " Jeyk javob berishga tayyor.",R.drawable.wakanda));
//
//            musicList.add(new Music(R.drawable.img_8,"John Wick: Chapter 4",
//                    "6.4","ohn Wick uncovers a path to defeating The" +
//                    " High Table. But before he can earn his freedom, Wick must face off against" +
//                    " a new enemy with powerful alliances across the globe and forces that turn old " +
//                    "friends into foes.",R.drawable.baymaks));
//            musicList.add(new Music(R.drawable.img_9,"Black Panther: Wakanda Forever",
//                    "6.7","После смерти короля Т`Чаллы" +
//                    " королева Рамонда, Шури, М`Баку, Окойе и Дора Милаж сражаются, чтобы " +
//                    "защитить Ваканду от мировых держав.",R.drawable.rohiba));
//
//            musicList.add(new Music(R.drawable.img_10,
//                    "Five Feet Apart watch online in Tas-ix","6.1",
//                    "The space in which they exist, cruel dictates the condition — the " +
//                            "lovers must be no closer than a meter from each other they can't " +
//                            "even touch. But true love knows no boundaries, and the stronger the " +
//                            "feelings, the greater the temptation to break the rules…",R.drawable.john));
//            musicList.add(new Music(R.drawable.img_11,"BayMax","7.3",
//                    "Mehribon va sezgir tibbiy robot Baymax va uning " +
//                            "do'stlarining sarguzashtlari haqida.",R.drawable.img_1));
//
//        }
//
//
//    }




}