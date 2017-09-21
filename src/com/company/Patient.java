package com.company;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private int period;

    private List<Device> devices;

    public Patient(String name, int period) {
        this.name = name;
        this.period = period;
        devices = new ArrayList();
    }
    public String getName() {
        return this.name;
    }
    public int getPeriod() {return this.period;}
    public List<Device> getDevices() {
        return this.devices;
    }
    public void addDevice(Device device) {
        device.generateLog(this.period, this.name);
        devices.add(device);
    }
    public void outputPatientInfo() {
        System.out.println("patient" + " " + this.name);
        for (Device device: devices) {
            device.printLog();
        }
    }




}
