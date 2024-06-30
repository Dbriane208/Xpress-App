package daniel.brian.xpressapp.employee.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import daniel.brian.xpressapp.admin.db.TaskAssignmentDB;
import daniel.brian.xpressapp.databinding.FragmentAssignedTasksBinding;
import daniel.brian.xpressapp.employee.adapters.AssignedTaskAdapter;

public class AssignedTasksFragment extends Fragment {
    FragmentAssignedTasksBinding binding;
    TaskAssignmentDB db;
    RecyclerView recyclerView;
    AssignedTaskAdapter assignedTaskAdapter;
    private ArrayList<String> employee,carReg,carModel,time,service;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssignedTasksBinding.inflate(getLayoutInflater());

        db = new TaskAssignmentDB(this.getContext());
        employee = new ArrayList<>();
        carReg = new ArrayList<>();
        carModel = new ArrayList<>();
        time = new ArrayList<>();
        service = new ArrayList<>();

        recyclerView = binding.assignedTasksRV;
        assignedTaskAdapter = new AssignedTaskAdapter(this.getContext(),employee,carReg,carModel,time,service);
        recyclerView.setAdapter(assignedTaskAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2, LinearLayoutManager.VERTICAL,false));
        displayData();

        return binding.getRoot();
    }

    private void displayData() {
        Cursor cursor = db.getAllTasks();
        if(cursor.getCount() == 0){
            Snackbar.make(requireView(),"No Available Tasks",Snackbar.LENGTH_SHORT).show();
        }else{
            employee.clear();
            carReg.clear();
            carModel.clear();
            time.clear();
            service.clear();

            while(cursor.moveToNext()){
                employee.add(cursor.getString(0));
                carReg.add(cursor.getString(1));
                carModel.add(cursor.getString(2));
                time.add(cursor.getString(3));
                service.add(cursor.getString(4));
            }

        }

    }
}