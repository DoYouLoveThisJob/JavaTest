package cn.czx.test.util;

import com.hankcs.hanlp.HanLP;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by czx on 2016/12/6.
 */
public class StringUtil {
    /**
     *
     *----------Dragon be here!----------/
     * 　　　┏┓　　　┏┓
     * 　　┏┛┻━━━┛┻┓
     * 　　┃　　　　　　　┃
     * 　　┃　　　━　　　┃
     * 　　┃　┳┛　┗┳　┃
     * 　　┃　　　　　　　┃
     * 　　┃　　　┻　　　┃
     * 　　┃　　　　　　　┃
     * 　　┗━┓　　　┏━┛
     * 　　　　┃　　　┃神兽保佑
     * 　　　　┃　　　┃代码无BUG！
     * 　　　　┃　　　┗━━━┓
     * 　　　　┃　　　　　　　┣┓
     * 　　　　┃　　　　　　　┏┛
     * 　　　　┗┓┓┏━┳┓┏┛
     * 　　　　　┃┫┫　┃┫┫
     * 　　　　　┗┻┛　┗┻┛
     * ━━━━━━神兽出没━━━━━━
     */
    public static String gbEncoding(String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        //System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    public static String decodeUnicode(String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }




    public static void main(String[] args) {
        System.out.println(HanLP.convertToTraditionalChinese("老实"));

      System.out.println(gbEncoding("老师abc"));
      System.out.println(decodeUnicode("\\u8001\\u5e08\\u0061\\u0062\\u0063"));

//            Locale defaultLocale = Locale.getDefault();
//           Locale[] locales=Locale.getAvailableLocales();
//            for( Locale locale:locales){
//                System.out.println(locale.getCountry()+"-"+locale.getLanguage());
//            }
//            TW-zh
//            System.out.println("country:" + defaultLocale.getCountry());
//            System.out.println("language:" + defaultLocale.getLanguage());


        Locale defaultLocale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle",
                defaultLocale);
        String k1 = rb.getString("k1");
        System.out.println(k1);
        MessageFormat mf = new MessageFormat(rb.getString("k1"));
        System.out.println(mf.format(new Object[]{"张三"}));

    }
    public static boolean isEmpty(String str){
        if(str!=null && !str.equals("") &&  !str.trim().equals("")){
           return false;
        }
        return true;

    }



}
