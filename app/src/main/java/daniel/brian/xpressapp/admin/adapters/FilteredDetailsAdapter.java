package daniel.brian.xpressapp.admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.xpressapp.databinding.FilterTemplateBinding;

public class FilteredDetailsAdapter extends RecyclerView.Adapter<FilteredDetailsAdapter.FilteredDetailsViewHolder> {
    private ArrayList employeeName,carReg,carModel,product,service,totalAmount;
    private Context context;

    public FilteredDetailsAdapter(
            Context context,
            ArrayList employeeName,
            ArrayList carReg,
            ArrayList carModel,
            ArrayList product,
            ArrayList service,
            ArrayList totalAmount
    ){
        this.context = context;
        this.employeeName = employeeName;
        this.carReg = carReg;
        this.carModel = carModel;
        this.product = product;
        this.service = service;
        this.totalAmount = totalAmount;
    }

    @NonNull
    @Override
    public FilteredDetailsAdapter.FilteredDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilteredDetailsAdapter.FilteredDetailsViewHolder(
                FilterTemplateBinding.inflate(LayoutInflater.from(parent.getContext()))
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FilteredDetailsAdapter.FilteredDetailsViewHolder holder, int position) {
        holder.binding.employeeName.setText(String.valueOf(employeeName.get(position)));
        holder.binding.CarReg.setText(String.valueOf(carReg.get(position)));
        holder.binding.CarModel.setText(String.valueOf(carModel.get(position)));
        holder.binding.productUsed.setText(String.valueOf(product.get(position)));
        holder.binding.ServiceCompleted.setText(String.valueOf(service.get(position)));
        holder.binding.totalAmount.setText(String.valueOf(totalAmount.get(position)));
    }

    @Override
    public int getItemCount() {
        return totalAmount.size();
    }

    public static class FilteredDetailsViewHolder extends RecyclerView.ViewHolder{
        private final FilterTemplateBinding binding;

        public FilteredDetailsViewHolder(FilterTemplateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
