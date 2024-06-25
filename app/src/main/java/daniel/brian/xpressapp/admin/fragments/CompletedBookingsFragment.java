package daniel.brian.xpressapp.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniel.brian.xpressapp.R;
import daniel.brian.xpressapp.databinding.FragmentCompletedBookingsBinding;


public class CompletedBookingsFragment extends Fragment {
    FragmentCompletedBookingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletedBookingsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}