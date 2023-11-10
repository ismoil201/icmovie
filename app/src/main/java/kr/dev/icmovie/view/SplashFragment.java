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

import kr.dev.icmovie.R;
import kr.dev.icmovie.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {


    private FragmentSplashBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SplashFragment() {
        // Required empty public constructor
    }


    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
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
}