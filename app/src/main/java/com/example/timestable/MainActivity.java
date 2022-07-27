package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mylistview;
    ArrayList<String> tables;
    public void generatenumbers(int timestablenumber)
    {
        tables=new ArrayList<String>();
        for(int j=1;j<=100;j++)
        {
            tables.add(Integer.toString(j*timestablenumber));
        }
        ArrayAdapter<String> arrayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tables);
        mylistview.setAdapter(arrayadapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylistview=(ListView)findViewById(R.id.listview);
        final SeekBar numberbar=(SeekBar)findViewById(R.id.numberbar);
        int max=101;
        int start=11;
        numberbar.setMax(max);
        numberbar.setProgress(start);
        generatenumbers(start);
        numberbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Number Bar Tapped!",Integer.toString(i));
                final int timestablenumber;
                int min=1;
                if(i<min)
                {
                    timestablenumber=min;
                    numberbar.setProgress(min);
                }
                else
                {
                    timestablenumber=i;
                }
                generatenumbers(timestablenumber);
                mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.i("Number Selected",tables.get(i).toString());
                        Toast.makeText(getApplicationContext(),Integer.toString(timestablenumber)+" X "+Integer.toString(i+1)+" = "+tables.get(i).toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}