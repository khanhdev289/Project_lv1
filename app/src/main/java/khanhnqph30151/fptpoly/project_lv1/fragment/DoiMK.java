package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khanhnqph30151.fptpoly.project_lv1.R;


public class DoiMK extends Fragment {


    public DoiMK() {
        // Required empty public constructor
    }


    public static DoiMK newInstance() {
        DoiMK fragment = new DoiMK();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doi_m_k, container, false);
    }
}