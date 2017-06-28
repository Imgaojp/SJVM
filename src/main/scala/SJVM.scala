import classpath.ClassPath

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
  * Created by Imgaojp on 2017/6/26.
  */
object SJVM {
  def main(args: Array[String]): Unit = {
    val cmd = Cmd parseCmd args
    if (cmd.helpFlag)
      Cmd.printUsage()
    else if (cmd.versionFlag)
      print("version 0.0.1\n")
    else
      StartJvm(cmd)
  }

  def StartJvm(cmd: cmdArgs): Unit = {
    val cp = new ClassPath
    cp parse(cmd.XjreOption, cmd.cpOption)
    val classData = cp.readClass(cmd.className)
    if (classData==null) {
      printf("could not load main class %s\n", cmd.className)
      System.exit(1)
    }
    for (b <- classData){
      printf("%c\t",b)
    }
  }

}