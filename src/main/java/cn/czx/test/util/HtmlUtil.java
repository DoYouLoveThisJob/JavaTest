package cn.czx.test.util;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import org.fit.cssbox.demo.ImageRenderer;
/**
 * Created by czx on 2016/12/20.
 */
public class HtmlUtil {
    /**
     * url的页面转svg
     * @param url
     * @param saveFilePath
     * @return
     */
    public static boolean urlToSvg(String url,String saveFilePath){
       // Assert.hasText(url, "param[url] could not be null");
       // Assert.hasText(saveFilePath, "param[saveFilePath] could not be null");
        if(StringUtil.isEmpty(url)){
            return false;
        }
        if(StringUtil.isEmpty(saveFilePath)){
            return false;
        }
        try {
            ImageRenderer render = new ImageRenderer();

            FileOutputStream out = new FileOutputStream(new File(saveFilePath));
            render.setWindowSize(new Dimension(2500, 800), true);
            render.renderURL(url, out, ImageRenderer.Type.SVG);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args){
        HtmlUtil.urlToSvg("https://www.zhihu.com/question/28914581","D:\\QQDownload\\pdf\\demo.svg");

    }
}
