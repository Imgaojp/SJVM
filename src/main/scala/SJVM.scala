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
    printf("classpath: %s \nclass: %s \nargs: %s \n",
      cmd.cpOption, cmd.className, cmd.args.mkString(","))
  }

}