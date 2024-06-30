package daniel.brian.xpressapp.employee.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.FragmentEmployeeDashboardBinding;

public class EmployeeDashboardFragment extends Fragment {
    FragmentEmployeeDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeDashboardBinding.inflate(getLayoutInflater());

        // navigate to completed task
        binding.completedTask.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_employeeDashboardFragment_to_completionTaskFragment);
        });

        // navigate to assigned task
        binding.assignedTask.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_employeeDashboardFragment_to_assignedTasksFragment);
        });

        return binding.getRoot();
    }
}