package daniel.brian.xpressapp.admin.fragments;

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
import daniel.brian.xpressapp.admin.db.TaskAssignmentDB;
import daniel.brian.xpressapp.databinding.FragmentTaskAssignmentBinding;

public class TaskAssignmentFragment extends Fragment {
    FragmentTaskAssignmentBinding binding;
    TaskAssignmentDB db;
    EmployeeDB employeeDB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskAssignmentBinding.inflate(getLayoutInflater());

        db = new TaskAssignmentDB(this.getContext());
        employeeDB = new EmployeeDB(this.getContext());


        binding.saveTask.setOnClickListener(v -> {
            String employee = Objects.requireNonNull(binding.employeeName.getText()).toString();
            String carReg = Objects.requireNonNull(binding.employeeCarReg.getText()).toString();
            String carModel = Objects.requireNonNull(binding.employeeCarModel.getText()).toString();
            String time = Objects.requireNonNull(binding.employeeTime.getText()).toString();
            String service = Objects.requireNonNull(binding.employeeService.getText()).toString();

            if(employee.isEmpty() || carReg.isEmpty() || carModel.isEmpty() || time.isEmpty() || service.isEmpty()){
                Snackbar.make(requireView(),"Please Enter all Fields",Snackbar.LENGTH_SHORT).show();
            }else{
                boolean checkEmployee = employeeDB.checkEmployee(employee);
                if(checkEmployee){
                    boolean task = db.createTask(employee,carReg,carModel,time,service);
                    if(task){
                        Snackbar.make(requireView(),"Task Assigned successfully",Snackbar.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(v);
                        navController.navigateUp();
                    }else{
                        Snackbar.make(requireView(),"Error saving the task",Snackbar.LENGTH_SHORT).show();
                    }
                }else{
                    Snackbar.make(requireView(),"Error doesn't exist",Snackbar.LENGTH_SHORT).show();
                }
            }

        });
        return binding.getRoot();
    }
}