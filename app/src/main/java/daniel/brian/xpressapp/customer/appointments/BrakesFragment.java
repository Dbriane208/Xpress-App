package daniel.brian.xpressapp.customer.appointments;

import static daniel.brian.xpressapp.payments.utils.Utils.validatePhoneNumber;

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

import java.util.Objects;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.customer.db.BookingDB;
import daniel.brian.xpressapp.databinding.FragmentBrakesBinding;

public class BrakesFragment extends Fragment {
    FragmentBrakesBinding binding;
    BookingDB db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBrakesBinding.inflate(getLayoutInflater());

        db = new BookingDB(this.getContext());

        setUpListArrayAdapter();

        binding.brakesBook.setOnClickListener(v -> {
            String service = binding.brakesService.getText().toString();
            String branch = binding.brakesBranch.getText().toString();
            String firstname = Objects.requireNonNull(binding.firstNameB.getText()).toString();
            String phone = Objects.requireNonNull(binding.brakesPhone.getText()).toString();
            String time = binding.timeBrakes.getText().toString();
            String date = Objects.requireNonNull(binding.dateBrakes.getText()).toString();
            String carReg = Objects.requireNonNull(binding.carRegBrakes.getText()).toString();
            String carModel = Objects.requireNonNull(binding.carModelBrakes.getText()).toString();

            if(service.isEmpty() || branch.isEmpty() || firstname.isEmpty() || phone.isEmpty() || time.isEmpty() || date.isEmpty() || carReg.isEmpty() || carModel.isEmpty()){
                Snackbar.make(requireView(),"Please Enter all fields",Snackbar.LENGTH_SHORT).show();
            }else{
                boolean appointments = db.bookAppointment(service,branch,firstname,validatePhoneNumber(phone),time,date,carReg,carModel);
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

    private void setUpListArrayAdapter() {
        String[] brakes = getResources().getStringArray(R.array.brakeServices);
        ArrayAdapter<String> batteryAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, brakes);
        binding.brakesService.setAdapter(batteryAdapter);

        String[] branches = getResources().getStringArray(R.array.branches);
        ArrayAdapter<String> branchesAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, branches);
        binding.brakesBranch.setAdapter(branchesAdapter);


        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, time);
        binding.timeBrakes.setAdapter(timeAdapter);
    }
}