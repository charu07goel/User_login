package com.example.charugoel.application;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {

    Global uname = Global.getInstance();
    ImageView log_in;
    EditText Username_login, Pass_login;
    String username , password;

    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        log_in = (ImageView) view.findViewById(R.id.login);
        Username_login = (EditText) view.findViewById(R.id.username_login);
        Pass_login = (EditText) view.findViewById(R.id.pass_login);


        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = Username_login.getText().toString().trim();
                password = Pass_login.getText().toString().trim();
                DatabaseOperations dop = new DatabaseOperations(getActivity());
                Cursor CR = dop.getInformation(dop);
                CR.moveToFirst();
                boolean login_status = false;
                String First_name = "";

                do{
                    if(username.equals(CR.getString(1)) && (password.equals(CR.getString(2)))){
                        login_status = true;
                        uname.set(username);
                        First_name = CR.getString(0);

                    }

                }while(CR.moveToNext());
                if(login_status){
                    Toast.makeText(getActivity(), "Login Successful...\n Welcome "+First_name,Toast.LENGTH_SHORT).show();

                    Username_login.setText("");
                    Pass_login.setText("");

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Profile profile = new Profile();
                    fragmentTransaction.add(R.id.main_act , profile);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                }
                else{
                    Pass_login.setText("");
                    Toast.makeText(getActivity(), "Login Failed...",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;

    }

}
