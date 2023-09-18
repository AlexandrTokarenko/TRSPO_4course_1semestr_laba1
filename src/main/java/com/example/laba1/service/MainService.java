package com.example.laba1.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainService {

    private Lock lock;
    private Condition condition;
    private double totalSum = 0;
    private int finished = 0;
    private long time = 0;

    public void calculate(int numberOfIntervals, int numberOfThreads) {

        double a = 1;
        double b = 9;
        int n = numberOfIntervals;
        int nThreads = numberOfThreads;
        double delta = (b-a)/nThreads;
        finished = 0;
        totalSum = 0;
        long start = System.currentTimeMillis();

        for (int i = 0; i < nThreads; i++) {
            IntegralCalculator calculator = new IntegralCalculator(a+i*delta,a+(i+1)*delta,n/nThreads,this);
            new Thread(calculator).start();
        }
        lock = new ReentrantLock();
        condition = lock.newCondition();
        try {
            lock.lock();
            while (finished < nThreads) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        long finish = System.currentTimeMillis();
        time = finish - start;

/*        double a = 0;
        double b = Math.PI/2;
        int n = numberOfIntervals;
        int nThreads = numberOfThreads;
        double delta = (b-a)/nThreads;
        finished = 0;
        totalSum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < nThreads; i++) {
            IntegralCalculator calculator = new IntegralCalculator(a+i*delta,a+(i+1)*delta,n/nThreads,this);
            new Thread(calculator).start();
        }
        lock = new ReentrantLock();
        condition = lock.newCondition();
        try {
            lock.lock();
            while (finished < nThreads) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        long finish = System.currentTimeMillis();
        time = finish - start;*/
    }

    public double getTotalSum() {
        return totalSum;
    }

    public long getTime() {
        return time;
    }

    public void sendResult(double v) {
        try {
            lock.lock();
            totalSum += v;
            finished++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
