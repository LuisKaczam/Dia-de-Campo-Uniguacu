package com.uniguacu.dia_de_campo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.uniguacu.dia_de_campo.fragments.HomeFragment;
import com.uniguacu.dia_de_campo.fragments.InternetFragment;
import com.uniguacu.dia_de_campo.fragments.MapaFragment;
import com.uniguacu.dia_de_campo.fragments.PontuacaoFragment;
import com.uniguacu.dia_de_campo.fragments.RankingFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        frameLayout = findViewById(R.id.framelayout);

        MudaFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        MudaFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_dashboard:
                        MudaFragment(new PontuacaoFragment());
                        return true;
                    case R.id.navigation_notifications:
                        if (verificaConexao()) {
                            MudaFragment(new MapaFragment());
                        }else{
                            MudaFragment(new InternetFragment());
                        }
                        return true;
                    case R.id.navigation_ranking:
                        if (verificaConexao()) {
                            MudaFragment(new RankingFragment());
                        }else{
                            MudaFragment(new InternetFragment());
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void MudaFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }

    public boolean verificaConexao() {
        ConnectivityManager conexão = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return conexão.getActiveNetworkInfo() != null && conexão.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}