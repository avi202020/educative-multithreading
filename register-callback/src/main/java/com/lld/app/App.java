package com.lld.app;


import com.lld.app.util.Callback;
import com.lld.app.util.CallbackExecutor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        CallbackExecutor callbackExecutor = new CallbackExecutor();
        Thread thread = new Thread(() -> {
            try {
                callbackExecutor.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        Thread.sleep(4000);

        System.out.println("Start time : " + System.currentTimeMillis()/1000);
        callbackExecutor.registerCallback(new Callback(4, "Function 1"));
        callbackExecutor.registerCallback(new Callback(8, "Function 2"));
        callbackExecutor.registerCallback(new Callback(1, "Function 3"));
    }
}
