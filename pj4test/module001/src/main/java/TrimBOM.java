import java.io.*;

public class TrimBOM {

    public static InputStream getInputStream(InputStream in) throws IOException {

        PushbackInputStream testin = new PushbackInputStream(in);
        int ch = testin.read();
        if (ch != 0xEF) {
            testin.unread(ch);
        } else if ((ch = testin.read()) != 0xBB) {
            testin.unread(ch);
            testin.unread(0xef);
        } else if ((ch = testin.read()) != 0xBF) {
            throw new IOException("错误的UTF-8格式文件");
        } else {
            // 不需要做，这里是bom头被读完了
            // // System.out.println("still exist bom");
        }
        return testin;

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 封装目录,需要修改文件格式的路径
        File srcFolder = new File("E:\\Workspaces\\ws4idea\\pj001\\concurrent\\src\\test");
        doTrimBom(srcFolder);
    }

    private static void doTrimBom(File srcFolder) throws IOException {

        // 获取该目录下所有的文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();
        // 遍历该File数组，得到每一个File对象
        for (File file : fileArray) {
            // 继续判断是否以特定文件结尾,不是的话继续调用getAllJavaFilePaths()方法
            if (file.isDirectory()) {
                doTrimBom(file);
            } else {
                if (file.getName().endsWith(".java")) {
                    try {
                        FileInputStream fisOld = new FileInputStream(file);
                        InputStream fis = getInputStream(fisOld);
                        InputStreamReader isr = new InputStreamReader(fis);
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
                        // file.getAbsolutePath()即该文件的绝对路径,false代表不追加直接覆盖,true代表追加文件
                        FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), false);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        try {
                            osw.write(fileSource);
                            System.out.println(
                                    "已将文件：" + file.getAbsolutePath() + "的BOM去掉");
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

}
