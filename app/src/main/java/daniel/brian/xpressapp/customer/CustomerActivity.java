package daniel.brian.xpressapp.customer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.ActivityCustomerBinding;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this,R.id.fragmentContainer);
        NavigationUI.setupWithNavController(navigationView,navController);
    }
}