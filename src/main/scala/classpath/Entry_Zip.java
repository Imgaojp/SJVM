package classpath;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * Created by Imgaojp on 2017/2/17.
 */
public class Entry_Zip extends Entry {
    String absPath;

    public Entry_Zip(String path) {
        File file = new File(path);
        absPath = file.getAbsolutePath();
    }

    /**
     * use JarEntry,JarInputStream read "class" file in "zip" or "jar" file
     * @param className
     * @return
     */
    @Override
    public byte[] readClass(String className) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(new File(absPath)));
            JarInputStream jarInput = new JarInputStream(in);
            JarEntry entry = jarInput.getNextJarEntry();
            while (entry != null) {
                if (className.equals(entry.getName())) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int buf_size=1024;
                    byte[] buffer = new byte[buf_size];
                    int len=0;
                    while (-1 != (len = jarInput.read(buffer, 0, buf_size))) {
                        bos.write(buffer, 0, len);
                    }
                    return bos.toByteArray();
                }
                entry = jarInput.getNextJarEntry();
            }
        } catch (Exception e) {
            System.out.println("Class "+className+" not found"+" in "+absPath);
        }
        return null;
    }

    @Override
    public String toString() {
        return absPath;
    }
}
