package com.hoo.util;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
 
/**
 * <b>function:</b> 导出在增量的文件
 * @author hoojo
 * @createDate 2013-11-2 下午10:00:01
 * @file ExportIncrementFiles.java
 * @package com.hoo.util
 * @project AntTest
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class ExportIncrementFiles {
 
    /**
     * <b>function:</b> 导出增量文件
     * @author hoojo
     * @createDate 2013-11-2 下午10:15:43
     * @param configPath 增量文件路径配置目录
     * @param baseDir 基本路径 目标位置
     * @param destDir 增量文件保存位置
     * @throws Exception
     */
    private static void export(String configPath, String baseDir, String destDir) throws Exception {
        String srcFile = baseDir + configPath;
        String desFile = destDir + configPath;
        System.out.println(desFile);
        int lastIndex = desFile.lastIndexOf("/");
        String desPath = desFile.substring(0, lastIndex);
        
        File srcF = new File(srcFile);
        if(srcF.exists()){//如果不存在这样的源文件，就不再拷贝，这个用来解决版本之间有删除文件的情况。
            File desF = new File(desFile);
            File desP = new File(desPath);
            if(!desP.exists()) {
                desP.mkdirs();
            }
            System.out.println(srcFile);
            FileInputStream fis = new FileInputStream(srcF);
            FileOutputStream fos = new FileOutputStream(desF);
            
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) != -1) {
                fos.write(buf,0,len);
            }
            fos.flush();
            fos.close();
            fis.close();
        }
    }
    
    /**
     * <b>function:</b> 主函数 执行导出增量包任务
     * @author hoojo
     * @createDate 2013-11-2 下午10:00:01
     * @param args 参数1 增量包导出文件路径，参数2 要导出的文件的所在目标位置，参数3 增量包导出保存的位置路径
     */
    public static void main(String[] args) {
 
    	System.out.println("args[0] is Export Increment Files content path");
        System.out.println("args[1] is Export Increment Files target path");
        System.out.println("args[2] is Increment Files Export loaction");
        
        if (args.length > 0) {
            if (args.length == 1 && "help".equals(args[0])) {
                System.out.println("args[0] is Export Increment Files content path");
                System.out.println("args[1] is Export Increment Files target path");
                System.out.println("args[2] is Increment Files Export loaction");
            } else {
                String configPath = args[0];
                String baseDir = args[1];
                String destDir = args[2];
                
                try {
                    BufferedReader br = new BufferedReader(new FileReader(configPath));
                    String s = null;
                    while((s = br.readLine()) != null) {
                        s = s.trim();//去掉路径前面的空格
                        String str = destDir + s;
                        if(!destDir.equals(str)){//过滤空行
                            export(s, baseDir, destDir);
                        }
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}