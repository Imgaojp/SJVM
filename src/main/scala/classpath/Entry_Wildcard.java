package classpath;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
public class Entry_Wildcard extends Entry {
    String baseDir = "";
    List<Entry> entryList = new ArrayList<>();

    public Entry_Wildcard(String path) {
        File file = new File(path.substring(0, path.length() - 1));
        baseDir = file.getAbsolutePath();
    }


    @Override
    public byte[] readClass(String className) {

        Path dir = Paths.get(baseDir);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path e : stream
                    ) {
                String fileName = e.getFileName().toString();
                if ((!e.toFile().isDirectory()) && (fileName.contains(".jar") || fileName.contains(".JAR"))) {
                    entryList.add(new Entry_Zip(e.getParent() + File.separator+ fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Entry entry : entryList
                ) {
            byte[] bytes = entry.readClass(className);
            if (bytes != null) {
                return bytes;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return baseDir;
    }
}
