package com.example.foxiczek.fileworks;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "Stairs2");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    FileOutputStream fis;
Button check_storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_storage = (Button) findViewById(R.id.button_check);

        check_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateandTime = sdf.format(new Date());
                File filename = new File(folder, currentDateandTime + ".txt");
                if(checkExtStorage()){
                    try {
                        if(!filename.exists()) {
                            filename.createNewFile();
                        }
                        fis = new FileOutputStream(filename);
                        OutputStreamWriter output = new OutputStreamWriter(fis);
                        output.write("TESTING ONLY \n" + currentDateandTime);
                        output.close();

                        fis.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private boolean checkExtStorage(){
        if(folder.exists()){
            System.err.println(folder.toString() + " IS OK");
            return true;
        }else{
            System.err.println(folder.toString() + " ERROR");
            folder.mkdir();
            if(folder.exists()){
                return true;
            }else{
                System.err.println(folder.toString() + " ERROR");
                return false;
            }

        }

    }
}
