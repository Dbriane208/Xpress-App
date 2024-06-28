package daniel.brian.xpressapp.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.FragmentAdminBinding;

public class AdminFragment extends Fragment {
    FragmentAdminBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminBinding.inflate(getLayoutInflater());

        // navigate to completed bookings
        binding.completedBookings.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_adminFragment_to_completedBookingsFragment);
        });

        // navigate to all bookings
        binding.AllBookings.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_adminFragment_to_customerBookingsFragment);
        });

        // employee registration
        binding.employeeRegistration.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_adminFragment_to_registerEmployeesFragment);
        });

        // reports
        binding.reports.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_adminFragment_to_reportsFragment);
        });

        // task assignment
        binding.taskAssignment.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_adminFragment_to_taskAssignmentFragment);
        });

        // invoice
        binding.customerInvoice.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_adminFragment_to_customerInvoiceFragment);
        });

        return binding.getRoot();
    }
}