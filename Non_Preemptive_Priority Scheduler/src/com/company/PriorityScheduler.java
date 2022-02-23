package com.company;

import java.util.ArrayList;

public class PriorityScheduler {
    private int contextSwitch;
    private ArrayList<Process> processArrayList;
    private ArrayList<Integer> waitingTime;
    double wait = 0.0;
    double avgWait = 0.0;
    double avgTurnaround=0.0;


    public PriorityScheduler(int contextSwitch, ArrayList<Process> processArrayList) {
        this.contextSwitch = contextSwitch;
        this.processArrayList = processArrayList;
        setExecutionArray();
    }

    public void setExecutionArray() {
            for (int i = 0; i < processArrayList.size()-1; i++)
            {
                int min_idx = i;
                for (int j = i+1; j < processArrayList.size(); j++)
                    if (processArrayList.get(j).getProcessPriorityNumber() > processArrayList.get(min_idx).getProcessPriorityNumber() && processArrayList.get(j).getProcessArrivalTime() <= processArrayList.get(min_idx).getProcessArrivalTime() ) {
                        min_idx = j;
                    }
                Process temp = new Process("","",0,0,0);
                temp= processArrayList.get(min_idx);
                processArrayList.set(min_idx,processArrayList.get(i));
                processArrayList.set(i, temp);
            }

    }


    public void showExecutionOrder() {
        System.out.println("Execution order: ");
        for (int i=0; i<processArrayList.size();i++) {
            System.out.println(processArrayList.get(i).getProcessName()+"->");
        }

    }

    // ***** Waiting Time Operations ******

    public void CalculateProcessWaitingTime() {
        for (int i=0; i<processArrayList.size();i++) {
            processArrayList.get(i).setProcessWaitingTime(wait);
            wait+=processArrayList.get(i).getProcessBurstTime() + contextSwitch;
        }
    }

    public void showProcessWaitingTime() {
        for (int i=0; i<processArrayList.size();i++) {
            System.out.println("Process " + processArrayList.get(i).getProcessName() + " waiting time: " + processArrayList.get(i).getProcessWaitingTime());
        }
    }

    public void CalculateAvgProcessWaitingTime() {
        for (int i=0; i<processArrayList.size();i++) {
            avgWait+=processArrayList.get(i).getProcessWaitingTime();
        }
        avgWait /= processArrayList.size();
    }

    public void showAvgProcessWaitingTime() {
        System.out.println("Average waiting time: " + avgWait);
    }


    // ***** Turnaround Time Operations ******

    public void CalculateProcessTurnAroundTime() {
        for (int i=0; i<processArrayList.size();i++) {
            processArrayList.get(i).setProcessTurnaroundTime(processArrayList.get(i).getProcessBurstTime() + processArrayList.get(i).getProcessWaitingTime());
        }

    }
    public void showProcessTurnAroundTime() {
        for (int i=0; i<processArrayList.size();i++) {
            System.out.println("Process " + processArrayList.get(i).getProcessName() + " turnaround time: " +processArrayList.get(i).getProcessTurnaroundTime());
        }
    }
    public void CalculateAvgProcessTurnAroundTime() {
        for (int i=0;i<processArrayList.size();i++) {
            avgTurnaround += processArrayList.get(i).getProcessTurnaroundTime();
        }
        avgTurnaround = avgTurnaround / processArrayList.size();
    }

    public void showAvgProcessTurnAroundTime() {
        System.out.println("Average turnaround time: " +avgTurnaround);
    }



}
