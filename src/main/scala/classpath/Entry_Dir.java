package classpath;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class Entry_Dir extends Entry {
    private String absDir;

    public Entry_Dir(String path) {
        File file = new File(path);
        absDir = file.getAbsolutePath();
    }

    /**
     * read class in hex
     * @param className
     * @return
     */
    @Override
    public byte[] readClass(String className) {
        Path path = Paths.get(absDir, className);
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) path.toFile().length());
        BufferedInputStream inputStream=null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path.toFile()));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len=inputStream.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            System.out.println("Class "+className+" not found"+" in "+absDir);
            try {
                try {
                    inputStream.close();
                } catch (Exception e1) {
                    System.out.println("inputStream does not exist");
                }
                bos.close();
            } catch (Exception e1) {
                System.out.println("bos close error");
            }
        }
        return null;
    }

    public String toString() {
        return this.absDir;
    }
}
