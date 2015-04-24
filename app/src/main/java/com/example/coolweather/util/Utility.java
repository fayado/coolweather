package com.example.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.coolweather.db.CoolWeatherDB;
import com.example.coolweather.model.City;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 2015/4/23.
 */
public class Utility {

    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
                                                               List<weather> weathers) {
        if (weathers != null && weathers.size() > 0){
            for(weather weather : weathers){
                Province province = new Province();
                province.setProvinceCode(weather.getPyName());
                province.setProvinceName(weather.getCityname());
                coolWeatherDB.saveProvince(province);

            }
        return true;
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, List<weather> weathers,
                                               int provinceId) {
        if (weathers != null && weathers.size() > 0){
            for(weather weather : weathers){
                City city = new City();
                city.setCityCode(weather.getPyName());
                city.setCityName(weather.getCityname());
                city.setProvinceId(provinceId);
                coolWeatherDB.saveCity(city);

            }
            return true;
        }
        return false;
    }

    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, List<weather> weathers,
                                               int cityId) {
        if (weathers != null && weathers.size() > 0){

            for(weather weather : weathers){
                County county = new County();
                county.setCountyCode(weather.getPyName());
                county.setCountyName(weather.getCityname());
                county.setCityId(cityId);
                coolWeatherDB.saveCounty(county);

            }
            return true;
        }
        return false;
    }

    /**
     * 将服务器返回的所有天气信息存储到SharedPreferences文件中。
     */
    public static void saveWeatherInfo(String cityCode,Context context,weather weather) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context).edit();
        editor.putString("cityCode",cityCode);
        editor.putBoolean("city_selected", true);
        editor.putString("city_name", weather.getCityname());
        editor.putString("temp1", weather.getTem1());
        editor.putString("temp2", weather.getTem2());
        editor.putString("tempNow", weather.getTemNow());
        editor.putString("weather_desp", weather.getStateDetailed());
        editor.putString("publish_time", weather.getTime());
        editor.putString("current_date", sdf.format(new Date()));
        editor.commit();
    }
}
