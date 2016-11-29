package cn.czx.test.iotest;


import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS-PC on 2016/11/29.
 */
public class FileUtil {
    //出现问题文本，建议设置成utf-8 无bom格式或者去除\ufeff
    //文本内容去重
    public static void duplicateRemovalOp(String oldTextName,String newTextName) throws IOException {
        File file=new File(oldTextName);
        BufferedReader  br= new BufferedReader(new InputStreamReader(new BOMInputStream(new FileInputStream(file))));
        String s = null;
        Set<String> strSet=new HashSet<String>();
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            strSet.add(s);
        }
        br.close();
        File file2=new File(newTextName);
        if(!file2.exists()){
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2),"utf-8"));
        for(String  lineContext:strSet){
            bw.write(lineContext);
            bw.newLine();
        }
        bw.close();
    }
    public static void main(String[] args){
        try {
            FileUtil.duplicateRemovalOp("D:\\workProjects\\Text\\搜索店铺名1.txt","D:\\workProjects\\Text\\搜索店铺名2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
