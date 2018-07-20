package com.swathi.dbaplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String names[];
    ArrayList<Moduleclass> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        Database database=new Database(this);
        database.addDetails("Swathi",911917673);
        database.addDetails("Ramya",919761111);
        arrayList=database.retriveData();
        names=new String[arrayList.size()];
        list = (ListView) findViewById(R.id.listview);
        AdaptorNew adapter = new AdaptorNew(MainActivity.this, 0);
        for (int i = 0; i < arrayList.size(); i++) {
            Log.e("Arraylist", arrayList.get(i).Name + " " + arrayList.get(i).phnum);
        }
        list.setAdapter(adapter);

    }

    class AdaptorNew extends ArrayAdapter {

        public AdaptorNew(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return arrayList.size();

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            convertView = inflater.inflate(R.layout.database, null);
            TextView Name, ph_num;
            Name= (TextView) convertView.findViewById(R.id.tex1);
            ph_num= (TextView) convertView.findViewById(R.id.tex2);
            Name.setText(arrayList.get(position).Name);
            ph_num.setText(arrayList.get(position).phnum);
            return convertView;
        }
    }
}
