package org.example;

import main.thread.MyConsumer;
import main.thread.MyProducer;

public class Main {
    public static void main(String[] args) {
        MyProducer p = new MyProducer();
        MyProducer p2 = new MyProducer();
        MyConsumer m1 = new MyConsumer("Azay");
        MyConsumer m2 = new MyConsumer("Eldar");
        MyConsumer m3 = new MyConsumer("Cavid");
        MyConsumer m4 = new MyConsumer("Nergiz");
        MyConsumer m5 = new MyConsumer("Rashid");
        MyConsumer m6 = new MyConsumer("Tural");

        p.start();
        p2.start();
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();
    }
}