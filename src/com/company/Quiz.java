package com.company;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private static int monitor_period;

    private static int patient_number;
    private static List<Patient> patients;

    public static void main(String[] args) {

        patients = new ArrayList<>();
        patient_number = 0;
        monitor_period = Integer.parseInt(args[0]);
        ReadTheInputData(args);


    }
    private static void ReadTheInputData(String[] args) {


        for (int i =1; i < args.length; i++) {
            String temp[] = args[i].split(" ");
            if (temp[0] == "patient") {
                patient_number++;
                patients.add(new Patient(temp[1], Integer.parseInt(temp[2])));
            } else if (temp[0] == "PulseSensor") {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        0,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else if (temp[0] == "BloodPressureSensor") {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        1,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else if (temp[0] == "TemperatureSensor") {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        2,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else {
                System.out.println("Wrong Input Data");
            }
        }
    }
}
