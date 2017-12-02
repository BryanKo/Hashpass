package com.example.david.testing;

/**
 * Created by David on 11/29/2017.
 */

public class DataModel {
    private String timeText;
    private String buttonText;
    private String[] currWeather;
    private double[] currTemp;
    private double axx;
    private double ayy;

    public DataModel(String timeText, String buttonText, String[] currWeather, double[] currTemp, double axx, double ayy){
        this.timeText = timeText;
        this.buttonText = buttonText;
        this.currWeather = currWeather;
        this.currTemp = currTemp;
        this.axx = axx;
        this.ayy = ayy;
    }

    public String getTimeText(){
        return timeText;
    }

    public void setTimeText(String timeText){
        this.timeText = timeText;
    }

    public String getButtonText(){
        return buttonText;
    }

    public void setButtonText (String buttonText){
        this.buttonText = buttonText;
    }

    public String[] getCurrWeather() {
        return currWeather;
    }

    public void setCurrWeather(String[] currWeather){
        this.currWeather = currWeather;
    }

    public double[] getCurrTemp() {
        return currTemp;
    }

    public void setCurrTemp(double[] currTemp){
        this.currTemp = currTemp;
    }

    public  double getAxx(){
        return axx;
    }

    public void setAxx(double axx){
        this.axx = axx;
    }

    public  double getAyy(){
        return ayy;
    }

    public void setAyy(double ayy){
        this.ayy = ayy;
    }
}