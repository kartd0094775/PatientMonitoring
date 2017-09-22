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
        //monitor_period = Integer.parseInt(args[0]);
        //ReadTheInputData(args);
        try {
            File file = new File("sampleInput");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ReadTheInputData(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Monitoring();
        OutputDatabase();
    }
    public static void exchangeLog(List<Log> logs, int index) {
        Log temp = (Log) logs.get(index);
        logs.set(index, logs.get(index + 1));
        logs.set(index + 1, temp);
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
    private static void ReadTheInputData(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        monitor_period = Integer.parseInt(line);
        while ((line = reader.readLine()) != null) {
            String temp[] = line.split(" ");
            if (temp[0].equals("patient")) {
                patient_number++;
                patients.add(new Patient(temp[1], Integer.parseInt(temp[2])));
            } else if (temp[0].equals("PulseSensor")) {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        0,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else if (temp[0].equals("BloodPressureSensor")) {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        1,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else if (temp[0].equals("TemperatureSensor")) {
                Patient patient = patients.get(patient_number -1);
                patient.addDevice(new Device(
                        2,
                        temp[1],
                        temp[2],
                        Double.parseDouble(temp[3]),
                        Double.parseDouble(temp[4])
                ));
            } else {
                System.out.println("Wrong Input Data: " + temp[0]);
            }
        }
    }
//    private static void ReadTheInputData(String[] args) {
//        String[] tokens = args[0].split("patient");
//        for (int i=1; i<tokens.length; i++) {
//            String[] temp = tokens[i].split(" ");
//            patient_number++;
//            Patient patient = new Patient(temp[1], Integer.parseInt(temp[2]));
//            patients.add(patient);
//            int index = 3;
//            while (index < temp.length) {
//                if (temp[index].equals("PulseSensor")) {
//                    patient.addDevice(new Device(0,
//                            temp[index + 1],
//                            temp[index + 2],
//                            Double.parseDouble(temp[index + 3]),
//                            Double.parseDouble(temp[index + 4])
//                    ));
//                    index+=5;
//                } else if (temp[index].equals("BloodPressureSensor")) {
//                    patient.addDevice(new Device(1,
//                            temp[index + 1],
//                            temp[index + 2],
//                            Double.parseDouble(temp[index + 3]),
//                            Double.parseDouble(temp[index + 4])
//                    ));
//                    index+=5;
//                } else if (temp[index].equals("TemperatureSensor")) {
//                    patient.addDevice(new Device(2,
//                            temp[index + 1],
//                            temp[index + 2],
//                            Double.parseDouble(temp[index + 3]),
//                            Double.parseDouble(temp[index + 4])
//                    ));
//                    index+=5;
//                }
//            }
//        }
//    }
    private static void Monitoring() {
        List<Log> all_logs = new ArrayList<>();
        for (Patient patient: patients) {
            for (Device device: patient.getDevices()) {
                for (Log log: device.getLog()) {
                    all_logs.add(log);
                }
            }
        }
        for (int i = 1; i < all_logs.size()-1; i++) {
            for (int j = 0; j < all_logs.size()-i; j++) {
                if (all_logs.get(j).isGreaterThan(all_logs.get(j+1))) {
                    exchangeLog(all_logs, j);
                }
            }
        }
        for(Log log: all_logs){
            if(log.getAlertMessage() != null)
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
