package com.example.osvaldo.assingment1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {


    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_input, container, false);
        Button mybutton = myview.findViewById(R.id.submit);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAction(v);
            }
        });
        return myview;
    }

    private void submitAction(View v) {
        FragmentManager fragmentmanager = getChildFragmentManager();
        FragmentTransaction trans = fragmentmanager.beginTransaction();
        outputFragment output = new outputFragment();
        trans.add(R.id.resultout,  output);
        trans.commit();
    }

}
