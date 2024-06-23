package daniel.brian.xpressapp.auth.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.auth.db.AuthenticationDB;
import daniel.brian.xpressapp.customer.CustomerActivity;
import daniel.brian.xpressapp.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;
    AuthenticationDB authDB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());

        // Declaring the database
        authDB = new AuthenticationDB(this.getContext());

        //Navigating to the Login Fragment
       binding.regToLogin.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
        });

        // Registering a new user
       binding.btnRegister.setOnClickListener(view1 -> {
            String name = Objects.requireNonNull(binding.username.getText()).toString();
            String userEmail = Objects.requireNonNull(binding.email.getText()).toString();
            String userPassword = Objects.requireNonNull(binding.password.getText()).toString();

            if(name.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                if(userEmail.contains("@gmail.com") || userEmail.contains("@yahoo.com")){
                    boolean registerUser = authDB.registerUser(name,userEmail,userPassword);
                    if(registerUser){
                        Snackbar.make(requireView(),"Yay!! Registration Successful!",Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(this.getContext(), CustomerActivity.class);
                        startActivity(intent);
                    }else{
                        Snackbar.make(requireView(),"Oops!! Registration Failed!. User already exists!",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(requireView(),"Oops!! Please Enter a valid Email!",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        return binding.getRoot();
    }
}