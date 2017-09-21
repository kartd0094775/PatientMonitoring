package com.company;

public class Log {
    private int time_point;
    private String alert_message_format;
    private String database_format;
    private double data_value;

    public Log(int time_point, double data_value) {
        this.time_point = time_point;
        this.data_value = data_value;

        database_format = Quiz.timeTag(time_point) + " " + data_value;

    }
    public void setAlertFormat(double upper_bound, double lower_bound, String patient_name, String device_name) {
        if (data_value == -1) {
            this.alert_message_format = Quiz.timeTag(this.time_point) + " " + device_name + " falls";
        } else if ((data_value > upper_bound) || (data_value < lower_bound)) {
            this.alert_message_format = Quiz.timeTag(this.time_point) + " " + patient_name + " is in danger! Cause: " + device_name + " " + String.valueOf(data_value);
        } else {
            this.alert_message_format = null;
        }
    }
    public boolean isGreaterThan(Log logObject) {
        return this.time_point > logObject.time_point;
    }
    public boolean isLowerThan(Log logObject) {
        return this.time_point < logObject.time_point;
    }
    public void setAlertFormat(String warning) {
        this.alert_message_format = Quiz.timeTag(time_point) + " " + warning;
    }
    public String getAlertMessage() {
        return this.alert_message_format;
    }
    public String getDatabaseMessage() {
        return this.database_format;
    }
}
