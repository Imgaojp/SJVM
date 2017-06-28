package classpath;

import java.io.File;
import java.util.Objects;

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
public class ClassPath {
    public Entry bootClasspath;
    public Entry extClasspath;
    public Entry userClasspath;

    public void parse(String jreOption,String cpOption) {
        parseBootAndExtClasspath(jreOption);
        parseUserClasspath(cpOption);
    }

    public byte[] readClass(String className) {

        className = className.concat(".class");
        byte[] bytes = bootClasspath.readClass(className);
        if ( bytes!= null) {
            return bytes;
        }
        bytes = extClasspath.readClass(className);
        if (bytes != null) {
            return bytes;
        }
        return userClasspath.readClass(className);
    }

    public void parseUserClasspath(String cpOption) {
        if (Objects.equals(cpOption, "")) {
            cpOption = ".";
        }
        userClasspath = Entry.newEntry(cpOption);
    }

    public void parseBootAndExtClasspath(String jreOption) {
        String jreDir = "";
        String env = System.getenv("JAVA_HOME");
//        String env = "/usr/lib/jvm/java-8-openjdk-amd64";
        if (!Objects.equals(jreOption, "") && exists(jreOption)) {
            jreDir = jreOption;
        } else if (exists("./jre")) {
            jreDir = "./jre";
        } else if (!Objects.equals(env, "")) {
            jreDir = env.concat(File.separatorChar + "jre");
        } else {
            throw new RuntimeException("can't find jre folder");
        }
        // jre/lib/*
        String jreLibPath = jreDir.concat(File.separatorChar + "lib" + File.separatorChar + "*");
        bootClasspath = Entry.newEntry(jreLibPath);

        // jre/lib/ext/*
        String jreExtPath = jreDir.concat(File.separatorChar + "lib" + File.separatorChar + "ext" + File.separatorChar + "*");
        extClasspath = Entry.newEntry(jreExtPath);
    }

    private boolean exists(String path) {
        return new File(path).exists();
    }

    @Override
    public String toString() {
        return userClasspath.toString();
    }
}
