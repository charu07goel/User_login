package com.example.charugoel.application;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Wallet_Profile extends Fragment {


    Global uname = Global.getInstance();
    TextView amt;
    ImageView exchange;
    public Wallet_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wallet__profile, container, false);

        amt = (TextView) view.findViewById(R.id.amount);
        exchange = (ImageView) view.findViewById(R.id.exchange);


        DatabaseOperations dop = new DatabaseOperations(getActivity());
        Cursor CR = dop.fetch(dop);
        CR.moveToFirst();
        String Amount = "";
        String Username = uname.get();
        do{
            if(Username.equals(CR.getString(0))){
                Amount = CR.getString(1);
                break;
            }

        }while(CR.moveToNext());
        amt.setText(amt.getText()+" "+Amount);

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UserList profile = new UserList();
                fragmentTransaction.add(R.id.userlistDisp , profile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        return view;
    }


}
