package kr.dev.icmovie.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import kr.dev.icmovie.R;
import kr.dev.icmovie.adapters.HomeAdapter;
import kr.dev.icmovie.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    private List<Fragment> fragmentList;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(),container,false);

        fragmentList = new ArrayList<>();

        fragmentList.add( new PopularFragment());
        fragmentList.add( new AvtoFragment());
        fragmentList.add( new MusicFragment());
        homeAdapter = new HomeAdapter(getChildFragmentManager(),getLifecycle(),fragmentList);

        binding.vpHome.setAdapter(homeAdapter);
        new TabLayoutMediator(binding.tlHome, binding.vpHome, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 :{
                        tab.setText("Films");
                        return;
                    }
                    case 1 :{
                        tab.setText("Avto");
                        return;
                    } case 2: {
                        tab.setText("Music");
                        return;
                    }
                }
            }
        }).attach();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
        clicks();

    }
    private  void clicks(){

        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.profileFragment);
            }
        });


    }

}