package ridesharev3.examples.android.ait.ridesharev3;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by claudiafinn on 12/1/14.
 */
public class MyPushBroadcastReceiver extends ParsePushBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtras(intent.getExtras());
        i.putExtra("KEY_NEW_MESSAGE", true);
        context.startActivity(i);

    }
}
