package daniel.brian.xpressapp.admin.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.admin.adapters.CompletedBookingsAdapter;
import daniel.brian.xpressapp.databinding.FragmentCompletedBookingsBinding;
import daniel.brian.xpressapp.employee.db.CompletedTasksDB;


public class CompletedBookingsFragment extends Fragment {
    FragmentCompletedBookingsBinding binding;
    CompletedTasksDB db;
    RecyclerView recyclerView;
    CompletedBookingsAdapter bookingsAdapter;
    private ArrayList<String> employee,carReg,carModel,product,service,totalAmount;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletedBookingsBinding.inflate(getLayoutInflater());

        db = new CompletedTasksDB(this.getContext());
        employee = new ArrayList<>();
        carReg = new ArrayList<>();
        carModel = new ArrayList<>();
        product = new ArrayList<>();
        service = new ArrayList<>();
        totalAmount = new ArrayList<>();

        recyclerView = binding.completedBookingsRV;
        bookingsAdapter = new CompletedBookingsAdapter(this.getContext(),employee,carReg,carModel,product,service,totalAmount);
        recyclerView.setAdapter(bookingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        displayData();

        return binding.getRoot();
    }

    private void displayData() {
        Cursor cursor = db.getAllBookings();
        if(cursor.getCount() == 0){
            Snackbar.make(requireView(),"No Bookings Available",Snackbar.LENGTH_SHORT).show();
        }else{
            employee.clear();
            carReg.clear();
            carModel.clear();
            product.clear();
            service.clear();
            totalAmount.clear();

            while(cursor.moveToNext()){
                employee.add(cursor.getString(0));
                carReg.add(cursor.getString(1));
                carModel.add(cursor.getString(2));
                product.add(cursor.getString(3));
                service.add(cursor.getString(4));
                totalAmount.add(cursor.getString(5));
            }
        }
    }
}