package com.company;

import java.io.*;
import java.util.List;

public class Device {

    private String[] device_categories = {"PulseSensor", "BloodPressureSensor", "TemperatureSensor"};
    private int category;
    // Device Category:
    //  0: Pulse
    //  1: BloodPressure
    //  2: Temperature

    private double upper_bound;
    private double lower_bound;
    private double[] dataset;

    private String name;

    private Log[] logs;

    public Device(int category, String name, String file_name, double lower_bound, double upper_bound) {

        int lines_num = Quiz.getFileLineNumber(file_name);

        this.name = name;
        this.category = category;
        this.upper_bound = upper_bound;
        this.lower_bound = lower_bound;
        this.dataset = new double[lines_num];

        // Reade the DataSset
        try {
            String line;
            File dataset_file = new File(file_name);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataset_file));
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                dataset[i] = Double.parseDouble(line);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getName() {return this.name;}
    public int getCategory() {return this.category;}
    public Log[] getLog() {
        return this.logs;
    }
    public void generateLog(int period, String patient_name) {
        int point = 0;
        int  index = 0;
        double value;
        logs = new Log[Quiz.monitor_period / period];
        while (point <= Quiz.monitor_period) {

            value = (index < dataset.length) ? dataset[index] : -1;
            Log log = new Log(point, value);
            log.setAlertFormat(this.upper_bound, this.lower_bound, patient_name, this.name);
            logs[index] = log;

            index++;
            point += period;
        }
    }
    public void printLog() {
        System.out.println(device_categories[category] + " " + this.name);
        for (Log log: logs) {
            System.out.println(log.getDatabaseMessage());
        }
    }

}
