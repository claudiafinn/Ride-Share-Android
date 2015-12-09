package ridesharev3.examples.android.ait.ridesharev3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by claudiafinn on 12/2/14.
 */
public class CreateRide extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);

        final Spinner spin1 = (Spinner) findViewById(R.id.startingLoc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);

        final Spinner spin2 = (Spinner) findViewById(R.id.destination);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);

        final EditText et1 = (EditText) findViewById(R.id.etdescrption);
        final EditText numSpaces = (EditText)findViewById(R.id.numSpaces);
        final DatePicker dt1 = (DatePicker) findViewById(R.id.startDate);
        final EditText stupid = (EditText) findViewById(R.id.stupid);
        final EditText stupid2 = (EditText) findViewById(R.id.stupid2);
        final TimePicker time = (TimePicker)findViewById(R.id.timePicker);

        final int a=dt1.getDayOfMonth();
        final int b=dt1.getMonth();
        final int c=dt1.getYear();
        stupid.setText(getMonth(b)+ " "+ String.valueOf(a) + " "+String.valueOf(c));
        stupid2.setText(String.valueOf(time.getCurrentHour()+time.getCurrentMinute()));
        //gets date
        dt1.init(c,b,a, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                stupid.setText(getMonth(monthOfYear)+ " "+ String.valueOf(dayOfMonth) + " "+String.valueOf(year));
            }
        });

        //gets time
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                stupid2.setText(String.valueOf(hourOfDay) +String.valueOf(minute));
            }
        });

        final Button submit = (Button) findViewById(R.id.enterBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                try {
                    int num = Integer.parseInt(numSpaces.getText().toString());
                  //  Ride myRide= new Ride(et1.getText().toString(), spin1.getSelectedItem().toString(),
                    //        spin2.getSelectedItem().toString(),stupid.getText().toString(), stupid2.getText().toString(), num);
//
                    intentResult.putExtra("DESCRIPTION", et1.getText().toString() );
                    intentResult.putExtra("START",spin1.getSelectedItem().toString());
                    intentResult.putExtra("END",spin2.getSelectedItem().toString());
                    intentResult.putExtra("DATE",stupid.getText().toString());
                    intentResult.putExtra("TIME",stupid2.getText().toString());
                    intentResult.putExtra("SPACES",num);

                    setResult(RESULT_OK, intentResult);

                    finish();
                }
                catch(NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(), "That is not a valid dollar amount", Toast.LENGTH_LONG);
                }

            }
        });
    }
    public String getMonth(int i){
        if (i==0){return "January"; }
        if (i==1){ return "February"; }
        if (i==2){ return "March"; }
        if (i==3){return "April"; }
        if (i==4){ return "May";}
        if (i==5){return "June"; }
        if (i==6){ return "July";}
        if (i==7){  return "August"; }
        if (i==8){ return "September"; }
        if (i==9){ return "October";   }
        if (i==10){ return "Novemeber";   }
        if (i==11){ return "December";  }
        return "kldsjlsdkgjsd";
    }

}
