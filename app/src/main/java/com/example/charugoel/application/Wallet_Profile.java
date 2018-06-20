package com.example.charugoel.application;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Wallet_Profile extends Fragment {


    Global uname = Global.getInstance();
    TextView amt;
    public Wallet_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wallet__profile, container, false);

        amt = (TextView) view.findViewById(R.id.amount);


        DatabaseOperations dop = new DatabaseOperations(getActivity());
        Cursor CR = dop.fetch(dop);
        CR.moveToFirst();
        String Amount = "";
        do{

            if(uname.equals(CR.getString(0))){
                Amount = CR.getColumnName(1);
            }

        }while(CR.moveToNext());

        amt.setText(Amount);



        return view;
    }

}
