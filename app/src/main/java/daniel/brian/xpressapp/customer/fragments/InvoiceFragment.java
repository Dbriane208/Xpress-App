package daniel.brian.xpressapp.customer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import daniel.brian.xpressapp.databinding.FragmentInvoiceBinding;

public class InvoiceFragment extends Fragment {
    FragmentInvoiceBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInvoiceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}