package org.fmitest;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {
    static void main() {
        System.out.println(minDifference(new int[]{9,1,1,1}));
    }

    public static int minDifference(int[] tasks) {
        switch (tasks.length) {
            case 0:
                System.out.println("И двата екипа нямат задачи, ти си Бог");
                return 0;
            case 1:
                System.out.println("Много справедливо, няма що");
                return tasks[0];
        }

        int sum = 0;
        boolean isAllEqual = true;

        for (int task : tasks) {
            sum += task;
            if (task != tasks[0]) {
                isAllEqual = false;
            }
        }

        int halfSum = sum / 2;
        boolean[] cont = new boolean[halfSum + 1];
        cont[0] = true;

        if (isAllEqual) {
            System.out.println("Мир и разбирателство");
            return 0;
        }


        for (int i = 0; i < tasks.length; i++) {
            int currNum = tasks[i];

            for (int j = halfSum; j >= currNum; j--) {
                if (cont[j - currNum]) {
                    cont[j] = true;
                }
            }
        }

        for (int k = halfSum; k >= 0; k--) {
            if (cont[k]) {
                return sum - 2 * k;
            }
        }

        return 0;
    }
}
