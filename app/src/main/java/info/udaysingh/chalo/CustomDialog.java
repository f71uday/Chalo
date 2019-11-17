package info.udaysingh.chalo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import info.udaysingh.chalo.api.StopDataList;

public class CustomDialog extends Dialog implements DialogInterface.OnCancelListener {

    public Activity c;
    List<StopDataList> stopDataLists;
    List<String> stringList= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alarm_dialog);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Button button = (Button)findViewById(R.id.add_button);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.c, android.R.layout.simple_spinner_item, stringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public CustomDialog(Activity a, List<StopDataList> stopDataLists) {
        super(a);
        for(int i =0;i<stopDataLists.size();i++)
        {
            stringList.add(stopDataLists.get(i).getStopName());
        }
        this.c = a;
    }
    @Override
    public void onCancel(DialogInterface dialog) {

    }

}
