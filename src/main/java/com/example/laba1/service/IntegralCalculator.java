package com.example.laba1.service;
import com.example.laba1.fun.Function;

public class IntegralCalculator implements Runnable{

    private MainService main;

    private double a;
    private double b;
    private int n;

    public IntegralCalculator() {
    }

    public IntegralCalculator(double a, double b, int n, MainService main) {

        this.a = a;
        this.b = b;
        this.n = n;
        this.main = main;
    }

    @Override
    public void run() {

        double v = calculate(a,b,n);
        main.sendResult(v);
    }

    public double calculate(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (Function.calc(a) + Function.calc(b)) * 0.5;
        double preSum = 0;
        for (int i = 1; i < n; i += 1) {
            preSum += Function.calc(a + i * h);
        }
        sum += preSum;
        preSum = 0;
        for (int i = 1; i <= n; i += 1) {
            preSum += Function.calc(((a + i * h) + (a + (i-1) * h))/2);
        }
        sum += preSum * 2;
        return (h / 3) * sum;
    }

   /* public double calculate(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = Function.calc(a) + Function.calc(b);
        for (int i = 1; i < n; i += 2) {
            sum += 4 * Function.calc(a + i * h);
        }
        for (int i = 2; i < n - 1; i += 2) {
            sum += 2 * Function.calc(a + i * h);
        }
        return (h / 3) * sum;
    }*/


   /* public double calculate(double a, double b, int n) {
        double s = 0;
        double h = (b-a)/n;
        for (int i = 0; i < n; i++) {
            double x = a + i*h + h/2;
            double y = Function.calc(x/h);
            s += y;
        }
        return s * h;
    }*/
}
