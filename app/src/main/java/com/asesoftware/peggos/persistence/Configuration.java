package com.asesoftware.peggos.persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class Configuration {
    private Context context;

    public Configuration(Context context) {
        this.context = context;
    }

    public void initApp() {
        boolean mboolean = false;
        SharedPreferences settings = context.getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            settings = context.getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);

            //Toast.makeText(context,db.createDatabase(),Toast.LENGTH_LONG).show();
            editor.commit();
        } else {


        }
    }
}
