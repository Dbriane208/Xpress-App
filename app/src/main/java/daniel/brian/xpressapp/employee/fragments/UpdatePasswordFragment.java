package daniel.brian.xpressapp.employee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.xpressapp.admin.db.EmployeeDB;
import daniel.brian.xpressapp.databinding.FragmentUpdatePasswordBinding;

public class UpdatePasswordFragment extends Fragment {
    FragmentUpdatePasswordBinding binding;
    EmployeeDB db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdatePasswordBinding.inflate(getLayoutInflater());

        db = new EmployeeDB(this.getContext());

        binding.updatePass.setOnClickListener(view -> {
            String email = Objects.requireNonNull(binding.email.getText()).toString();
            String password = Objects.requireNonNull(binding.password.getText()).toString();

            if(email.isEmpty() || password.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields",Snackbar.LENGTH_LONG).show();
            } else {
                if (email.contains("@autocare.com")) {
                    boolean updatePass = db.updatePassword(email, password);
                    if (updatePass) {
                        Snackbar.make(requireView(), "Password Updated Successfully!", Snackbar.LENGTH_LONG).show();
                        NavController navController = Navigation.findNavController(view);
                        navController.navigateUp();
                    } else {
                        Snackbar.make(requireView(), "Password Failed to update", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(requireView(),"Please Enter a valid Email format",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return binding.getRoot();
    }
}