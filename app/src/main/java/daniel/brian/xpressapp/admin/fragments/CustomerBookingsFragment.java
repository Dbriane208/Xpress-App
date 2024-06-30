package daniel.brian.xpressapp.admin.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import daniel.brian.xpressapp.admin.adapters.AllBookingsAdapter;
import daniel.brian.xpressapp.customer.db.BookingDB;
import daniel.brian.xpressapp.databinding.FragmentCustomerBookingsBinding;


public class CustomerBookingsFragment extends Fragment {
    FragmentCustomerBookingsBinding binding;
    AllBookingsAdapter bookingsAdapter;
    BookingDB db;
    RecyclerView recyclerView;
    ArrayList<String> service,branch,firstname,phone,time,date,carReg,carModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerBookingsBinding.inflate(getLayoutInflater());

        db = new BookingDB(this.getContext());
        service = new ArrayList<>();
        branch = new ArrayList<>();
        firstname = new ArrayList<>();
        phone = new ArrayList<>();
        time = new ArrayList<>();
        date = new ArrayList<>();
        carReg = new ArrayList<>();
        carModel = new ArrayList<>();

        recyclerView = binding.customerBookingsRV;
        bookingsAdapter = new AllBookingsAdapter(this.getContext(),service,branch,firstname,phone,time,date,carReg,carModel);
        recyclerView.setAdapter(bookingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        displayData();

        return binding.getRoot();
    }

    private void displayData() {
        Cursor cursor = db.getAllBookings();
        if(cursor.getCount() == 0){
            Snackbar.make(requireView(),"No Available Bookings",Snackbar.LENGTH_SHORT).show();
        }else{
            service.clear();
            branch.clear();
            firstname.clear();
            phone.clear();
            time.clear();
            date.clear();
            carReg.clear();
            carModel.clear();

            while(cursor.moveToNext()){
                service.add(cursor.getString(0));
                branch.add(cursor.getString(1));
                firstname.add(cursor.getString(2));
                phone.add(cursor.getString(3));
                time.add(cursor.getString(4));
                date.add(cursor.getString(5));
                carReg.add(cursor.getString(6));
                carModel.add(cursor.getString(7));
            }

        }
    }
}