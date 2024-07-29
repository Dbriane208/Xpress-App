package daniel.brian.xpressapp.admin.fragments;

import static daniel.brian.xpressapp.payments.utils.Utils.validatePhoneNumber;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import daniel.brian.xpressapp.admin.db.InvoiceDB;
import daniel.brian.xpressapp.databinding.FragmentCustomerInvoiceBinding;

public class CustomerInvoiceFragment extends Fragment {
    FragmentCustomerInvoiceBinding binding;
    InvoiceDB db;
    private String selectedDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerInvoiceBinding.inflate(getLayoutInflater());

        db = new InvoiceDB(this.getContext());

        binding.invoiceDate.setOnClickListener(v -> showDatePickerDialog());

        binding.invoiceSave.setOnClickListener(v -> {
            String owner = Objects.requireNonNull(binding.invoiceOwner.getText()).toString();
            String phone = Objects.requireNonNull(binding.invoicePhone.getText()).toString();
            String carReg = Objects.requireNonNull(binding.invoiceCarReg.getText()).toString();
            String carModel = Objects.requireNonNull(binding.invoiceCarModel.getText()).toString();
            String serviceDone = Objects.requireNonNull(binding.invoiceServiceDone.getText()).toString();
            String servedBy = Objects.requireNonNull(binding.invoiceServedBy.getText()).toString();
            String totalAmount = Objects.requireNonNull(binding.invoiceTotalCost.getText()).toString();

            if(owner.isEmpty() || phone.isEmpty() || carReg.isEmpty() || carModel.isEmpty() || serviceDone.isEmpty() || selectedDate.isEmpty() || selectedDate == null || servedBy.isEmpty() || totalAmount.isEmpty()){
                if(!phone.startsWith("07") || !phone.startsWith("01") && !(phone.length() == 10)) {
                    Snackbar.make(requireView(), "Please Enter valid phone number", Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(requireView(), "Please enter all Fields", Snackbar.LENGTH_SHORT).show();
                }
            }else{
                boolean createInvoice = db.createInvoice(owner,validatePhoneNumber(phone),carReg,carModel,serviceDone,selectedDate,servedBy,totalAmount);
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

    private void showDatePickerDialog() {

        Calendar initialDate = Calendar.getInstance();

        // Construct the dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.getContext(), (view, year, month, dayOfMonth) -> {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
            selectedDate = simpleDateFormat.format(date.getTime());
            binding.invoiceDate.setText(selectedDate);
        }, initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Show the dialog
        datePickerDialog.show();
    }
}