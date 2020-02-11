package com.rulerbug.first.Utils;

public class TextUtils {
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isEmpty(String... strs) {
        boolean isHaveEmpty = false;
        for (String str : strs) {
            if (isEmpty(str)) {
                isHaveEmpty = true;
                break;
            }
        }
        return isHaveEmpty;
    }
}
