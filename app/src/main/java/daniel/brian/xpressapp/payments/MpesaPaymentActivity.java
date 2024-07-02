package daniel.brian.xpressapp.payments;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.ActivityMpesaPaymentBinding;

public class MpesaPaymentActivity extends AppCompatActivity {
    ActivityMpesaPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMpesaPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}