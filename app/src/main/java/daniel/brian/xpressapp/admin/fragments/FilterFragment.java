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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.admin.adapters.FilteredDetailsAdapter;
import daniel.brian.xpressapp.databinding.FragmentFilterBinding;
import daniel.brian.xpressapp.employee.db.CompletedTasksDB;


public class FilterFragment extends Fragment {
    FragmentFilterBinding binding;
    CompletedTasksDB db;
    FilteredDetailsAdapter detailsAdapter;
    RecyclerView recyclerView;
    ArrayList<String> employeeName, carReg, carModel, product, service, totalAmount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(getLayoutInflater());

        setServiceListAdapter();

        db = new CompletedTasksDB(this.getContext());
        employeeName = new ArrayList<>();
        carReg = new ArrayList<>();
        carModel = new ArrayList<>();
        product = new ArrayList<>();
        service = new ArrayList<>();
        totalAmount = new ArrayList<>();

        recyclerView = binding.filterRV;
        detailsAdapter = new FilteredDetailsAdapter(this.getContext(), employeeName, carReg, carModel, product, service, totalAmount);
        recyclerView.setAdapter(detailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        binding.filter.setOnClickListener(v -> {
            String firstname = Objects.requireNonNull(binding.employeeName.getText()).toString();
            String serviceDone = binding.Service.getText().toString();

            displayData(firstname, serviceDone);
        });

        return binding.getRoot();
    }

    private void setServiceListAdapter() {
        String[] allServices = getResources().getStringArray(R.array.allServices);
        ArrayAdapter<String>  servicesAdapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item,allServices);
        binding.Service.setAdapter(servicesAdapter);
    }

    private void displayData(String firstname, String serviceDone) {
        Cursor cursor = db.filterByNameOrService(firstname, serviceDone);
        if (cursor.getCount() == 0) {
            Toast.makeText(this.getContext(),"No such Data",Toast.LENGTH_SHORT).show();
        } else {
            employeeName.clear();
            carReg.clear();
            carModel.clear();
            product.clear();
            service.clear();
            totalAmount.clear();

            while (cursor.moveToNext()) {
                service.add(cursor.getString(0));
                carReg.add(cursor.getString(1));
                carModel.add(cursor.getString(2));
                product.add(cursor.getString(3));
                service.add(cursor.getString(4));
                totalAmount.add(cursor.getString(5));

            }
        }


    }
}