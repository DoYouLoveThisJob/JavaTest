package cn.czx.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by czx on 2016/12/21.
 */
public class HttpRequestUtil {

    public static String sendPost(String url, String param,String Subject,String Signkey,String flag,String date,String crc, String ua)throws Exception {

        StringBuffer buffer = new StringBuffer();
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setConnectTimeout(10000);
        if(!StringUtil.isEmpty(ua)){
            connection.setRequestProperty("User-Agent", ua);
        }else{
            connection.setRequestProperty("User-Agent", "WMS Invoker");
        }

        if("1".equals(flag)){
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setRequestProperty("X-Request-Valid", crc);
            connection.setRequestProperty("Date", date);
        }else{
            connection.setRequestProperty("Subject", Subject);
            connection.setRequestProperty("Signkey", Signkey);
            connection.setRequestProperty("Message-From", "JUANPI");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("X-Requested-With", "java");
        }

        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        out.write(param);
        out.flush();
        out.close();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String reponseText = buffer.toString();
        return reponseText;

    }

}
