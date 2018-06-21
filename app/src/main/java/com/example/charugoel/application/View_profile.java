package com.example.charugoel.application;


import android.Manifest;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class View_profile extends Fragment {

    Global uname = Global.getInstance();
    TextView name,age;
    Integer pick = -1;
    ImageView pic,pic1,pic2,pic3,pic4;
    FloatingActionButton fab,fab1,fab2,fab3,fab4;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private Uri ImageCaptureUri;


    public View_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);



        final String[] items = new String[] {"Take Photo", "Choose from Galley"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items );
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment.getExternalStorageDirectory(), "tap_avtar"+ String.valueOf(System.currentTimeMillis())+".jpg");
                    ImageCaptureUri = Uri.fromFile(file);
                    try{
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageCaptureUri);
                        intent.putExtra("return data",true);

                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    dialog.cancel();
                }else{
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Complete Action Using"),PICK_FROM_FILE);
                }
            }
        });

        final AlertDialog Dialog = builder.create();
        pic = (ImageView) view.findViewById(R.id.pic);
        pic1 = (ImageView) view.findViewById(R.id.pic1);
        pic2 = (ImageView) view.findViewById(R.id.pic2);
        pic3 = (ImageView) view.findViewById(R.id.pic3);
        pic4 = (ImageView) view.findViewById(R.id.pic4);
        name = (TextView) view.findViewById(R.id.name_profile);
        age = (TextView) view.findViewById(R.id.age_profile);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) view.findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick = 0;
                Dialog.show();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pick = 1;
                Dialog.show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick = 2;
                Dialog.show();

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick = 3;
                Dialog.show();
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick = 4;
                Dialog.show();
            }
        });



        DatabaseOperations dop = new DatabaseOperations(getActivity());
        Cursor CR = dop.getInformation(dop);
        CR.moveToFirst();
        String Username = "";
        String First_name = "";
        String Age = "";
        Username = uname.get();
        do{
            if(Username.equals(CR.getString(1))) {
                First_name = CR.getString(0);
                Age = (CR.getString(3));
            }

        }while (CR.moveToNext());
        name.setText(First_name.toUpperCase());
        age.setText(Age);


        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        Bitmap bitmap = null;
        String path = "";
        if(requestCode == PICK_FROM_FILE){
            ImageCaptureUri = data.getData();
            path = getRealPathFromURI(ImageCaptureUri);
            if(path == null)
                path = ImageCaptureUri.getPath();
            if(path!= null)
                bitmap = BitmapFactory.decodeFile(path);
        }else if(requestCode == PICK_FROM_CAMERA){
            path = ImageCaptureUri.getPath();
            bitmap = BitmapFactory.decodeFile(path);
        }
        switch (pick){
            case 0:
                pic.setImageBitmap(bitmap);
            case 1:
                pic1.setImageBitmap(bitmap);
            case 2:
                pic2.setImageBitmap(bitmap);
            case 3:
                pic3.setImageBitmap(bitmap);
            case 4:
                pic4.setImageBitmap(bitmap);
            case -1:
                Toast.makeText(getActivity(),"Image Slot Not Selected", Toast.LENGTH_SHORT).show();
        }

    }

    public String getRealPathFromURI(Uri contentURI){
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentURI, proj, null, null, null);
        if(cursor == null)
            return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
