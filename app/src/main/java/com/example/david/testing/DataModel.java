package com.example.david.testing;

/**
 * Created by David on 11/29/2017.
 */

/**
 * DataModel is used to hold data when passed between MainActivity and MainAdapter, and vice versa
 * It contains an array for the current weather, and current temp,
 * Strings for current time, texts for the buttons (food, bar, active, and indoor),
 * GPS axis for location and passTime, which I believe is the UNIX time to be used with
 * the weather API to get temp and weather of various hours.
*/
 public class DataModel {
    private String[] currWeather;
    private String timeText;
    private String foodText;
    private String barText;
    private String activeText;
    private String indoorText;
    private double[] currTemp;
    private double axx;
    private double ayy;
    private double passTime;

    public DataModel(String timeText, String foodText, String barText, String activeText, String indoorText, String[] currWeather, double[] currTemp, double axx, double ayy, double passTime){
        this.timeText = timeText;
        this.foodText = foodText;
        this.barText = barText;
        this.activeText = activeText;
        this.indoorText = indoorText;
        this.currWeather = currWeather;
        this.currTemp = currTemp;
        this.axx = axx;
        this.ayy = ayy;
        this.passTime = passTime;
    }

    public String getTimeText(){
        return timeText;
    }

    public void setTimeText(String timeText){
        this.timeText = timeText;
    }

    public String getFoodText(){
        return foodText;
    }

    public void setFoodText (String foodText){
        this.foodText = foodText;
    }

    public String getBarText(){
        return barText;
    }

    public void setBarText (String barText){
        this.barText = barText;
    }
    public String getActiveText(){
        return activeText;
    }

    public void setActiveText (String activeText){
        this.activeText = activeText;
    }

    public String getIndoorText(){
        return indoorText;
    }

    public void setIndoorText (String indoorText){
        this.indoorText = indoorText;
    }

    public String[] getCurrWeather() {
        return currWeather;
    }

    public void setCurrWeather(String[] currWeather){ this.currWeather = currWeather; }

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

    public  double getAyy(){ return ayy; }

    public void setAyy(double ayy){ this.ayy = ayy; }

    public void setPassTime(double passTime){ this.passTime = passTime; }

    public  double getPassTime(){ return passTime; }

}