package daniel.brian.xpressapp.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import daniel.brian.xpressapp.databinding.FragmentCustomerInvoiceBinding;

public class CustomerInvoiceFragment extends Fragment {
    FragmentCustomerInvoiceBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomerInvoiceBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}