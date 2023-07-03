package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khanhnqph30151.fptpoly.project_lv1.R;


public class Loai_Sp extends Fragment {



    public Loai_Sp() {
        // Required empty public constructor
    }

    public static Loai_Sp newInstance() {
        Loai_Sp fragment = new Loai_Sp();

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
        return inflater.inflate(R.layout.fragment_loai__sp, container, false);
    }
}