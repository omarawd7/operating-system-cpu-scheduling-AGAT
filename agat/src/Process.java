//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.util.*;

public class Process implements Comparable<Process> {
    public int factor;
    private String processName;
    private String processColor;
    private int processArrivalTime;
    private int processBurstTime;
    private int processPriorityNumber;
    private int Quantum;
    private double processWaitingTime;
    private double processTurnaroundTime;
    public int remainingTime;

    public Process() {
    }

    public Process(String processName, String processColor, int processArrivalTime, int processBurstTime, int processPriorityNumber, int Quantum) {
        this.processName = processName;
        this.processColor = processColor;
        this.processArrivalTime = processArrivalTime;
        this.processBurstTime = processBurstTime;
        this.processPriorityNumber = processPriorityNumber;
        this.Quantum = Quantum;
    }

    public void setQuantum(int Quantum) {
        this.Quantum = Quantum;
    }

    public int getQuantum() {
        return this.Quantum;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessColor() {
        return this.processColor;
    }

    public void setProcessColor(String processColor) {
        this.processColor = processColor;
    }

    public int getProcessArrivalTime() {
        return this.processArrivalTime;
    }

    public void setProcessArrivalTime(int processArrivalTime) {
        this.processArrivalTime = processArrivalTime;
    }

    public int getProcessBurstTime() {
        return this.processBurstTime;
    }

    public void setProcessBurstTime(int processBurstTime) {
        this.processBurstTime = processBurstTime;
    }

    public int getProcessPriorityNumber() {
        return this.processPriorityNumber;
    }

    public void setProcessPriorityNumber(int processPriorityNumber) {
        this.processPriorityNumber = processPriorityNumber;
    }

    public double getProcessWaitingTime() {
        return this.processWaitingTime;
    }

    public void setProcessWaitingTime(double processWaitingTime) {
        this.processWaitingTime = processWaitingTime;
    }

    public double getProcessTurnaroundTime() {
        return this.processTurnaroundTime;
    }

    public void setProcessTurnaroundTime(double processTurnaroundTime) {
        this.processTurnaroundTime = processTurnaroundTime;
    }

    public int compareTo(Process _p) {
        return this.getProcessArrivalTime() - _p.getProcessArrivalTime();
    }

    public String toString() {
        return "processName='" + this.processName + '\'' + ", processColor='" + this.processColor + '\'' + ", processArrivalTime=" + this.processArrivalTime + ", processBurstTime=" + this.processBurstTime + ", processPriorityNumber=" + this.processPriorityNumber + '}' + "\n";
    }

    public static class AGAT {
        private ArrayList<java.lang.Process> processes = new ArrayList();
        private ArrayList<java.lang.Process> processes2 = new ArrayList();
        private Queue<java.lang.Process> readyQueue = new LinkedList();
        public static ArrayList<Integer> times = new ArrayList();
        private ArrayList<java.lang.Process> results = new ArrayList();

        public AGAT() {
        }

        public AGAT(ArrayList<java.lang.Process> _processes, int _roundRobinTimeQuantum) {
            this.processes = _processes;
        }

        public boolean isFinished() {
            for(int i = 0; i < this.processes.size(); ++i) {
                if (((java.lang.Process)this.processes.get(i)).getQuantum() != 0) {
                    return false;
                }
            }

            return true;
        }

        public java.lang.Process getleastAG(int _time) {
            int minAG = 2147483647;
            int index = 0;

            for(int i = 0; i < this.processes.size() && ((java.lang.Process)this.processes.get(i)).getProcessArrivalTime() <= _time; ++i) {
                if (((java.lang.Process)this.processes.get(i)).factor < minAG && ((java.lang.Process)this.processes.get(i)).getQuantum() != 0) {
                    minAG = ((java.lang.Process)this.processes.get(i)).factor;
                    index = i;
                }
            }

            return (java.lang.Process)this.processes.get(index);
        }

        public int getProcessIndex(java.lang.Process _p) {
            for(int i = 0; i < this.processes.size(); ++i) {
                if (((java.lang.Process)this.processes.get(i)).getProcessName() == _p.getProcessName()) {
                    return i;
                }
            }

            return -1;
        }

        public boolean isLastProcess(java.lang.Process _p, int _time) {
            int index = this.getProcessIndex(_p);
            boolean flag = true;
            if (index == this.processes.size() - 1) {
                return true;
            } else {
                ++index;

                for(; index < this.processes.size() && ((java.lang.Process)this.processes.get(index)).getProcessArrivalTime() <= _time; ++index) {
                    if (((java.lang.Process)this.processes.get(index)).factor != 0) {
                        flag = false;
                    }
                }

                return flag;
            }
        }

        public java.lang.Process getBestProcess(int _time, int _preIndex) {
            java.lang.Process p = (java.lang.Process)this.processes.get(_preIndex);
            java.lang.Process temp;
            if (this.isLastProcess(p, _time) && this.readyQueue.size() > 0) {
                for(temp = (java.lang.Process)this.readyQueue.poll(); temp.remainingTime == 0; temp = (java.lang.Process)this.readyQueue.poll()) {
                }

                return temp;
            } else {
                temp = this.getleastAG(_time);
                return temp;
            }
        }

        public void setProcesses() {
            for(int i = 0; i < this.processes.size(); ++i) {
                ((java.lang.Process)this.processes.get(i)).remainingTime = ((java.lang.Process)this.processes.get(i)).getProcessBurstTime();
                ((java.lang.Process)this.processes.get(i)).factor = (int)((double)(10 - ((java.lang.Process)this.processes.get(i)).getProcessPriorityNumber()) + Math.ceil((double)((java.lang.Process)this.processes.get(i)).getProcessArrivalTime() / this.calc_V1()) + Math.ceil((double)((java.lang.Process)this.processes.get(i)).remainingTime / this.calc_V2()));
            }

        }

        public int nonPreemptiveAG(java.lang.Process _p) {
            int nonPreemptiveAGTime = (int)Math.round((double)_p.getQuantum() * 0.04D);
            int minValue = Math.min(nonPreemptiveAGTime, _p.remainingTime);
            return minValue;
        }

        public int preemptiveAG(java.lang.Process _p, int _time, int _executingQuantum) {
            int timeForThisProcess = 0;

            while(_p.remainingTime > 0) {
                java.lang.Process p = this.getleastAG(_time);
                if (p.getProcessName() != _p.getProcessName()) {
                    break;
                }

                --_p.remainingTime;
                ++_time;
                ++timeForThisProcess;
                if (_p.getQuantum() == timeForThisProcess + _executingQuantum) {
                    break;
                }
            }

            return timeForThisProcess;
        }

        double calc_V1() {
            double V1;
            if (((java.lang.Process)this.processes.get(this.processes.size() - 1)).getProcessArrivalTime() > 10) {
                V1 = (double)((float)((java.lang.Process)this.processes.get(this.processes.size() - 1)).getProcessArrivalTime() / 10.0F);
            } else {
                V1 = 1.0D;
            }

            return V1;
        }

        int Max_Burst() {
            int max = 0;

            for(int i = 0; i < this.processes.size(); ++i) {
                if (((java.lang.Process)this.processes.get(i)).getProcessBurstTime() > max) {
                    max = ((java.lang.Process)this.processes.get(i)).getProcessBurstTime();
                }
            }

            return max;
        }

        double calc_V2() {
            double V2;
            if (this.Max_Burst() > 10) {
                V2 = (double)(this.Max_Burst() / 10);
            } else {
                V2 = 1.0D;
            }

            return V2;
        }

        public void printResults() {
            int i = 0;

            for(Iterator var2 = this.results.iterator(); var2.hasNext(); ++i) {
                java.lang.Process p = (java.lang.Process)var2.next();
                System.out.println(p.getProcessName() + " : " + times.get(i));
            }

        }

        public ArrayList<java.lang.Process> start() {
            Collections.sort(this.processes);
            this.setProcesses();
            int preIndex = -1;
            java.lang.Process current = null;
            int time = ((java.lang.Process)this.processes.get(0)).getProcessArrivalTime();

            while(!this.isFinished()) {
                times.add(time);
                int currentIndex;
                if (preIndex == -1) {
                    current = (java.lang.Process)this.processes.get(0);
                    currentIndex = 0;
                } else {
                    current = this.getBestProcess(time, preIndex);
                    currentIndex = this.getProcessIndex(current);
                }

                int nonPreemptiveAGTime = this.nonPreemptiveAG(current);
                time += nonPreemptiveAGTime;
                java.lang.Process var10000 = (java.lang.Process)this.processes.get(currentIndex);
                var10000.remainingTime -= nonPreemptiveAGTime;
                int preemptiveAGTime = this.preemptiveAG(current, time, nonPreemptiveAGTime);
                time += preemptiveAGTime;
                if (current.remainingTime == 0) {
                    ((java.lang.Process)this.processes.get(currentIndex)).setQuantum(0);
                    this.readyQueue.remove(this.processes.get(currentIndex));
                    this.processes2.add(this.processes.get(currentIndex));
                } else if (current.remainingTime != 0 && nonPreemptiveAGTime + preemptiveAGTime == current.getQuantum()) {
                    ((java.lang.Process)this.processes.get(currentIndex)).setQuantum(current.getQuantum() + 2);
                    this.readyQueue.add(current);
                } else if (nonPreemptiveAGTime + preemptiveAGTime != current.getQuantum()) {
                    ((java.lang.Process)this.processes.get(currentIndex)).setQuantum(((java.lang.Process)this.processes.get(currentIndex)).getQuantum() + nonPreemptiveAGTime + preemptiveAGTime);
                    this.readyQueue.add(current);
                }

                ((java.lang.Process)this.processes.get(currentIndex)).setProcessArrivalTime(time);
                preIndex = this.getProcessIndex(current);
                this.results.add(current);
            }

            this.printResults();
            return this.results;
        }
    }
}
