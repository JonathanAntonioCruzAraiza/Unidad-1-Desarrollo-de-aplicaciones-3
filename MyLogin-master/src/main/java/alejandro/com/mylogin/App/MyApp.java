package alejandro.com.mylogin.App;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Kast on 19/04/2017.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
