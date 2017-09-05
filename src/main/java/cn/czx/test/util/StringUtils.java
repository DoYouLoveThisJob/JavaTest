package cn.czx.test.util;

/**
 * Created by czx on 2017/9/5.
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        if(str == null){
            return true;
        }
        if(str.length() == 0){
           return  true;
        }
        if(str.trim().equals("")){
            return  true;
        }
        return false;

    }
}
