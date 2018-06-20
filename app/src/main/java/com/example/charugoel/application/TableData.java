package com.example.charugoel.application;

import android.provider.BaseColumns;

/**
 * Created by Charu Goel on 19-06-2018.
 */

public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String First_name = "first_name";
        public static final String Last_name = "last_name";
        public static final String Username = "username";
        public static final String Password = "password";
        public static final String Age = "age";
        public static final String DATABASE_NAME = "user_info";
        public static final String TABLE_NAME = "reg_info";

    }


}
