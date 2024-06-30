package daniel.brian.xpressapp.employee.fragments;

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
import daniel.brian.xpressapp.databinding.FragmentCompletionTaskBinding;
import daniel.brian.xpressapp.employee.db.CompletedTasksDB;

public class CompletionTaskFragment extends Fragment {
    FragmentCompletionTaskBinding binding;
    CompletedTasksDB db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletionTaskBinding.inflate(getLayoutInflater());

        db = new CompletedTasksDB(this.getContext());

        binding.completedTask.setOnClickListener(v -> {
            String employee = Objects.requireNonNull(binding.completedTaskName.getText()).toString();
            String carReg = Objects.requireNonNull(binding.completedTaskCarReg.getText()).toString();
            String carModel = Objects.requireNonNull(binding.completedTaskCarModel.getText()).toString();
            String product = Objects.requireNonNull(binding.productUsed.getText()).toString();
            String service = Objects.requireNonNull(binding.completedService.getText()).toString();
            String amount = Objects.requireNonNull(binding.completedTotalCost.getText()).toString();

            if(employee.isEmpty() || carReg.isEmpty() || carModel.isEmpty() || product.isEmpty() || service.isEmpty() || amount.isEmpty()){
                Snackbar.make(requireView(),"Please Enter all Fields",Snackbar.LENGTH_SHORT).show();
            }else{
                boolean completedTask = db.completedTasks(employee,carReg,carModel,product,service,amount);
                if(completedTask){
                    Snackbar.make(requireView(),"Task Successfully saved",Snackbar.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }else{
                    Snackbar.make(requireView(),"Error saving the task",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }
}