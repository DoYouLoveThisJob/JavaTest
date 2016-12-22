package cn.czx.test.util;

import org.fit.cssbox.demo.ImageRenderer;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

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
    public static boolean HTML2SVG(String url,String saveFilePath){
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


}
