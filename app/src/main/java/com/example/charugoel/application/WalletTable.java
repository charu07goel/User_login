package com.example.charugoel.application;

import android.provider.BaseColumns;

/**
 * Created by Charu Goel on 20-06-2018.
 */

public class WalletTable {

    public WalletTable(){

    }

    public static abstract class TableInfo implements BaseColumns {

        public static final String Username = "username";
        public static final String Wallet = "amount";
        public static final String DATABASE_NAME = "user_info";
        public static final String TABLE_NAME = "wallet_info";

    }

}
