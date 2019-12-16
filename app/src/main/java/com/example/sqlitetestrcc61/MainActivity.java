package com.example.sqlitetestrcc61;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        ArrayList<Todoclass> list = Print();
        TodoAdapter adapter = new TodoAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);


    }

    public void Save(View view) {
        final Dialog d = new Dialog(MainActivity.this);
        d.setContentView(R.layout.custom_alertdialog);

        final EditText ed_event = d.findViewById(R.id.ed_event);
        Button btn_svae = d.findViewById(R.id.btn_sava);


        btn_svae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteDataBase db = new SqliteDataBase(MainActivity.this);
                long r = db.InsertData(ed_event.getText().toString());
                if(r== -1){
                    Toast.makeText(MainActivity.this,"data not saved ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"data saved ",Toast.LENGTH_SHORT).show();
                }

                d.dismiss();

            }
        });

        d.show();

    }

    public ArrayList<Todoclass> Print (){

        SqliteDataBase db = new SqliteDataBase(MainActivity.this);
        Cursor c = db.GetData();
        ArrayList<Todoclass> todoclasseslist = new ArrayList<>();

        // moveToNext() -> if get data move to next row , else exit
        while (c.moveToNext()){
            // create object from todoclass to get data from row into this object
            // add object from todoclass into arraylist
            //then send this arraylist to listview to view it
            Todoclass todoobject = new Todoclass();
            todoobject.setEvent(c.getString(0));// 0 -> event
            todoobject.setId(c.getInt(1)); // 1 -> id
            todoobject.setDone(c.getInt(2)); // 2 -> do
            todoclasseslist.add(todoobject);
        }

        return todoclasseslist;
    }
}
