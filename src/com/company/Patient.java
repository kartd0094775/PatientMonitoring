package com.company;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String name;
    private int period;
    private Device device[];
    private List<Device> devices;

    public Patient(String name, int period) {
        this.name = name;
        this.period = period;
        devices = new ArrayList();
    }
    public String getName() {
        return this.name;
    }
    public void addDevice(Device device) {
        devices.add(device);
    }




}
