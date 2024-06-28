package daniel.brian.xpressapp.auth.fragments;

import android.content.Intent;
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

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.admin.AdminActivity;
import daniel.brian.xpressapp.admin.db.EmployeeDB;
import daniel.brian.xpressapp.auth.db.AuthenticationDB;
import daniel.brian.xpressapp.customer.CustomerActivity;
import daniel.brian.xpressapp.databinding.FragmentLoginBinding;
import daniel.brian.xpressapp.employee.EmployeeActivity;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    AuthenticationDB authDB;
    EmployeeDB employeeDB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater());

        //Navigating to the register Fragment
        binding.loginToRegister.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });

        //Navigating to the forgot password Fragment
        binding.forgotPass.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
        });

        // Declaring the database
        authDB = new AuthenticationDB(this.getContext());
        employeeDB = new EmployeeDB(this.getContext());

        // Login a register user
        binding.btnLogin.setOnClickListener(view1 -> {
            String userEmail = Objects.requireNonNull(binding.email.getText()).toString();
            String userPassword = Objects.requireNonNull(binding.password.getText()).toString();

            boolean checkAdmin = binding.asAdmin.isChecked();
            boolean checkEmployee = binding.asEmployee.isChecked();
            boolean checkCustomer = binding.asCustomer.isChecked();

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                if(userEmail.matches("admin@auto.com") && userPassword.matches("admin@auto")){
                    if(checkAdmin && !checkEmployee && !checkCustomer){
                        binding.asEmployee.isTemporarilyDetached();
                        binding.asCustomer.isTemporarilyDetached();
                        Intent intent = new Intent(this.getContext(), AdminActivity.class);
                        startActivity(intent);
                        Snackbar.make(requireView(),"Logging as Admin Successful!",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(requireView(),"Oops!! Check the right box!",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    if(!checkEmployee && !checkAdmin && checkCustomer){
                        binding.asAdmin.isTemporarilyDetached();
                        binding.asEmployee.isTemporarilyDetached();
                        if(userEmail.contains("@gmail.com") || userEmail.contains("@yahoo.com")){
                            boolean loginUser = authDB.loginUser(userEmail,userPassword);
                            if(loginUser){
                                Intent intent = new Intent(this.getContext(), CustomerActivity.class);
                                startActivity(intent);
                                Snackbar.make(requireView(),"Login Successful!",Snackbar.LENGTH_LONG).show();
                            }else{
                                Snackbar.make(requireView(),"Invalid Credentials! Try Again!",Snackbar.LENGTH_LONG).show();
                            }
                        }else {
                            Snackbar.make(requireView(),"Oops!! Please Enter a valid Email!",Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(requireView(),"Oops!! Check the right box!",Snackbar.LENGTH_LONG).show();
                    }

                    if(!checkCustomer && !checkAdmin && checkEmployee){
                        binding.asAdmin.isTemporarilyDetached();
                        binding.asCustomer.isTemporarilyDetached();
                        if(userEmail.contains("@autocare.com")){
                            boolean loginEmployee = employeeDB.loginEmployee(userEmail,userPassword);
                            if(loginEmployee){
                                Intent intent = new Intent(this.getContext(), EmployeeActivity.class);
                                startActivity(intent);
                                Snackbar.make(requireView(),"Login Successful!",Snackbar.LENGTH_LONG).show();
                            }else{
                                Snackbar.make(requireView(),"Employee not recognised",Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(requireView(),"Oops!! Please Enter a valid Email!",Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        return binding.getRoot();
    }
}