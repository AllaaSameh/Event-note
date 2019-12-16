package com.example.sqlitetestrcc61;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todoclass> {


    public TodoAdapter(@NonNull Context context,  @NonNull List<Todoclass> objects) {
        super(context, R.layout.row_desgin, objects);
        // make layout static in code to not set it in parameter
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_desgin ,parent , false);

        TextView tv_event = convertView.findViewById(R.id.tv_event);
        final CheckBox ch_done = convertView.findViewById(R.id.checkBox);
        ImageButton remove = convertView.findViewById(R.id.btn_remove);

        tv_event.setText(getItem(position).getEvent());

        // mean if done = 1
        if(getItem(position).getDone()==1){
            ch_done.setChecked(true);
        }else{
            ch_done.setChecked(false);
        }

        final SqliteDataBase db = new SqliteDataBase(getContext());
        // for checkbox
        ch_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int r ;
                if(ch_done.isChecked()){
                    r = db.UpdateData(1 , getItem(position).getId());
                }else {
                    r =db.UpdateData(0 , getItem(position).getId());
                }

                if(r==1){
                    Toast.makeText(getContext(),"data updated ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"data not updated ",Toast.LENGTH_SHORT).show();

                }
            }
        });

        //for remove data
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = db.Remove(getItem(position).getId());
                if(r==1){
                    Toast.makeText(getContext(),"data removed ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"data not removed ",Toast.LENGTH_SHORT).show();

                }
            }
        });




       return convertView ;

    }
}
