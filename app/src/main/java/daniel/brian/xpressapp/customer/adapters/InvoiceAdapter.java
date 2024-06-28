package daniel.brian.xpressapp.customer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.xpressapp.databinding.InvoiceTemplateBinding;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {
    private ArrayList owner,phone,carReg,carModel,serviceDone,date,servedBy,totalAmount;
    private Context context;

    public InvoiceAdapter(Context context, ArrayList owner, ArrayList phone, ArrayList carReg, ArrayList carModel, ArrayList serviceDone,ArrayList date, ArrayList servedBy, ArrayList totalAmount) {
        this.context = context;
        this.owner = owner;
        this.phone = phone;
        this.carReg = carReg;
        this.carModel = carModel;
        this.serviceDone = serviceDone;
        this.date = date;
        this.servedBy = servedBy;
        this.totalAmount = totalAmount;
    }

    @NonNull
    @Override
    public InvoiceAdapter.InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvoiceViewHolder(
                InvoiceTemplateBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.InvoiceViewHolder holder, int position) {
        holder.binding.completedOwner.setText(String.valueOf(owner.get(position)));
        holder.binding.ownerPhone.setText(String.valueOf(phone.get(position)));
        holder.binding.completedCarRegTask.setText(String.valueOf(carReg.get(position)));
        holder.binding.completedCarModelTask.setText(String.valueOf(carModel.get(position)));
        holder.binding.completedServiceDone.setText(String.valueOf(serviceDone.get(position)));
        holder.binding.completedDate.setText(String.valueOf(date.get(position)));
        holder.binding.completedTaskServedBy.setText(String.valueOf(servedBy.get(position)));
        holder.binding.totalAmountUsed.setText(String.valueOf(totalAmount.get(position)));
    }

    @Override
    public int getItemCount() {
        return owner.size();
    }

    public static  class InvoiceViewHolder extends RecyclerView.ViewHolder{
        private final InvoiceTemplateBinding binding;

        public InvoiceViewHolder(InvoiceTemplateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
