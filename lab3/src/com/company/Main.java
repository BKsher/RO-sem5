package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Hairdresser extends Thread {

    private ArrayList<Customer> customers;

    private boolean isSleeping;

    private static Object sync = new Object();

    public Hairdresser() {
        isSleeping = true;
        System.out.println("Hairdresser is sleeping...");
        customers = new ArrayList<Customer>();
    }

    public synchronized boolean isSleeping() {
        return isSleeping;
    }

    public synchronized void addNewCustomer(Customer customer) {
        customers.add(customer);
    }

    public synchronized void wakeUp() {
        System.out.println("Hairdresser has woken up");
        this.isSleeping = false;
    }

    @Override
    public void run() {
        while(true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(isSleeping) continue;

            if(customers.size() == 0) {
                System.out.println("Hairdresser is sleeping...");
                isSleeping = true;
            } else {
                Customer customer = customers.get(0);
                customer.startWork();
                System.out.println("Hairdresser is working on " + customer.getFirstName() + " ...");
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Haircut for " + customer.getFirstName() + " is finished");
                customers.remove(customer);
            }
        }
    }
}

class Customer extends Thread {

    private String firstName;
    private Hairdresser hairdresser;

    private volatile boolean isFinished;
    private boolean isWorking;

    public Customer(String firstName, Hairdresser hairdresser) {
        this.firstName = firstName;
        this.hairdresser = hairdresser;
        this.isFinished = false;
        this.isWorking = false;
        arrived();
    }

    public String getFirstName() {
        return firstName;
    }

    private void arrived() {
        hairdresser.addNewCustomer(this);
        if(hairdresser.isSleeping()) {
            hairdresser.wakeUp();
        } else {
            System.out.println(firstName + " is sleeping in queue");
        }
        this.start();
    }

    public void startWork() {
         isWorking = true;
    }

    public void doHaircut(int time) {
        System.out.println(firstName + " is sleeping...");
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(firstName + " has woken up with new haircut!");
        isFinished = true;
    }

    @Override
    public void run() {
        while (!isFinished) {
            if(isWorking) {
                doHaircut(5000);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Hairdresser hairdresser = new Hairdresser();

        int totalCustomers = 0;

        hairdresser.start();

        while(true) {
            int input = 0;
            if(sc.hasNext())
                input = sc.nextInt();
            if(input == -1) break;
            if(input == 1) {
                totalCustomers++;
                Customer customer = new Customer("Customer" + totalCustomers, hairdresser);
            }
        }
    }
}
