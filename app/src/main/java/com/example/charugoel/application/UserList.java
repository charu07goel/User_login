package com.example.charugoel.application;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserList extends Fragment {

    Global uname = Global.getInstance();
    private int counter = 0;
    SQLiteDatabase sqLiteDatabase;
    DatabaseOperations dop,dop1,dop2;
    Cursor CR;
    ListAdapter listAdapter;
    ListView listView;
    public UserList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        listAdapter = new ListAdapter(getActivity(), R.layout.row_layout );
        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);
        dop = new DatabaseOperations(getActivity());
        sqLiteDatabase = dop.getReadableDatabase();
        CR = dop.getInformation(dop);
        String username = "", first_name = "", last_name = "";
        if(CR.moveToFirst()){

            do{
                if(!(CR.getString(1).equals(uname.get()))) {

                    first_name = CR.getString(0);
                    username = CR.getString(1);
                    last_name = CR.getString(4);

                    UserProvider userProvider = new UserProvider(username, first_name, last_name);
                    listAdapter.add(userProvider);
                }

            }while (CR.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.row_name);
                final TextView list_uname = (TextView) view.findViewById(R.id.row_username);
                View mview = getActivity().getLayoutInflater().inflate(R.layout.dialog_wallet, null);
                View view1 = getActivity().getLayoutInflater().inflate(R.layout.fragment_wallet__profile,null);
                final EditText amount = (EditText) view.findViewById(R.id.amt);
                final TextView amt = (TextView) view1.findViewById(R.id.amount);
                ImageView imageView = (ImageView) mview.findViewById(R.id.coin);
                final EditText editText = (EditText) mview.findViewById(R.id.amt);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            counter++;
                            editText.setText(Integer.toString(counter));
                    }
                });
                String name = textView.getText().toString();
                new AlertDialog.Builder(getActivity())
                        .setMessage("Select amount to transfer to "+ name)
                        .setView(mview)
                        .setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dop1 = new DatabaseOperations(getActivity());
                                CR = dop1.fetch(dop1);
                                CR.moveToFirst();
                                String a = "";
                                do {

                                    if ((CR.getString(0).equals(uname.get()))) {
                                        a = CR.getString(1);
                                    }

                                } while (CR.moveToNext());

                                if (Integer.valueOf(a) != null) {

                                    if (Integer.valueOf(a) >= counter) {
                                        Integer new_amt = Integer.valueOf(a) - counter;
                                        dop1.update(dop1, uname.get(), a, new_amt.toString());
                                        String x = new_amt.toString();

                                        String name2 = list_uname.getText().toString();
                                        String a2 = "";
                                        dop2 = new DatabaseOperations(getActivity());
                                        CR = dop2.fetch(dop2);
                                        CR.moveToFirst();
                                        do {
                                            if ((CR.getString(0).equals(name2))) {
                                                a2 = CR.getString(1);
                                            }

                                        } while (CR.moveToNext());

                                        Integer new_amt2 = Integer.valueOf(a2) + counter;
                                        dop2.update(dop2, name2, a2, new_amt2.toString());
                                        amt.setText("₹ "+x);
                                        Toast.makeText(getActivity(), "Transfer Successful...", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getActivity(), "Not Enough Money", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                counter = 0;
                            }
                        })
                        .show();
            }
        });

        return view ;
    }

}
