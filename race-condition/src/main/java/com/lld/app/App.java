package com.lld.app;

import com.lld.app.util.RaceConditionUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RaceConditionUtil raceConditionUtil = new RaceConditionUtil();

        Thread modifierThread = new Thread(() -> raceConditionUtil.modifier());
        Thread printerThread = new Thread(() -> raceConditionUtil.printer());

        modifierThread.start();
        printerThread.start();

        try {
            modifierThread.join();
            printerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
