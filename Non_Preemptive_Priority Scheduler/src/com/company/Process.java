package com.company;

public class Process {
    private String processName;
    private String processColor;
    private int processArrivalTime, processBurstTime, processPriorityNumber;
    private double processWaitingTime;
    private double processTurnaroundTime;

    public Process(String processName, String processColor, int processArrivalTime, int processBurstTime, int processPriorityNumber) {
        this.processName = processName;
        this.processColor = processColor;
        this.processArrivalTime = processArrivalTime;
        this.processBurstTime = processBurstTime;
        this.processPriorityNumber = processPriorityNumber;
    }

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

    public int getProcessPriorityNumber() {
        return processPriorityNumber;
    }

    public void setProcessPriorityNumber(int processPriorityNumber) {
        this.processPriorityNumber = processPriorityNumber;
    }

    public double getProcessWaitingTime() {
        return processWaitingTime;
    }

    public void setProcessWaitingTime(double processWaitingTime) {
        this.processWaitingTime = processWaitingTime;
    }

    public double getProcessTurnaroundTime() {
        return processTurnaroundTime;
    }

    public void setProcessTurnaroundTime(double processTurnaroundTime) {
        this.processTurnaroundTime = processTurnaroundTime;
    }

    @Override
    public String toString() {
        return "processName='" + processName + '\'' +
                ", processColor='" + processColor + '\'' +
                ", processArrivalTime=" + processArrivalTime +
                ", processBurstTime=" + processBurstTime +
                ", processPriorityNumber=" + processPriorityNumber +
                '}' + "\n";
    }
}