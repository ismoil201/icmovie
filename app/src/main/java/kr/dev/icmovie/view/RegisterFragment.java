package kr.dev.icmovie.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.dev.icmovie.R;
import kr.dev.icmovie.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clicks();

    }


    private  void clicks(){

        binding.btnSign.setOnClickListener(view ->

                Navigation.findNavController(binding.getRoot()).navigate(R.id.signFragment));
        binding.tvLogin.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment));
        binding.ivGoogleIcon.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment));
        binding.ivAppleIcon.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment));
        binding.ivFacebookIcon.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment));


    }
}