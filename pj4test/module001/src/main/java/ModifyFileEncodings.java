

//import info.monitorenter.cpdetector.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.awt.SystemColor.info;

public class ModifyFileEncodings{

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 封装目录,需要修改文件格式的路径
        File srcFolder = new File("E:\\Workspaces\\ws4idea\\pj001\\concurrent\\src");

        String newCharater = "UTF-8";

        getAllJavaFilePaths(srcFolder, newCharater);
    }

    private static void getAllJavaFilePaths(File srcFolder, String newCharater) throws IOException {

        // 获取该目录下所有的文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();

        // 遍历该File数组，得到每一个File对象
        for (File file : fileArray) {

            // 继续判断是否以特定文件结尾,不是的话继续调用getAllJavaFilePaths()方法
            if (file.isDirectory()) {
                getAllJavaFilePaths(file, newCharater);
            } else {
                if (file.getName().endsWith(".java")) {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        //oldcCharacter 获取特定的字符集
                       // String oldcCharacter = getChartsetName(file);
                        String oldcCharacter = "UTF-16";
                        InputStreamReader isr = new InputStreamReader(fis, oldcCharacter);
                        BufferedReader br = new BufferedReader(isr);
                        String str = null;
                        // 创建StringBuffer字符串缓存区
                        StringBuffer sb = new StringBuffer();
                        // 通过readLine()方法遍历读取文件
                        while ((str = br.readLine()) != null) {
                            // 使用readLine()方法无法进行换行,需要手动在原本输出的字符串后面加"\n"或"\r"
                            str += "\n";
                            sb.append(str);
                        }
                        String fileSource = sb.toString();
                        // 以GBK格式写入文件,file.getAbsolutePath()即该文件的绝对路径,false代表不追加直接覆盖,true代表追加文件
                        FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), false);
                        OutputStreamWriter osw = new OutputStreamWriter(fos, newCharater);
                        try {
                            osw.write(fileSource);
                            System.out.println(
                                    "将：" + oldcCharacter + " 的文件：" + file.getAbsolutePath() + "修改字符集为：" + newCharater);
                        } finally {
                            osw.flush();
                            osw.close();
                            fos.close();
                            br.close();
                            isr.close();
                            fis.close();
                        }
                    } catch (Exception e) {
                    }
                } else {
                    System.err.println("该文件以忽略：" + file.getAbsolutePath());
                }
            }
        }
    }

//    public static String getChartsetName(File file) {
//        String chartsetName = null;
//        // 获取文件编码格式
//        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//        detector.add(new ParsingDetector(true));
//        detector.add(JChardetFacade.getInstance());
//        detector.add(ASCIIDetector.getInstance());
//        detector.add(UnicodeDetector.getInstance());
//        java.nio.charset.Charset charset = null;
//        try {
//            if (file != null) {
//                charset = detector.detectCodepage(file.toURL());
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        if (charset != null) {
//            chartsetName = charset.name();
//        } else {
//            chartsetName = "未知的编码";
//        }
//        return chartsetName;
//    }
 /*
  *  <dependency>
             <groupId>net.sourceforge.jchardet</groupId>
             <artifactId>jchardet</artifactId>
             <version>1.0</version>
         </dependency>
         <dependency>
             <groupId>antlr</groupId>
             <artifactId>antlr</artifactId>
             <version>2.7.7</version>
         </dependency>
    */
}