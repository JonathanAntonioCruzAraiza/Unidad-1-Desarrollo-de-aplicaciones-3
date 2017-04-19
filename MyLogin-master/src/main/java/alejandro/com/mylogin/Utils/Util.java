package alejandro.com.mylogin.Utils;

import android.content.SharedPreferences;

/**
 * Created by Kast on 19/04/2017.
 */
public class Util {

    public static String getUserMailPref(SharedPreferences preferences){
        return preferences.getString("email","");
    }

    public static String getPassMailPref(SharedPreferences preferences){
        return preferences.getString("pass","");
    }

}
