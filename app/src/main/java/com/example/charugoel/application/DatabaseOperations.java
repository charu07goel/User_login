package com.example.charugoel.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Charu Goel on 19-06-2018.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;

    public String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS "+TableData.TableInfo.TABLE_NAME+"("+TableData.TableInfo.First_name+" TEXT,"+TableData.TableInfo.Last_name+" TEXT,"+TableData.TableInfo.Username+" TEXT PRIMARY KEY NOT NULL,"+TableData.TableInfo.Password+" TEXT,"+TableData.TableInfo.Age + " INTEGER);";
    public String CREATE_WALLET = "CREATE TABLE IF NOT EXISTS "+WalletTable.TableInfo.TABLE_NAME+"("+WalletTable.TableInfo.Username+" TEXT PRIMARY KEY NOT NULL,"+ WalletTable.TableInfo.Wallet+" INTEGER DEFAULT 50);";


    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","One row inserted");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
        sdb.execSQL(CREATE_WALLET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int i, int i1) {

        sdb.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TABLE_NAME);
        sdb.execSQL("DROP TABLE IF EXISTS " + WalletTable.TableInfo.TABLE_NAME);
         onCreate(sdb);
    }


    public void putInformation(DatabaseOperations dop, String first_name, String last_name, String username, String password, String age){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.First_name, first_name);
        cv.put(TableData.TableInfo.Last_name, last_name);
        cv.put(TableData.TableInfo.Username, username);
        cv.put(TableData.TableInfo.Password, password);
        cv.put(TableData.TableInfo.Age, age);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);

    }

    public void insertInfo(DatabaseOperations dop , String username, String wallet){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WalletTable.TableInfo.Wallet, wallet);
        cv.put(WalletTable.TableInfo.Username, username);
        long k = SQ.insert(WalletTable.TableInfo.TABLE_NAME, null, cv);

    }

    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.First_name, TableData.TableInfo.Username, TableData.TableInfo.Password, TableData.TableInfo.Age};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        if(CR!=null)
            CR.moveToFirst();
        return CR;
    }

    public Cursor fetch(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {WalletTable.TableInfo.Username, WalletTable.TableInfo.Wallet};
        Cursor CR = SQ.query(WalletTable.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        if(CR != null)
            CR.moveToFirst();

        return CR;
    }
}
