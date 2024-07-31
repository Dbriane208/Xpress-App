package daniel.brian.xpressapp.customer.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import daniel.brian.xpressapp.admin.db.InvoiceDB;
import daniel.brian.xpressapp.customer.adapters.InvoiceAdapter;
import daniel.brian.xpressapp.databinding.FragmentInvoiceBinding;
import daniel.brian.xpressapp.employee.db.CompletedTasksDB;
import daniel.brian.xpressapp.payments.MpesaPaymentActivity;

public class InvoiceFragment extends Fragment {
    FragmentInvoiceBinding binding;
    RecyclerView recyclerView;
    InvoiceDB db;
    CompletedTasksDB completedTasksDB;
    InvoiceAdapter invoiceAdapter;
    ArrayList<String> owner,phone,carReg,carModel,serviceDone,date,servedBy,totalAmount;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInvoiceBinding.inflate(getLayoutInflater());

        db = new InvoiceDB(this.getContext());
        completedTasksDB = new CompletedTasksDB(this.getContext());
        owner = new ArrayList<>();
        phone = new ArrayList<>();
        carReg = new ArrayList<>();
        carModel = new ArrayList<>();
        serviceDone = new ArrayList<>();
        date = new ArrayList<>();
        servedBy = new ArrayList<>();
        totalAmount = new ArrayList<>();

        int total = completedTasksDB.getTotalCost();
        binding.totalAmount.setText("Ksh " + total);

        binding.checkOut.setOnClickListener(v -> {
            String phoneNumber = Objects.requireNonNull(binding.phoneNumber.getText()).toString();

            Intent intent = new Intent(this.getContext(), MpesaPaymentActivity.class);
            intent.putExtra("price",total);
            intent.putExtra("phone",phoneNumber);
            startActivity(intent);
        });

        recyclerView = binding.invoiceRV;
        invoiceAdapter = new InvoiceAdapter(this.getContext(),owner,phone,carReg,carModel,serviceDone,date,servedBy,totalAmount);
        recyclerView.setAdapter(invoiceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        displayData();

        return binding.getRoot();
    }

    private void displayData() {
        Cursor cursor = db.getAllInvoices();
        if(cursor.getCount() == 0){
            Toast.makeText(this.getContext(),"Empty Invoices",Toast.LENGTH_SHORT).show();
        }else{
            owner.clear();
            phone.clear();
            carReg.clear();
            carModel.clear();
            serviceDone.clear();
            date.clear();
            servedBy.clear();
            totalAmount.clear();

            while(cursor.moveToNext()){
                owner.add(cursor.getString(0));
                phone.add(cursor.getString(1));
                carReg.add(cursor.getString(2));
                carModel.add(cursor.getString(3));
                serviceDone.add(cursor.getString(4));
                date.add(cursor.getString(5));
                servedBy.add(cursor.getString(6));
                totalAmount.add(cursor.getString(7));

            }
        }
    }
}