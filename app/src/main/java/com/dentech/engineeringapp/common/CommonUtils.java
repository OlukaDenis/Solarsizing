package com.dentech.engineeringapp.common;

import com.dentech.engineeringapp.model.Mobile;
import com.dentech.engineeringapp.model.OnMarket;
import com.dentech.engineeringapp.model.ProjectParams;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonUtils {
    public static String current_user_phone;
    public static ProjectParams currentParameters;
    public static OnMarket current_on_market;

    public static double mobileTotal1;
    public static double mobileTotal2;

    public static double computerTotal1;
    public static double computerTotal2;

    public static double lightsTotal1;
    public static double lightsTotal2;

    public static double tvTotal1;
    public static double tvTotal2;

    public static double radioTotal1;
    public static double radioTotal2;

    public static double fridgeTotal1;
    public static double fridgeTotal2;


    //after calculation
    public static double panelsRating;
    public static double number_of_panels;
    public static double batteryRating;
    public static double number_of_batteries;
    public static double controllerRating;
    public static double inverterRating;

    //rounding off
    public static double round(double value, int places){
        if (places < 0){
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
