package kr.dev.icmovie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.AvtoAdapter;
import kr.dev.icmovie.adapters.OnclickItemPo;
import kr.dev.icmovie.databinding.FragmentAvtoBinding;
import kr.dev.icmovie.models.AvtoData;


public class AvtoFragment extends Fragment implements OnclickItemPo {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AvtoData avtoData;
    private FragmentAvtoBinding binding;

    private List<AvtoData> avtoDataList;
    public AvtoFragment() {
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentAvtoBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadList();
        sharedPreferences = getActivity().getSharedPreferences("Avto", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                DividerItemDecoration.VERTICAL);

        binding.rvAvto.addItemDecoration(dividerItemDecoration);


        AvtoAdapter avtoAdapter = new AvtoAdapter(avtoDataList,this);

        binding.rvAvto.setAdapter(avtoAdapter);


    }


    private    void loadList(){

        avtoDataList =  new ArrayList<>();

        for (int i = 0; i < 20; i++) {


            avtoDataList.add(new AvtoData(R.drawable.img_21,"Tungi Poyezd Premyera","6.7",
                    "Ragnarning Vikinglar otryadining hikoyasi. U Viking qabilalarining " +
                            "qiroli bo'lish uchun ko'tarildi. Norvegiya afsonasiga ko'ra, u urush va " +
                            "jangchilar xudosi Odinning bevosita avlodi bo'lgan.",R.drawable.drama));

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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void clickItem(int position) {
        editor.remove("avtodata");
        Gson gson = new Gson();
        avtoData  = avtoDataList.get(position);
        editor.putString("avtodata",gson.toJson(avtoData));
        editor.commit();

        Navigation.findNavController(binding.getRoot()).navigate(R.id.avtoDetailFragment);
    }


}