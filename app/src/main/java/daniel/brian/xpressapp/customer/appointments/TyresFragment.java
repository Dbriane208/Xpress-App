package daniel.brian.xpressapp.customer.appointments;

import static daniel.brian.xpressapp.payments.utils.Utils.validatePhoneNumber;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.customer.db.BookingDB;
import daniel.brian.xpressapp.databinding.FragmentTyresBinding;

public class TyresFragment extends Fragment {
    FragmentTyresBinding binding;
    private String selectedDate;

    BookingDB db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTyresBinding.inflate(getLayoutInflater());

        db = new BookingDB(this.getContext());

        setUpListArrayAdapter();

        binding.dateTyres.setOnClickListener(v -> showDatePickerDialog());

        binding.tyresBook.setOnClickListener(v -> {
            String service = binding.tyreService.getText().toString();
            String branch = binding.tyreBranch.getText().toString();
            String firstname = Objects.requireNonNull(binding.firstNameT.getText()).toString();
            String phone = Objects.requireNonNull(binding.tyrePhone.getText()).toString();
            String time = binding.tyreTime.getText().toString();
            String carReg = Objects.requireNonNull(binding.carRegTyres.getText()).toString();
            String carModel = Objects.requireNonNull(binding.carModelTyres.getText()).toString();

            if(service.isEmpty() || branch.isEmpty() || firstname.isEmpty() || phone.isEmpty() || time.isEmpty() || selectedDate.isEmpty() || selectedDate == null || carReg.isEmpty() || carModel.isEmpty()){
                Snackbar.make(requireView(),"Please Enter all fields",Snackbar.LENGTH_SHORT).show();
            }else{
                boolean appointments = db.bookAppointment(service,branch,firstname,validatePhoneNumber(phone),time,selectedDate,carReg,carModel);
                if(appointments){
                    Snackbar.make(requireView(),"Appointment successfully booked!",Snackbar.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }else{
                    Snackbar.make(requireView(),"Booking failed!",Snackbar.LENGTH_SHORT).show();
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
            binding.dateTyres.setText(selectedDate);
        }, initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Show the dialog
        datePickerDialog.show();
    }

    private void setUpListArrayAdapter() {
        String[] tyres = getResources().getStringArray(R.array.tyreServices);
        ArrayAdapter<String> batteryAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, tyres);
        binding.tyreService.setAdapter(batteryAdapter);

        String[] branches = getResources().getStringArray(R.array.branches);
        ArrayAdapter<String> branchesAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, branches);
        binding.tyreBranch.setAdapter(branchesAdapter);

        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, time);
        binding.tyreTime.setAdapter(timeAdapter);
    }
}