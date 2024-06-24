package daniel.brian.xpressapp.customer.appointments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.FragmentScheduleBinding;


public class ScheduleFragment extends Fragment {
    FragmentScheduleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(getLayoutInflater());

        // tyres add
        binding.tyresAdd.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_scheduleFragment_to_tyresFragment);
        });

        // oil add
        binding.oilAdd.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_scheduleFragment_to_oilFragment);
        });

        // brakes add
        binding.brakesAdd.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_scheduleFragment_to_brakesFragment);
        });

        // battery add
        binding.batteryAdd.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_scheduleFragment_to_batteryFragment);
        });

        // suspension add
        binding.suspensionAdd.setOnClickListener(v -> {
           NavController navController = Navigation.findNavController(v);
           navController.navigate(R.id.action_scheduleFragment_to_suspensionFragment);
        });

        return binding.getRoot();
    }
}