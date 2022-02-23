package com.company;

public class Process {
    private String processName;
    private String processColor;
    private int processArrivalTime;
    private int processBurstTime;
    private int ProcessId;

    public Process(String processName, String processColor, int processArrivalTime, int processBurstTime,int ProcessId) {
        this.processName = processName;
        this.processColor = processColor;
        this.processArrivalTime = processArrivalTime;
        this.processBurstTime = processBurstTime;
        this.ProcessId=ProcessId;
    }

    public int getProcessId() {return ProcessId;}

    public void setProcessId(int processId) {ProcessId = processId;}

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessColor() {
        return processColor;
    }

    public void setProcessColor(String processColor) {
        this.processColor = processColor;
    }

    public int getProcessArrivalTime() {
        return processArrivalTime;
    }

    public void setProcessArrivalTime(int processArrivalTime) {
        this.processArrivalTime = processArrivalTime;
    }

    public int getProcessBurstTime() {
        return processBurstTime;
    }

    public void setProcessBurstTime(int processBurstTime) {
        this.processBurstTime = processBurstTime;
    }


}
