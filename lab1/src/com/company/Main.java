package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Move extends Thread {

    private static int locked = 0;

    private boolean direction;
    private volatile JSlider slider;

    private boolean isStopped = true;

    public synchronized void keepGoing() {
        this.isStopped = false;
        locked = 1;
    }

    public synchronized void doStop() {
        this.isStopped = true;
        locked = 0;
    }

    public synchronized boolean keepRunning() {
        return !this.isStopped;
    }

    Move(boolean direction, JSlider slider) {
        this.direction = direction;
        this.slider = slider;
        this.setDaemon(true);
    }

    public static int getLocked() {
        return locked;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public JSlider getSlider() {
        return slider;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }


    @Override
    public void run() {
        while(true) {
            //System.out.println(direction ? "right" : "left");
            if(Thread.interrupted()) {
                System.out.println("Thread" + Thread.currentThread().getName() + " was interrupted");
                break;
            }
            if(keepRunning()) {
                slider.setValue(slider.getValue() + (direction ? 1 : -1));
                //System.out.println("I'm here");
                if(slider.getValue() < 10) slider.setValue(10);
                if(slider.getValue() > 90) slider.setValue(90);
                try {
                    sleep(direction ? 100 : 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        JSlider slider = new JSlider();
        slider.setBounds(40, 0, 400, 100);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);

        Move moveLeft = new Move(false, slider);
        Move moveRight = new Move(true, slider);

        moveLeft.start(); moveRight.start();

        JButton startLeftButton = new JButton("Start left");
        startLeftButton.setBounds(100, 150, 100, 50);
        startLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moveRight.getLocked() == 1)
                    JOptionPane.showMessageDialog(null, "Thread right currently working");
                else moveLeft.keepGoing();
            }
        });
        JButton startRightButton = new JButton("Start right");
        startRightButton.setBounds(300, 150, 100, 50);
        startRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moveLeft.getLocked() == 1)
                    JOptionPane.showMessageDialog(null, "Thread left currently working");
                else moveRight.keepGoing();
            }
        });
        JButton stopLeftButton = new JButton("Stop left");
        stopLeftButton.setBounds(100, 250, 100, 50);
        stopLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLeft.doStop();
            }
        });
        JButton stopRightButton = new JButton("Stop Right");
        stopRightButton.setBounds(300, 250, 100, 50);
        stopRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRight.doStop();
            }
        });

        JPanel panel = new JPanel(null);

        panel.add(startLeftButton);
        panel.add(startRightButton);
        panel.add(stopLeftButton);
        panel.add(stopRightButton);
        panel.add(slider);

        frame.getContentPane().add(panel);

        frame.setSize(500,600);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
