package com.example.charugoel.application;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserList extends Fragment {

    Global uname = Global.getInstance();
    SQLiteDatabase sqLiteDatabase;
    DatabaseOperations dop;
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

        return view ;
    }

}
