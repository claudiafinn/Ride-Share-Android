package ridesharev3.examples.android.ait.ridesharev3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends Activity {

        private String username="meow";
        private TextView tvMessage=null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //final EditText etMessage = (EditText) findViewById(R.id.etMessage);
            Button btnSend = (Button) findViewById(R.id.btnCreate);
            Button btnRefresh = (Button) findViewById(R.id.btnRefresh);
            tvMessage= (TextView) findViewById(R.id.tvMessages);

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), CreateRide.class);
                    startActivityForResult(i, 1);
                   // sendMessage(i.getIntent().toString());
                }
            });
            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshRidess();
                }
            });

            ParsePush.subscribeInBackground("SharedRides");

            if(getIntent()!=null && getIntent().hasExtra("KEY_NEW_MESSAGE")&&
                    getIntent().getBooleanExtra("KEY_NEW_MESSAGE", false)){
                refreshRidess();
            }

        }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
               // tvMessage.setText("jksfhskdfhksd");
                sendRide(data.getStringExtra("DESCRIPTION"),data.getStringExtra("START")
                ,data.getStringExtra("END"),data.getStringExtra("DATE")
                ,data.getStringExtra("TIME"),data.getIntExtra("SPACES", 1));
               // sendRide(data.getStringExtra("DESCRIPTION"));
                Toast.makeText(this, "Item added to the list!"+data.getStringExtra("DESCRIPTION"), Toast.LENGTH_LONG).show();
                break;

            case RESULT_CANCELED:
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                break;
        }
    }
        private void sendRide(String description, String start,
                                 String end, String date, String time, int num){
            tvMessage.setText("here");
            ParseObject po = new ParseObject("Ride");
            po.put("user_name", username);
            po.put("description", description);
            po.put("Starting Loc", start);
            po.put("Destination", end);
            tvMessage.append("nowhere");
            po.put("Date", date);
            po.put("time", time);
            po.put("spaces available", num);
         /*  private void sendRide(String desc){
               ParseObject p = new ParseObject("Ride");
               p.put("description", desc);
               p.saveInBackground();*/

            ParsePush push = new ParsePush();
            push.setChannel("SharedRides");
            push.setMessage(username+": "+description);
            push.sendInBackground();
        }

    private void refreshRidess(){
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Ride");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject>parseObjects, ParseException e) {
                if(e!=null){
                    tvMessage.setText("Error "+e.getMessage());
                }
                else{
                     tvMessage.setText("lolhere");
                    tvMessage.append(parseObjects.toString());
                    for(ParseObject po: parseObjects){
                        tvMessage.append(po.getString("user_name")+": "+po.getString("description")+"\n");
                    }
                }
            }
        });
    }


}
