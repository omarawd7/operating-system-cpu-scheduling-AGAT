package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the number of processes: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfProcesses = scanner.nextInt();
        String name;
        String color;
        int arrivalTime,burstTime,priorityNumber,context;
        scanner.nextLine();
        ArrayList<Process> processes = new ArrayList<>();
        for (int i=0; i<numberOfProcesses;i++) {
            System.out.println("Enter the name of process " + i + ": ");
            name = scanner.nextLine();
            System.out.println("Enter the color of process " + i + ": ");
            color = scanner.nextLine();
            System.out.println("Enter the arrival time of process " + i + ": ");
            arrivalTime = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the burst time of process " + i + ": ");
            burstTime = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the priority number of process " + i + ": ");
            priorityNumber = scanner.nextInt();
            scanner.nextLine();
            Process process = new Process(name,color,arrivalTime,burstTime,priorityNumber);
            processes.add(process);

        }
        System.out.println("Enter context switching value: ");
        context = scanner.nextInt();
        scanner.nextLine();
        PriorityScheduler priorityScheduler = new PriorityScheduler(context,processes);
        priorityScheduler.showExecutionOrder();
        System.out.println("\n");
        priorityScheduler.CalculateProcessWaitingTime();
        priorityScheduler.showProcessWaitingTime();
        System.out.println("\n");
        priorityScheduler.CalculateAvgProcessWaitingTime();
        priorityScheduler.showAvgProcessWaitingTime();
        System.out.println("\n");
        priorityScheduler.CalculateProcessTurnAroundTime();
        priorityScheduler.showProcessTurnAroundTime();
        System.out.println("\n");
        priorityScheduler.CalculateAvgProcessTurnAroundTime();
        priorityScheduler.showAvgProcessTurnAroundTime();
        System.out.println("\n");
    }
}
