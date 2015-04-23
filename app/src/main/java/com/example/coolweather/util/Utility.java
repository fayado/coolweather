package com.example.coolweather.util;

import com.example.coolweather.db.CoolWeatherDB;
import com.example.coolweather.model.City;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import java.util.List;

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
}
