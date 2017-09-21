package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Quiz {
    public static int monitor_period;

    private static int patient_number;
    private static List<Patient> patients;

    public static void main(String[] args) {

        patients = new ArrayList<>();
        patient_number = 0;
        monitor_period = Integer.parseInt(args[0]);
        ReadTheInputData(args);
        Monitoring();
        OutputDatabase();
    }
    public static void exchangeLog(Log a, Log b) {
        Log temp = a;
        a = b;
        b = temp;
    }
    public static void OutputDatabase() {
        for (Patient patient: patients) {
            patient.outputPatientInfo();
        }
    }
    public static String timeTag(int time) {
        return "[" + String.valueOf(time) + "]";
    }
    public static int getFileLineNumber(String fileName) {
        File file = new File(fileName);
        int lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) lines++;
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lines;
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
    private static void Monitoring() {
        int length = 0;
        List<Log> all_logs = new ArrayList<>();
        for (Patient patient: patients) {
            for (Device device: patient.getDevices()) {
                for (Log log: device.getLog()) {
                    all_logs.add(log);
                    length++;
                }
            }
        }
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i; j++) {
                if (all_logs.get(j).isGreaterThan(all_logs.get(j+1))) {
                    exchangeLog(all_logs.get(j), all_logs.get(j+1));
                }
            }
        }
        for(Log log: all_logs){
            System.out.println(log.getAlertMessage());
        }

    }
//    private static ArrayList<Integer> GetTestPoint() {
//        int[] test_point = new int[monitor_period];
//        for (Patient patient: patients) {
//            int point = 0;
//            while (point <= monitor_period) {
//                if(!IntStream.of(test_point).anyMatch(x->x==patient.getPeriod())) {
//
//                }
//            }
//        }
//    }
}
