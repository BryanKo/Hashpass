package com.example.david.testing;

/**
 * Created by David on 11/29/2017.
 */

public class DataModel {
    private String timeText;
    private String buttonText;

    public DataModel(String timeText, String buttonText){
        this.timeText = timeText;
        this.buttonText = buttonText;
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
}