package daniel.brian.xpressapp.admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.xpressapp.databinding.CompletedTasksTemplateBinding;

public class CompletedBookingsAdapter extends RecyclerView.Adapter<CompletedBookingsAdapter.CompletedBookingsViewHolder> {
    private ArrayList employee,carReg,carModel,product,service,totalAmount;
    private Context context;

    public CompletedBookingsAdapter(Context context, ArrayList employee, ArrayList carReg, ArrayList carModel, ArrayList product, ArrayList service, ArrayList totalAmount) {
        this.context = context;
        this.employee = employee;
        this.carReg = carReg;
        this.carModel = carModel;
        this.product = product;
        this.service = service;
        this.totalAmount = totalAmount;
    }

    @NonNull
    @Override
    public CompletedBookingsAdapter.CompletedBookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompletedBookingsViewHolder(
                CompletedTasksTemplateBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedBookingsAdapter.CompletedBookingsViewHolder holder, int position) {
        holder.binding.servedBy.setText(String.valueOf(employee.get(position)));
        holder.binding.carRegTask.setText(String.valueOf(carReg.get(position)));
        holder.binding.carModelTask.setText(String.valueOf(carModel.get(position)));
        holder.binding.productUsedTask.setText(String.valueOf(product.get(position)));
        holder.binding.serviceDone.setText(String.valueOf(service.get(position)));
        holder.binding.amountUsed.setText(String.valueOf(totalAmount.get(position)));
    }

    @Override
    public int getItemCount() {
        return totalAmount.size();
    }

    public static class CompletedBookingsViewHolder extends RecyclerView.ViewHolder{
        private final CompletedTasksTemplateBinding binding;
        public CompletedBookingsViewHolder(CompletedTasksTemplateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
