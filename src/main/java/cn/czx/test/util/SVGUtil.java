package cn.czx.test.util;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by czx on 2016/12/22.
 */
public class SVGUtil {
    public static void svg2Pdf(String svgPath,String pdfPath) throws Exception {
        Transcoder transcoder = new PDFTranscoder();
        TranscoderInput transcoderInput = new TranscoderInput(
                new FileInputStream(new File(svgPath)));
        TranscoderOutput transcoderOutput = new TranscoderOutput(
                new FileOutputStream(new File(pdfPath)));
        transcoder.transcode(transcoderInput, transcoderOutput);

    }


    public static void svg2PNG(String svgPath,String pdfPath) throws Exception {
        Transcoder transcoder = new PNGTranscoder();

        TranscoderInput transcoderInput = new TranscoderInput(
                new FileInputStream(new File(svgPath)));
        TranscoderOutput transcoderOutput = new TranscoderOutput(
                new FileOutputStream(new File(pdfPath)));
        transcoder.transcode(transcoderInput, transcoderOutput);

    }

    public static void svg2JPEG(String svgPath,String pdfPath) throws Exception {
        Transcoder transcoder = new JPEGTranscoder();
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.99f);
        TranscoderInput transcoderInput = new TranscoderInput(
                new FileInputStream(new File(svgPath)));
        TranscoderOutput transcoderOutput = new TranscoderOutput(
                new FileOutputStream(new File(pdfPath)));
        transcoder.transcode(transcoderInput, transcoderOutput);

    }

    public static void main(String[] args){
        //HtmlUtil.urlToSvg("https://www.zhihu.com/question/28914581","D:\\SVG\\demo.svg");
        try {
            svg2JPEG("D:\\SVG\\demo.svg","D:\\SVG\\demo.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
