package daniel.brian.xpressapp.employee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.xpressapp.databinding.TaskAssignmentTemplateBinding;

public class AssignedTaskAdapter extends RecyclerView.Adapter<AssignedTaskAdapter.AssignedTaskViewHolder> {
    private ArrayList employee,carReg,carModel,time,service;
    private Context context;

    public AssignedTaskAdapter(Context context,ArrayList employee, ArrayList carReg, ArrayList carModel, ArrayList time, ArrayList service) {
        this.context = context;
        this.employee = employee;
        this.carReg = carReg;
        this.carModel = carModel;
        this.time = time;
        this.service = service;
    }


    @NonNull
    @Override
    public AssignedTaskAdapter.AssignedTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssignedTaskViewHolder(
                TaskAssignmentTemplateBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AssignedTaskAdapter.AssignedTaskViewHolder holder, int position) {
        holder.binding.assignedName.setText(String.valueOf(employee.get(position)));
        holder.binding.assignedCarReg.setText(String.valueOf(carReg.get(position)));
        holder.binding.assignedCarModel.setText(String.valueOf(carModel.get(position)));
        holder.binding.assignedTime.setText(String.valueOf(time.get(position)));
        holder.binding.assignedService.setText(String.valueOf(service.get(position)));
    }

    @Override
    public int getItemCount() {
        return time.size();
    }

    public static class AssignedTaskViewHolder extends RecyclerView.ViewHolder{
        private final TaskAssignmentTemplateBinding binding;

        public AssignedTaskViewHolder(TaskAssignmentTemplateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
