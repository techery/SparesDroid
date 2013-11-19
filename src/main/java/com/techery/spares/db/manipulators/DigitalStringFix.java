package com.techery.spares.db.manipulators;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class DigitalStringFix {
    public static String encode(String str){
        if(isDigitalString(str,0)){
            return "_" + str;
        } else{
            return str;
        }
    }

    public static String decode(String str){
        if(str == null)
            return str;
        if(str.length() == 0)
            return str;
        if(str.charAt(0) != '_')
            return str;
        if(!isDigitalString(str,1))
            return str;
        return str.substring(1);
    }

    private static boolean isDigitalString(String str, int start){
        if(str == null)
            return false;

        int len = str.length();
        if(start >= len)
            return false;

        for(int i = start; i < len; i++){
            if("0123456789".indexOf(str.charAt(i)) < 0)
                return false;
        }
        return true;
    }
}
