package daniel.brian.xpressapp.admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.xpressapp.databinding.AllBookingsTemplateBinding;

public class AllBookingsAdapter extends RecyclerView.Adapter<AllBookingsAdapter.AllBookingViewHolder>{
    private ArrayList service;
    private ArrayList branch;
    private ArrayList firstname;
    private ArrayList phone;
    private ArrayList time;
    private ArrayList date;
    private ArrayList carReg;
    private ArrayList carModel;
    private Context context;

    public AllBookingsAdapter(Context context,
                              ArrayList service,
                              ArrayList branch,
                              ArrayList firstname,
                              ArrayList phone,
                              ArrayList time,
                              ArrayList date,
                              ArrayList carReg,
                              ArrayList carModel){
        this.context = context;
        this.service = service;
        this.branch = branch;
        this.firstname = firstname;
        this.phone = phone;
        this.time = time;
        this.date = date;
        this.carReg = carReg;
        this.carModel = carModel;
    }
    @NonNull
    @Override
    public AllBookingsAdapter.AllBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllBookingsAdapter.AllBookingViewHolder(
                AllBookingsTemplateBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AllBookingsAdapter.AllBookingViewHolder holder, int position) {
        holder.binding.service.setText(String.valueOf(service.get(position)));
        holder.binding.branch.setText(String.valueOf(branch.get(position)));
        holder.binding.firstname.setText(String.valueOf(firstname.get(position)));
        holder.binding.phone.setText(String.valueOf(phone.get(position)));
        holder.binding.time.setText(String.valueOf(time.get(position)));
        holder.binding.date.setText(String.valueOf(date.get(position)));
        holder.binding.carReg.setText(String.valueOf(carReg.get(position)));
        holder.binding.carModel.setText(String.valueOf(carModel.get(position)));

    }

    @Override
    public int getItemCount() {
        return phone.size();
    }

    public static class AllBookingViewHolder extends RecyclerView.ViewHolder{
        private final AllBookingsTemplateBinding binding;

        public AllBookingViewHolder(AllBookingsTemplateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
