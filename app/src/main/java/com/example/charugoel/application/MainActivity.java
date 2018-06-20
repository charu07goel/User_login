package com.example.charugoel.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Global uname = Global.getInstance();
    Button sign_up;
    TextView sign_in;
    EditText First_name,Last_name, Username, Password, Age;
    String first_name = null,last_name = null, username = null, password = null, age= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_in = (TextView) findViewById(R.id.sign_in);
        First_name = (EditText)findViewById(R.id.first_name);
        Last_name = (EditText)findViewById(R.id.last_name);
        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Age = (EditText)findViewById(R.id.age);
        sign_up = (Button)findViewById(R.id.sign_up);
        final Context ctx = this;

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_name = First_name.getText().toString().trim();
                last_name = Last_name.getText().toString().trim();
                username = Username.getText().toString().trim();
                password = Password.getText().toString().trim();
                age = Age.getText().toString().trim();

                /*DatabaseOperations dop = new DatabaseOperations(getBaseContext());
                Cursor CR = dop.getInformation(dop);
                CR.moveToFirst();
                boolean login_status = false;

                do{
                    if(username.equals(CR.getString(1))){
                        login_status = true;
                    }

                    else if(CR.getString(1).equals("")){
                        login_status = false;
                    }

                }while(CR.moveToNext());


                if(login_status ) {
                    Toast.makeText(getBaseContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
                    Username.setText("");
                    Password.setText("");
                }

                    else {*/

                        if (first_name.equals("") || last_name.equals("") || username.equals("") || password.equals("") || age.equals("")) {
                            Toast.makeText(getBaseContext(), "Fields Incomplete...", Toast.LENGTH_SHORT).show();
                            First_name.setText(First_name.getText());
                            Last_name.setText(Last_name.getText());
                            Username.setText(Username.getText());
                            Password.setText(Password.getText());
                            Age.setText(Age.getText());
                        } else if (password.length() < 6 && password.length() != 0) {
                            Toast.makeText(getBaseContext(), "Password Too Short - Minimum Length is 6", Toast.LENGTH_SHORT).show();
                        }

                        else if(exists(username)){
                            Toast.makeText(getBaseContext(), "Username Exists", Toast.LENGTH_SHORT).show();
                            Username.setText("");
                            Password.setText("");
                        }
                        else {
                            DatabaseOperations DB = new DatabaseOperations(ctx);
                            DB.putInformation(DB, first_name, last_name, username, password, age);

                            DB.insertInfo(DB, username, "50");

                            Toast.makeText(getBaseContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            First_name.setText("");
                            Last_name.setText("");
                            Username.setText("");
                            Password.setText("");
                            Age.setText("");
                            // finish();
                        }


            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignUp signup_frag = new SignUp();
                fragmentTransaction.add(R.id.main_act , signup_frag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    private boolean exists(String uname){

        DatabaseOperations dop = new DatabaseOperations(this);
        Cursor CR = dop.getInformation(dop);
        CR.moveToFirst();
        boolean login_status = false;
        String First_name = "";

        do{
            if(username.equals(CR.getString(1))){
                login_status = true;
            }

        }while(CR.moveToNext());

        return login_status;
    }
}
