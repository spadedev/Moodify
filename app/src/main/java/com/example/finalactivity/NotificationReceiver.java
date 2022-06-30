package com.example.finalactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String CUSTOM_INTENT = "com.example.finalactivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences mPreferences = context.getSharedPreferences("PREFERENCE_KEY_NAME", MODE_PRIVATE);

        if (CUSTOM_INTENT.equals(intent.getAction())) {
            Log.d("asdf", "I've began");

            mPreferences.edit().putInt("counter", 0).apply();
            mPreferences.edit().putInt("PREFERENCE_KEY_COLOR", 2).apply();
            mPreferences.edit().putString("PREFERENCE_KEY_COMMENT", "").apply();

        } else {
            Log.d("tagtag", "received unwanted intent: " + intent);
        }

    }
}
