package cn.czx.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;

public class SVGConverter {
    public static void main(String[] args) throws Exception {
//  String svgpath = "C:\\Users\\HC\\Desktop\\html\\svg\\svgconvert\\test.svg";
        String svgpath = "D:\\SVG\\demo.svg";
        File svgFile = new File(svgpath);
        String name = svgFile.getName();
        name = name.substring(0,name.lastIndexOf("."));
        SVGConverter converter = new SVGConverter();
        //converter.svg2PDF(new File(svgpath), new File("D:/"+name+"_SVG文件转PDF.pdf"));
       // converter.svg2PNG(new File(svgpath), new File("D:/"+name+"_SVG文件转PNG.png"));
       // converter.svg2JPEG(new File(svgpath), new File("D:/"+name+"_SVG文件转JPG.jpg"));
        String svgCode = converter.svg2String(new File(svgpath));
        converter.svg2PDF(svgCode,  "D:/"+name+"_SVG代码转PDF.pdf");
        //converter.svg2PNG(svgCode,  "D:/"+name+"_SVG代码转PNG.png");
        //converter.svg2JPEG(svgCode,  "D:/"+name+"_SVG代码转JPG.jpg");
//        converter.svg2PDF(svgCode,  new FileOutputStream(new File("D:/"+name+"_SVG代码转输出流.pdf")));
//        converter.svg2PNG(svgCode,  new FileOutputStream(new File("D:/"+name+"_SVG代码转输出流.png")));
//        converter.svg2JPEG(svgCode, new FileOutputStream(new File("D:/"+name+"_SVG代码转输出流.jpg")));
    }


    public void svg2PNG(String svgCode,String outpath) throws TranscoderException, IOException{
        Transcoder transcoder  = new PNGTranscoder();
        svgConverte(svgCode, outpath, transcoder);
    }

    public void svg2PNG(String svgCode,OutputStream out) throws TranscoderException, IOException{
        Transcoder transcoder  = new PNGTranscoder();
        svgConverte(svgCode, out, transcoder);
    }

    public void svg2PNG(File svgFile,File outFile) throws TranscoderException, IOException{
        Transcoder transcoder  = new PNGTranscoder();
        svgConverte(svgFile, outFile, transcoder);
    }

    public void svg2JPEG(String svgCode,String outpath) throws TranscoderException, IOException{
        Transcoder transcoder  = new JPEGTranscoder();
        //为防止ERROR: The JPEG quality has not been specified. Use the default one: no compression 错误，需如下配置
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.99f);
        svgConverte(svgCode, outpath, transcoder);
    }

    public void svg2JPEG(String svgCode,OutputStream out) throws TranscoderException, IOException{
        Transcoder transcoder  = new JPEGTranscoder();
        //为防止ERROR: The JPEG quality has not been specified. Use the default one: no compression 错误，需如下配置
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.99f);
        svgConverte(svgCode, out, transcoder);
    }

    public void svg2JPEG(File svgFile,File outFile) throws TranscoderException, IOException{
        Transcoder transcoder  = new JPEGTranscoder();
        //为防止ERROR: The JPEG quality has not been specified. Use the default one: no compression 错误，需如下配置
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.99f);
        svgConverte(svgFile, outFile, transcoder);
    }


    public void svg2PDF(String svgCode,String outpath) throws TranscoderException, IOException{
        Transcoder transcoder  = new PDFTranscoder();
        svgConverte(svgCode, outpath, transcoder);
    }

    public void svg2PDF(String svgCode,OutputStream out) throws TranscoderException, IOException{
        Transcoder transcoder  = new PDFTranscoder();
        svgConverte(svgCode, out, transcoder);
    }

    public void svg2PDF(File svgFile,File outFile) throws TranscoderException, IOException{
        Transcoder transcoder  = new PDFTranscoder();
        svgConverte(svgFile, outFile, transcoder);
    }

    private void svgConverte(String svgCode, String outpath, Transcoder transcoder) throws IOException, TranscoderException {
        svgConverte(svgCode, getOutputStream(outpath), transcoder);
    }
    private void svgConverte(File svg, File outFile, Transcoder transcoder) throws IOException, TranscoderException {
        svgConverte(svg2String(getInputStream(svg)), getOutputStream(outFile), transcoder);
    }
    private void svgConverte(String svgCode, OutputStream out, Transcoder transcoder) throws IOException, TranscoderException {
        svgCode = svgCode.replaceAll(":rect", "rect");
        TranscoderInput input = new TranscoderInput(new StringReader(svgCode));
        TranscoderOutput output = new TranscoderOutput(out);
        svgConverte(input,output,transcoder);
    }
    private void svgConverte(TranscoderInput input, TranscoderOutput output, Transcoder transcoder) throws IOException, TranscoderException {
        transcoder.transcode(input, output);
    }

    public InputStream getInputStream(File file) throws IOException{
        return new FileInputStream(file);
    }

    public InputStream getInputStream(String filepath) throws IOException{
        File file = new File(filepath);
        if(file.exists())
            return getInputStream(file);
        else
            return null;
    }

    public OutputStream getOutputStream(File outFile) throws IOException{
        return new FileOutputStream(outFile);
    }

    public OutputStream getOutputStream(String outpath) throws IOException{
        File file = new File(outpath);
        if(!file.exists())
            file.createNewFile();
        return getOutputStream(file);
    }

    public String svg2String(File svgFile) throws IOException{
        InputStream in = getInputStream(svgFile);
        return svg2String(in, "UTF-8");
    }

    public String svg2String(File svgFile,String charset) throws IOException{
        InputStream in = getInputStream(svgFile);
        return svg2String(in, charset);
    }

    public String svg2String(InputStream in){
        return svg2String(in, "UTF-8");
    }


    public String svg2String(InputStream in,String charset){
        StringBuffer svgBuffer = new StringBuffer();
        BufferedReader bfr = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(in,charset);
            bfr = new BufferedReader(inputStreamReader);
            String line = "";
            while((line = bfr.readLine())!=null){
                svgBuffer.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bfr!=null)
                    bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return svgBuffer.toString();
    }
}