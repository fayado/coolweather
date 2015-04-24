package com.example.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.example.coolweather.receiver.AutoUpdateReceiver;
import com.example.coolweather.util.HttpCallbackListener;
import com.example.coolweather.util.Utility;
import com.example.coolweather.util.XmlWeather;
import com.example.coolweather.util.weather;

import java.util.List;

/**
 * Created by admin on 2015/4/24.
 */
public class AutoUpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String cityCode = prefs.getString("cityCode","");
        final String countyName = prefs.getString("city_name","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather(cityCode,countyName);
            }
        }).start();
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 8*60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i = new Intent(this,AutoUpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather(final String cityCode,final String countyName){

        XmlWeather.getCityWeather(cityCode,countyName,new HttpCallbackListener() {
            @Override
            public void onFinish(List<weather> weathers) {
                Utility.saveWeatherInfo(cityCode, AutoUpdateService.this, weathers.get(0));
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
