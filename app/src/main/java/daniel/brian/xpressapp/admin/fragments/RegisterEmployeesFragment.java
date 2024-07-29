package daniel.brian.xpressapp.admin.fragments;

import static daniel.brian.xpressapp.payments.utils.Utils.validatePhoneNumber;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.admin.db.EmployeeDB;
import daniel.brian.xpressapp.databinding.FragmentRegisterEmployeesBinding;


public class RegisterEmployeesFragment extends Fragment {
    FragmentRegisterEmployeesBinding binding;
    EmployeeDB db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterEmployeesBinding.inflate(getLayoutInflater());

        db = new EmployeeDB(this.getContext());

        binding.employeeRegister.setOnClickListener(v -> {
            String name = Objects.requireNonNull(binding.employeeUsername.getText()).toString();
            String email = Objects.requireNonNull(binding.employeeEmail.getText()).toString();
            String phone = Objects.requireNonNull(binding.employeePhone.getText()).toString();
            String password = Objects.requireNonNull(binding.employeePassword.getText()).toString();

            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
                if(!phone.startsWith("07") || !phone.startsWith("01") && !(phone.length() == 10)) {
                    Snackbar.make(requireView(), "Please Enter valid phone number", Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(requireView(), "Please enter all Fields", Snackbar.LENGTH_SHORT).show();
                }
            }else {
                if (email.contains("@autocare.com")) {
                    boolean registerEmployee = db.registerEmployee(name, email, validatePhoneNumber(phone), password);
                    if (registerEmployee) {
                        Snackbar.make(requireView(), "Employee successfully registered!", Snackbar.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(v);
                        navController.navigateUp();
                    } else {
                        Snackbar.make(requireView(), "Error registering the user", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return binding.getRoot();
    }
}