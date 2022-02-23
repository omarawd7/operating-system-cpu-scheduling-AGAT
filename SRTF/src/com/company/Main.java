package com.company;

import java.util.ArrayList;

class Process {
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

class SRTF
{   static String exe="";
    static void WaitingTime(Process[] proc, int n, int[] wt)
    {
        int[] remainingTime = new int[n];
        for (int i = 0; i < n; i++)
            remainingTime[i] = proc[i].getProcessBurstTime();

        int count = 0;
        int shortestTime = 0;


        int minimum = Integer.MAX_VALUE;
        int timeLine = 0;
        boolean Executing = false;
        ArrayList<String> arr=new ArrayList<>();

        while (count < n) {

            for (int j = 0; j < n; j++) {
                if ((proc[j].getProcessArrivalTime() <= timeLine) && (remainingTime[j] < minimum) && remainingTime[j] > 0) {
                    minimum = remainingTime[j]; //Burst Time
                    shortestTime = j;
                    Executing = true;

                    // System.out.print(timeLine+"--"+proc[j].getProcessName()+"--");
                }
            }
            if (!Executing) {
                timeLine++;
                continue;
            }
            if(!arr.contains(proc[shortestTime].getProcessName()))
            {arr.add(proc[shortestTime].getProcessName());
                //  System.out.print(timeLine+"--"+proc[shortestTime].getProcessName()+"--");
                exe+=timeLine+"--"+proc[shortestTime].getProcessName()+"--";
            }
            // if(arr.contains(proc[shortestTime].getProcessName()))
            //{System.out.print(timeLine+"--"+proc[shortestTime].getProcessName()+"--");}


            remainingTime[shortestTime] -= 1;
            minimum = remainingTime[shortestTime];

            if (remainingTime[shortestTime] == 0) {
                minimum = Integer.MAX_VALUE;
                count++;
                Executing = false;
                wt[shortestTime] = timeLine + 1 - (proc[shortestTime].getProcessBurstTime() + proc[shortestTime].getProcessArrivalTime());
            }
            timeLine++;

        }
        exe+=timeLine;
    }

    static void TurnAroundTime(Process[] proc, int n, int[] watingTime, int[] turnaroundTime)
    {
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = proc[i].getProcessBurstTime() + watingTime[i];
        }
    }

    static void exeDetails(Process[] proc, int n)
    {
        int[] wt = new int[n];
        int[] tat = new int[n];
        int  total_w = 0, total_t = 0;

        WaitingTime(proc, n, wt);
        TurnAroundTime(proc, n, wt, tat);
        System.out.println(exe);
        System.out.println("Processes   Burst time  Waiting time    Turn around time");

        for (int i = 0; i < n; i++) {
            total_w += wt[i];
            total_t += tat[i];
            System.out.println(proc[i].getProcessName() + "\t\t\t" + proc[i].getProcessBurstTime() + "\t\t \t" + wt[i] + "\t\t\t\t" + tat[i]);
        }
        System.out.println("Average waiting time = " + (double)total_w / (double) n);
        System.out.println("Average turn around time = " + (double)total_t / (double) n);

    }

}
public class Main{
    public static void main(String[] args)
    {
        Process[] proc = { new Process("p1", "red",1, 6,0),
                new Process("p2", "blue",3, 8,0),
                new Process("p3", "green",3, 4,0),
                new Process("p4", "yellow",5, 3,0)};

        SRTF.exeDetails(proc, proc.length);
    }

}
