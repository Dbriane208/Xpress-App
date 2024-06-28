package daniel.brian.xpressapp.admin.fragments;

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

import daniel.brian.xpressapp.admin.db.InvoiceDB;
import daniel.brian.xpressapp.databinding.FragmentCustomerInvoiceBinding;

public class CustomerInvoiceFragment extends Fragment {
    FragmentCustomerInvoiceBinding binding;
    InvoiceDB db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerInvoiceBinding.inflate(getLayoutInflater());

        db = new InvoiceDB(this.getContext());

        binding.invoiceSave.setOnClickListener(v -> {
            String owner = Objects.requireNonNull(binding.invoiceOwner.getText()).toString();
            String phone = Objects.requireNonNull(binding.invoicePhone.getText()).toString();
            String carReg = Objects.requireNonNull(binding.invoiceCarReg.getText()).toString();
            String carModel = Objects.requireNonNull(binding.invoiceCarModel.getText()).toString();
            String date = Objects.requireNonNull(binding.invoiceDate.getText()).toString();
            String serviceDone = Objects.requireNonNull(binding.invoiceServiceDone.getText()).toString();
            String servedBy = Objects.requireNonNull(binding.invoiceServedBy.getText()).toString();
            String totalAmount = Objects.requireNonNull(binding.invoiceTotalCost.getText()).toString();

            if(owner.isEmpty() || phone.isEmpty() || carReg.isEmpty() || carModel.isEmpty() || serviceDone.isEmpty() || date.isEmpty() || servedBy.isEmpty() || totalAmount.isEmpty()){
                if(!phone.startsWith("07") || !phone.startsWith("01") && !(phone.length() == 10)) {
                    Snackbar.make(requireView(), "Please Enter valid phone number", Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(requireView(), "Please enter all Fields", Snackbar.LENGTH_SHORT).show();
                }
            }else{
                boolean createInvoice = db.createInvoice(owner,phone,carReg,carModel,serviceDone,date,servedBy,totalAmount);
                if(createInvoice){
                    Snackbar.make(requireView(), "Invoice created successfully!", Snackbar.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }else{
                    Snackbar.make(requireView(), "Error creating the invoice", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}