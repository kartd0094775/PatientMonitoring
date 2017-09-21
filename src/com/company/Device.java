package com.company;

import java.io.File;

public class Device {
    private int category;
    // Device Category:
    //  0: Pulse
    //  1: BloodPressure
    //  2: Temperature
    private double upper_bound;
    private double lower_bound;

    private File dataset_file;

    private String name;

    public Device(int category, String name, String file_name, double lower_bound, double upper_bound) {
        this.name = name;
        this.category = category;
        this.upper_bound = upper_bound;
        this.lower_bound = lower_bound;
        this.dataset_file = new File(file_name);
    }

}
