package com.company;

class Test {
    static void beeTest() {
        int[][] bigField = new int[3][10];
        bigField[2][8] = 1;

        Bee bee1 = new Bee(bigField[0], "Bee1");
        Bee bee2 = new Bee(bigField[1], "Bee2");
        Bee bee3 = new Bee(bigField[2], "Bee3");

        bee1.start(); bee2.start(); bee3.start();
    }
}

class Bee extends Thread{
    private static boolean isFound = false;

    private int[] field;
    private String name;
    private int currentPos;

    public Bee(int[] field, String name) {
        this.field = field;
        this.name = name;
        currentPos = 0;
    }

    private boolean keepSearching() {
        return !isFound;
    }

    @Override
    public void run() {
        while(keepSearching()) {
            if(currentPos >= field.length) break;
            if(field[currentPos] == 1) {
                System.out.println("Vinny is found by " + name + "!!!");
                isFound = true;
            }
            System.out.println("Field checked by " + name + " in pos " + currentPos + " is clear");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentPos++;
        }
    }
}

class Soldier extends Thread {
    private String name;

    private static boolean isOut = false, isLoaded = false;

    private int total = 5;

    public Soldier(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(total > 0) {
            if(name.equals("Ivanov")) {
                System.out.println("Ivanov is taking out the object...");
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ivanov took out the object");
                total--;
                isOut = true;
            }

            if(name.equals("Petrov")) {
                if(isOut) {
                    isOut = false;
                    System.out.println("Petrov loading object in car...");
                    try {
                        sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Petrov has loaded object in car");
                    total--;
                    isLoaded = true;
                }
            }

            if(name.equals("Nechiporyk")) {
                if(isLoaded) {
                    isLoaded = false;
                    System.out.println("Nechiporyk is counting the object...");
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Nechiporyk has counted the object");
                    total--;
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Soldier ivan = new Soldier("Ivanov");
        Soldier petr = new Soldier("Petrov");
        Soldier nech = new Soldier("Nechiporyk");
        ivan.start(); petr.start(); nech.start();
    }
}
