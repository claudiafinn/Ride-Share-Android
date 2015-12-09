package ridesharev3.examples.android.ait.ridesharev3;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by claudiafinn on 12/1/14.
 */
public class MyApplication extends Application {

    public void onCreate(){
        super.onCreate();
        Parse.initialize(this,
                "pyLXfo5n8fQ5c1tCFYcFMifk1vtqHrJHkFVtXGfT", "xrKpYuonSrIf2scRu0Q0m2NlxafeWUzoMrgaL76C");

        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
