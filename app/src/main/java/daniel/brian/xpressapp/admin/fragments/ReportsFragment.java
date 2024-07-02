package daniel.brian.xpressapp.admin.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.admin.db.EmployeeDB;
import daniel.brian.xpressapp.admin.db.TaskAssignmentDB;
import daniel.brian.xpressapp.auth.db.AuthenticationDB;
import daniel.brian.xpressapp.customer.db.BookingDB;
import daniel.brian.xpressapp.databinding.FragmentReportsBinding;
import daniel.brian.xpressapp.employee.db.CompletedTasksDB;


public class ReportsFragment extends Fragment {
    FragmentReportsBinding binding;
    EmployeeDB employeeDB;
    CompletedTasksDB completedTasksDB;
    BookingDB bookingDB;
    AuthenticationDB authenticationDB;
    TaskAssignmentDB taskAssignmentDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReportsBinding.inflate(getLayoutInflater());

        employeeDB = new EmployeeDB(this.getContext());
        completedTasksDB = new CompletedTasksDB(this.getContext());
        bookingDB = new BookingDB(this.getContext());
        authenticationDB = new AuthenticationDB(this.getContext());
        taskAssignmentDB = new TaskAssignmentDB(this.getContext());

        int employees = employeeDB.getAllEmployees();
        binding.employeeCount.setText(String.valueOf(employees));
        
        int revenues = completedTasksDB.getTotalCost();
        binding.revenueCount.setText(String.valueOf(revenues));

        int completedBookings = completedTasksDB.getAllBookingsCount();
        binding.bookingsCount.setText(String.valueOf(completedBookings));

        int totalBookings = bookingDB.getAllAppointmentsCount();
        binding.completedBookingsCount.setText(String.valueOf(totalBookings));

        int completedTasks = completedTasksDB.getAllBookingsCount();
        binding.assignedTasksCount.setText(String.valueOf(completedTasks));

        int users = authenticationDB.getAllUsers();
        binding.registeredUserCount.setText(String.valueOf(users));

        return binding.getRoot();
    }
}