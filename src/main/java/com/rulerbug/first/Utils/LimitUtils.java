package com.rulerbug.first.Utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author RulerBug
 */
public class LimitUtils {

    private static String splitString = "\\*";
    private static int t0000 = 0;
    private static int t1000 = 8;
    private static int t0100 = 4;
    private static int t0010 = 2;
    private static int t0001 = 1;
    private static int tOnlyAdd = t1000;
    private static int tOnlyDel = t0100;
    private static int tOnlyUpdate = t0010;
    private static int tOnlySearch = t0001;
    private static int tOnlyNone = t0000;
    public static List<String> SkillInfo = Arrays.asList(new String[]{
            "isCanUseYcode"});

    /*
     *需要确定权限的表
     * */
    public static enum SKILLS {

        ISCANUSEYCODE(0);
        private int index;

        private SKILLS(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    protected static int error = -1;

    public static boolean isHaveAdd(int index, String msg) {
        if (index == error) {
            return false;
        }
        final String[] split = msg.split(splitString);
        String limitALL = split[index];
        int AddLimit = tOnlyAdd & Integer.parseInt(limitALL);
        return AddLimit == tOnlyAdd;
    }

    public static boolean isHavedEL(int index, String msg) {
        if (index == error) {
            return false;
        }
        final String[] split = msg.split(splitString);
        String limitALL = split[index];
        int DelLimit = tOnlyDel & Integer.parseInt(limitALL);
        return DelLimit == tOnlyDel;
    }

    public static boolean isHaveUpdate(int index, String msg) {
        if (index == error) {
            return false;
        }
        final String[] split = msg.split(splitString);
        String limitALL = split[index];
        int UpdateLimit = tOnlyUpdate & Integer.parseInt(limitALL);

        return UpdateLimit == tOnlyUpdate;
    }

    public static boolean isHaveSearch(int index, String msg) {
        if (index == error) {
            return false;
        }
        final String[] split = msg.split(splitString);
        String limitALL = split[index];
        int SearchLimit = tOnlySearch & Integer.parseInt(limitALL);
        return SearchLimit == tOnlySearch;
    }

    protected static int getTablesIndex(String tableName) {
        for (String nameString : SkillInfo) {
            if (nameString.equals(tableName)) {
                return SkillInfo.indexOf(nameString);
            }
        }
        return error;
    }

    protected static int getTablesIndex(SKILLS table) {
        return table.getIndex();
    }

    public static boolean isHaveAdd(String table, String msg) {
        return isHaveAdd(getTablesIndex(table), msg);
    }

    public static boolean isHaveAdd(SKILLS table, String msg) {
        return isHaveAdd(getTablesIndex(table), msg);
    }

    public static boolean isHavedEL(String table, String msg) {
        return isHavedEL(getTablesIndex(table), msg);
    }

    public static boolean isHavedEL(SKILLS table, String msg) {
        return isHavedEL(getTablesIndex(table), msg);
    }

    public static boolean isHaveUpdate(String table, String msg) {
        return isHaveUpdate(getTablesIndex(table), msg);
    }

    public static boolean isHaveUpdate(SKILLS table, String msg) {
        return isHaveUpdate(getTablesIndex(table), msg);
    }

    public static boolean isHaveSearch(String table, String msg) {
        return isHaveSearch(getTablesIndex(table), msg);
    }

    public static boolean isHaveSearch(SKILLS table, String msg) {
        return isHaveSearch(getTablesIndex(table), msg);
    }

}
