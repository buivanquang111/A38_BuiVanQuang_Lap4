package com.example.a38_buivanquang_btlap4_day4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class Main2Activity extends AppCompatActivity {

    Spinner spwork;
    Button btntune;
    EditText ettitle, etdes,ettag,etweek;
    TextView tvtag,tvweek,tvhour,tvdate;
    ArrayList<String> type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("Create New Note");
        spwork=findViewById(R.id.spwork);
        btntune=findViewById(R.id.btntune);
        ettitle=findViewById(R.id.ettitle);
        etdes=findViewById(R.id.etdes);
        ettag=findViewById(R.id.ettag);
        etweek=findViewById(R.id.etweek);
        tvhour=findViewById(R.id.tvhour);
        tvdate=findViewById(R.id.tvdate);
        tvtag=findViewById(R.id.tvtag);
        tvweek=findViewById(R.id.tvweek);


        type= new ArrayList<>();
        type.add("work");
        type.add("phone");
        type.add("Home");
       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1,type);
       spwork.setAdapter(arrayAdapter);

       final Calendar calendar=Calendar.getInstance();
       final int nam=calendar.get(Calendar.YEAR);
       final int thang=calendar.get(Calendar.MONTH);
       final int ngay=calendar.get(Calendar.DAY_OF_MONTH);
       final int gio=calendar.get(Calendar.HOUR_OF_DAY);
       final int phut=calendar.get(Calendar.MINUTE);
        //set hour
       tvhour.setText(String.valueOf(gio)+":"+String.valueOf(phut));
       tvhour.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              TimePickerDialog timePickerDialog=new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                  @Override
                  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh::mm::ss");
                      calendar.set(0,0,0,hourOfDay,minute);
                      tvhour.setText(simpleDateFormat.format(calendar.getTime()));
                  }
              },gio ,phut,true);
              timePickerDialog.show();
           }
       });
        //set date
        tvdate.setText(String.valueOf(ngay)+"/"+String.valueOf(thang)+"/"+String.valueOf(nam));
        tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy");
                        calendar.set(year,month,dayOfMonth);
                        tvdate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },ngay,thang,nam);
                datePickerDialog.show();
            }
        });



       tvtag.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final int position=0;
               final String[] tag={"family","game","android","VTC","friend"};
               final boolean[] ischecks={false,false,true,false,true};

               AlertDialog alertDialog=new AlertDialog.Builder(Main2Activity.this)
                       .setTitle("Set Tag")
                        .setMultiChoiceItems(tag, ischecks, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
//                                int position=i;
//                                boolean b1=isChecked;
//                                ischecks[i]=isChecked;
//                                ettag.setText(tag[position]);


                            }
                        })
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int i) {

                           }
                       })
                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int i) {

                           }
                       })
                       .create();
               alertDialog.show();
           }
       });

       tvweek.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String[] week={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
               boolean[] ischecks={true,true,false,false,false,true,false};
               AlertDialog alertDialog=new AlertDialog.Builder(Main2Activity.this)
                       .setTitle("Select week")
                       .setMultiChoiceItems(week, ischecks, new DialogInterface.OnMultiChoiceClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                           }
                       })
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       })
                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       })
                       .create();
               alertDialog.show();
           }
       });

       btntune.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu=new PopupMenu(getBaseContext(),v);
               popupMenu.getMenuInflater().inflate(R.menu.new_contact_menu,popupMenu.getMenu());

               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.itemfile:
                               Toast.makeText(getBaseContext(),"From File",Toast.LENGTH_LONG).show();
                               break;
                           case R.id.itemdefauls:
                               String[] from={"Nexus Tune","Winphone tune","Peep tune","Nokia Tune","Etc"};
                               boolean[] ischecks={false,false,true,false,false};
                               AlertDialog alertDialog=new AlertDialog.Builder(Main2Activity.this)
                                       .setTitle("select form defaults")
                                       .setSingleChoiceItems(from, 1, new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {

                                           }
                                       })
                                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(),"OK from dedaults",Toast.LENGTH_LONG).show();
                                           }
                                       })
                                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(),"Cancel form defaults",Toast.LENGTH_LONG).show();
                                           }
                                       })
                                       .create();
                               alertDialog.show();

                       }

                       return false;
                   }
               });
                popupMenu.show();
           }
       });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_contact,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemcancel:
                Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_LONG).show();
                break;

            case R.id.itemok:
                Toast.makeText(getBaseContext(),"OK",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
