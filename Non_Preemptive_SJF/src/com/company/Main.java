package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Process> processes=new ArrayList<>();

    public  static void SJF(){
        int system_Time=0, total_processes=0,Size=processes.size();
        float Average_Waiting_Time=0, Average_Turnaround_Time=0;
        String ProcessName[] = new String[Size];
        String ProcessColor[] = new String[Size];
        int ArrivalTime[] = new int[Size];
        int BurstTime[] = new int[Size];
        int ProcessID[] = new int[Size];
        int CompleteTime[] = new int[Size];
        int turnaround[] = new int[Size];
        int WaitingTime[] = new int[Size];
        int flag[]  = new int[Size];

        for (int i=0;i<Size;i++)
        {
            ProcessName[i]=processes.get(i).getProcessName();
            ProcessColor[i]=processes.get(i).getProcessColor();
            ArrivalTime[i]=processes.get(i).getProcessArrivalTime();
            BurstTime[i]=processes.get(i).getProcessBurstTime();
            ProcessID[i]=processes.get(i).getProcessId();
            flag[i]=0;
        }

        while(true)
        {
            int Current_Process=0, min=100;
            if (total_processes == Size)    //when all processes are completed
                break;
            for (int i=0; i<Size; i++)
            {
                if ((ArrivalTime[i] <= system_Time) && (flag[i] == 0) && (BurstTime[i]<min)) //to get the shortest Process time
                {
                    min=BurstTime[i];
                    Current_Process=i;
                }
            }
            if (Current_Process==Size)
                system_Time++;
            else
            {
                CompleteTime[Current_Process]=system_Time+BurstTime[Current_Process];
                system_Time+=BurstTime[Current_Process];
                turnaround[Current_Process]=CompleteTime[Current_Process]-ArrivalTime[Current_Process];
                WaitingTime[Current_Process]=turnaround[Current_Process]-BurstTime[Current_Process];
                flag[Current_Process]=1;
                total_processes++;
            }
        }
        for(int i=0;i<Size;i++)
        {

            System.out.println("Process ID:"+ProcessID[i]);
            System.out.println("process Name:"+ProcessName[i]);
            System.out.println("precess Color:"+ProcessColor[i]);
            System.out.println("Waiting Time:"+WaitingTime[i]);
            System.out.println("Turnaround Time:"+turnaround[i]);
            System.out.println("----------------------");
            Average_Waiting_Time+= WaitingTime[i];
            Average_Turnaround_Time+= turnaround[i];
        }
        System.out.println ("Average Turnaround Time is: "+ (float)(Average_Turnaround_Time/Size));
        System.out.println ("Average Waiting Time is: "+ (float)(Average_Waiting_Time/Size));
    }
    public static void main(String[] args) {
	// write your code here
        System.out.println("Enter the number of processes: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfProcesses = scanner.nextInt();
        String name,color;
        int arrivalTime,burstTime,choice=0;
        scanner.nextLine();
        for (int i=0; i<numberOfProcesses;i++) {
            System.out.println("Enter the name of process " + (i+1) + ": ");
            name = scanner.nextLine();
            System.out.println("Enter the color of process " + (i+1) + ": ");
            color = scanner.nextLine();
            System.out.println("Enter the arrival time of process " + (i+1)+ ": ");
            arrivalTime = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the burst time of process " + (i+1) + ": ");
            burstTime = scanner.nextInt();
            scanner.nextLine();
            Process process = new Process(name,color,arrivalTime,burstTime,(i+1));
            processes.add(process);
        }
        SJF();
    }
}
