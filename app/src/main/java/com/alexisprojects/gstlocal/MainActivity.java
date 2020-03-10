package com.alexisprojects.gstlocal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText gstHours;
    private EditText localHours;
    private EditText localLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button random =  (Button) findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gstHours = (EditText) findViewById(R.id.gstHours);
                localHours = (EditText) findViewById(R.id.localHours);
                localLength = (EditText) findViewById(R.id.localLength);
                String checkEmpty = localLength.getText().toString();
                int localNum = 0;

                if(checkEmpty.matches("")){
                    Toast.makeText(getApplicationContext(), "Day length needs a value.", Toast.LENGTH_LONG).show();
                    return;
                }else if(Integer.parseInt(localLength.getText().toString()) > 2){
                    localNum = Integer.parseInt(localLength.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Day length needs to be greater than 2.", Toast.LENGTH_LONG).show();
                    return;
                }

                Random randNum = new Random();

                int randGST = randNum.nextInt(24) + 1;
                int randLocal = randNum.nextInt(localNum) + 1;

                gstHours.setText(randGST + ":00");
                localHours.setText(randLocal + ":00");

                changeColor(randGST,randLocal,localNum);
            }
        });

        Button addHour = (Button) findViewById(R.id.addHour);
        addHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gstHours = (EditText) findViewById(R.id.gstHours);
                localHours = (EditText) findViewById(R.id.localHours);
                localLength = (EditText) findViewById(R.id.localLength);
                String checkGST = gstHours.getText().toString();
                String checkLocal = localHours.getText().toString();
                String checkLength = localLength.getText().toString();

                int gstHoursAdd = 0;
                int locHoursAdd = 0;
                int locLength = 0;

                int localFour = 0;

                if(checkLength.matches("")){
                    Toast.makeText(getApplicationContext(), "Day length needs a value.", Toast.LENGTH_LONG).show();
                    return;
                }else if(checkGST.matches("") || checkLocal.matches("")){
                    Toast.makeText(getApplicationContext(), "GST and Local times cannot be empty.", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    String gstHoursSplit[] = gstHours.getText().toString().split(":");
                    String locHousSplit[] = localHours.getText().toString().split(":");
                    gstHoursAdd = Integer.parseInt(gstHoursSplit[0]);
                    locHoursAdd = Integer.parseInt(locHousSplit[0]);
                    locLength = Integer.parseInt(localLength.getText().toString());
                    localFour = locLength / 4;
                    if(gstHoursAdd > 24){
                        Toast.makeText(getApplicationContext(), "GST cannot be larger than 24.", Toast.LENGTH_LONG).show();
                        return;
                    }else if(locHoursAdd > locLength){
                        Toast.makeText(getApplicationContext(), "Local Time cannot be greater than hours of the day.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                if(gstHoursAdd == 24){
                    gstHoursAdd = 1;
                }else{
                    gstHoursAdd = gstHoursAdd + 1;
                }

                if(locHoursAdd == locLength){
                    locHoursAdd = 1;
                }else{
                    locHoursAdd = locHoursAdd + 1;
                }

                gstHours.setText(gstHoursAdd + ":00");
                localHours.setText(locHoursAdd + ":00");

                changeColor(gstHoursAdd, locHoursAdd, locLength);
            }
        });

        Button manual = (Button) findViewById(R.id.manual);
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gstHours = (EditText) findViewById(R.id.gstHours);
                localHours = (EditText) findViewById(R.id.localHours);
                localLength = (EditText) findViewById(R.id.localLength);
                String checkGST = gstHours.getText().toString();
                String checkLocal = localHours.getText().toString();
                String checkLength = localLength.getText().toString();

                int gstHoursAdd = 0;
                int locHoursAdd = 0;
                int locLength = 0;

                int localFour = 0;

                if(checkLength.matches("")){
                    Toast.makeText(getApplicationContext(), "Day length needs a value.", Toast.LENGTH_LONG).show();
                    return;
                }else if(checkGST.matches("") || checkLocal.matches("")) {
                    Toast.makeText(getApplicationContext(), "GST and Local times cannot be empty.", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    String gstHoursSplit[] = gstHours.getText().toString().split(":");
                    String locHousSplit[] = localHours.getText().toString().split(":");
                    gstHoursAdd = Integer.parseInt(gstHoursSplit[0]);
                    locHoursAdd = Integer.parseInt(locHousSplit[0]);
                    gstHoursAdd = Integer.parseInt(gstHours.getText().toString());
                    locHoursAdd = Integer.parseInt(localHours.getText().toString());
                    locLength = Integer.parseInt(localLength.getText().toString());
                    localFour = locLength / 4;

                    if(gstHoursAdd > 24){
                        Toast.makeText(getApplicationContext(), "GST cannot be larger than 24.", Toast.LENGTH_LONG).show();
                        return;
                    }else if(locHoursAdd > locLength){
                        Toast.makeText(getApplicationContext(), "Local Time cannot be greater than hours of the day.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                gstHours.setText(gstHoursAdd + ":00");
                localHours.setText(locHoursAdd + ":00");

                changeColor(gstHoursAdd, locHoursAdd, locLength);
            }
        });
    }

    public void changeColor(int gstChange, int locChange, int locTotal){
        int localFour = locTotal / 4;

        if(gstChange <= 5 || gstChange >= 20){
            gstHours.setBackgroundResource(R.color.night);
            gstHours.setTextColor(getResources().getColor(R.color.white));
        }else{
            gstHours.setBackgroundResource(R.color.day);
            gstHours.setTextColor(getResources().getColor(R.color.white));
        }

        if(locChange <= localFour || locChange >= locTotal - localFour){
            localHours.setBackgroundResource(R.color.night);
            localHours.setTextColor(getResources().getColor(R.color.white));
        }else{
            localHours.setBackgroundResource(R.color.day);
            localHours.setTextColor(getResources().getColor(R.color.white));
        }

    }
}
