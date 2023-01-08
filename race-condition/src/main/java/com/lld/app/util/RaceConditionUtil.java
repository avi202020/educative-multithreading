package com.lld.app.util;

import java.util.Random;

public class RaceConditionUtil {
    private int randInt;
    private Random random = new Random(System.currentTimeMillis());

    public  void printer() {
        int i = 1000000;
        while (i!=0) {
            synchronized (this) {
                if (this.randInt % 5 == 0) {
                    if (this.randInt % 5 != 0) {
                        System.out.println(this.randInt);
                    }
                }
            }
            i--;
        }
    }

    public void modifier() {
        int i = 1000000;
        while (i!=0) {
            synchronized (this) {
                this.randInt = this.random.nextInt(1000);
                i--;
            }
        }
    }
}
