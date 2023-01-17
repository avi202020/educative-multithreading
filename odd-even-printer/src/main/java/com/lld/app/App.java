package com.lld.app;

import com.lld.app.util.OddEvenPrinterUtility;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        OddEvenPrinterUtility printer = new OddEvenPrinterUtility(1, 10);
        Thread oddPrinter = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread evenPrinter = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        oddPrinter.start();
        evenPrinter.start();

        oddPrinter.join();
        evenPrinter.join();
    }
}
