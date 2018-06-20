package com.example.charugoel.application;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    Global uname = Global.getInstance();
    TextView name;
    TextView logout;
    ImageView profile, view_profile, wallet;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        name = (TextView) view.findViewById(R.id.name);
        profile = (ImageView) view.findViewById(R.id.profile_pic);
        logout = (TextView) view.findViewById(R.id.logout);
        view_profile = (ImageView) view.findViewById(R.id.View_Profile);
        wallet = (ImageView) view.findViewById(R.id.wallet);


        DatabaseOperations dop = new DatabaseOperations(getActivity());
        Cursor CR = dop.getInformation(dop);
        CR.moveToFirst();
        String Username = "";
        String First_name = "";
        Username = uname.get();
        do{
            if(Username.equals(CR.getString(1)))
                First_name = CR.getString(0);
        }while (CR.moveToNext());
        name.setText(First_name);

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Wallet_Profile wal = new Wallet_Profile();
                fragmentTransaction.add(R.id.profile_frame, wal);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                View_profile profile = new View_profile();
                fragmentTransaction.add(R.id.profile_frame , profile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                View_profile profile = new View_profile();
                fragmentTransaction.add(R.id.profile_frame ,profile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStackImmediate();
            }
        });


        return view;
    }

}
