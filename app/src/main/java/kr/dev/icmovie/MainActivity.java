package kr.dev.icmovie;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import kr.dev.icmovie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    NavController navController;

    final boolean[] doubleBackToExitPressedOnce = {false};
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_container);

        navController= fragment.getNavController();

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                NavigationUI.onNavDestinationSelected(item,navController);

                if(item.getItemId()!=R.id.homeFragment){

                    navController.popBackStack(item.getItemId(),true);

                }

                return true;
            }

        });


        NavigationUI.setupWithNavController(binding.bottomNavigation,navController,false);

//        getOnBackPressedDispatcher().addCallback(this,
//                new OnBackPressedCallback(true) {
//                    @Override
//                    public void handleOnBackPressed() {
//                        if (doubleBackToExitPressedOnce[0]) {
//                            finish();
//                        } else {
//
//                            String message = "Chiqish uchun yana bir marta bosing";
//
//                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                            doubleBackToExitPressedOnce[0] = true;
//                            new Handler().postDelayed(() ->
//                                            doubleBackToExitPressedOnce[0] = false,
//                                    1500);
//                        }
//                    }
//                });

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
//        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}