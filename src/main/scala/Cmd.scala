import org.apache.commons.cli._

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
object Cmd {
  /**
    * parse Cmd args
    *
    * @param args
    * @return parsed args
    */
  def parseCmd(args: Array[String]): cmdArgs = {
    val parser = new BasicParser
    val options = new Options
    options addOption("version", false, "print version and exit")
    options addOption("?", "help", false, "print help message")
    options addOption("cp", "classpath", true, "classpath")
    options addOption("XjreOption", true, "path to jre")
    val commandLine = parser parse(options, args)
    val commandLineArgs = commandLine getArgs

    cmdArgs(
      helpFlag = commandLine.hasOption("help") || commandLine.hasOption("?") || commandLineArgs.isEmpty,
      versionFlag = commandLine hasOption "version",
      cpOption = if (commandLine hasOption "cp") commandLine getOptionValue "cp" else if (commandLine hasOption "classpath") commandLine getOptionValue "classpath" else "",
      XjreOption = if (commandLine hasOption "XjreOption") commandLine getOptionValue "XjreOption" else "",
      className = if (commandLineArgs nonEmpty) commandLineArgs(0) else "",
      args = if (commandLineArgs.length > 1) (commandLineArgs drop 1).toSeq else Seq.empty
    )


    //    val helpFlag = commandLine.hasOption("help") || commandLine.hasOption("?") || commandLineArgs.isEmpty
    //    val versionFlag = commandLine hasOption "version"
    //    val cpOption =
    //      if (commandLine.hasOption("cp"))
    //        commandLine.getOptionValue("cp")
    //      else if (commandLine.hasOption("classpath"))
    //        commandLine.getOptionValue("classpath")
    //      else ""
    //    val jreOption =
    //      if (commandLine.hasOption("jreOption"))
    //        commandLine.getOptionValue("jreOption")
    //      else ""
    //    val className = if (commandLineArgs nonEmpty) commandLineArgs(0) else ""
    //    //    val cmdArgs: Array[String] = if (commandLineArgs.length > 1) commandLineArgs drop 1 else Array("")
    //    val cmdArgs: Seq[String] = if (commandLineArgs.length > 1) (commandLineArgs drop 1).toSeq else Seq.empty
    //
    //
    //
    //    Map(
    //      "helpFlag" -> helpFlag,
    //      "versionFlag" -> versionFlag,
    //      "cpOption" -> cpOption,
    //      "jreOption" -> jreOption,
    //      "classname" -> className,
    //      "args" -> cmdArgs
    //    )
  }

  /**
    * print usage
    */
  def printUsage(): Unit = {
    print("Usage: java [-options] class [args...]\n")
  }
}

/**
  * A structure to return parsed args
  *
  * @param helpFlag
  * @param versionFlag
  * @param cpOption
  * @param XjreOption
  * @param className
  * @param args
  */
case class cmdArgs(
                    helpFlag: Boolean,
                    versionFlag: Boolean,
                    cpOption: String,
                    XjreOption: String,
                    className: String,
                    args: Seq[String]
                  )
