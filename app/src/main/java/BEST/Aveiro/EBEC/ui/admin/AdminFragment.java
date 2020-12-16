package BEST.Aveiro.EBEC.ui.admin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import BEST.Aveiro.EBEC.R;

public class AdminFragment extends Fragment {

    private AdminViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.admin_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        return root;
    }




}