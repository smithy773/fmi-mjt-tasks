package org.fmitest;

import java.util.Arrays;

public class Main {
    static void main() {
        System.out.println(longestUniqueSubstring("abb"));
    }

    public static String longestUniqueSubstring(String s) {
        if (s.isEmpty()) {
            return "empty";
        }

        if (s.length() == 1) {
            return s;
        }

        String[] strContainer = s.split("");
        System.out.println(Arrays.toString(strContainer));
        int currIdx = 0;

        for (var i = 0; i < s.length(); i++) {
            if (i != 0 && !strContainer[i].equals(strContainer[i - 1]) && !strContainer[currIdx].contains(strContainer[i])) {
                strContainer[currIdx] += strContainer[i];
                System.out.println(Arrays.toString(strContainer));
            } else if (i != 0) {
                currIdx++;
            }
        }

        int longestStrIdx = 0;

        for (var j = 0; j < strContainer.length; j++) {
            if (strContainer[longestStrIdx].length() < strContainer[j].length()) {
                longestStrIdx = j;
            }
        }

        return strContainer[longestStrIdx];
    }
}


