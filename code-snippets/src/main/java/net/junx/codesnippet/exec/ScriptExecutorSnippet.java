package net.junx.codesnippet.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author junX
 * @date Apr 4, 2017 3:23:57 PM <br/>
 * @version
 *
 */
public class ScriptExecutorSnippet {

    public void execute(String command) throws Exception {
        String osName = System.getProperty("os.name");
        osName = osName.toUpperCase();

        Runtime rt = Runtime.getRuntime();

        Process process = null;

        if (osName.startsWith("WINDOW")) {
            // windows系统
            String[] execArgs = new String[3];
            execArgs[0] = "cmd.exe";
            execArgs[1] = "/C";
            execArgs[2] = command;
            process = rt.exec(execArgs);

        } else {
            // unix系统
            process = rt.exec(command);

        }

        // 打印错误信息
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
        errorGobbler.start();

        // 打印输出信息
        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");
        outputGobbler.start();

        // 获取返回值
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("命令脚本[" + command + "]执行正常结束!!");
        } else {
            System.err.println("命令脚本[" + command + "]执行出现错误!! 返回值=[" + exitVal + "]");
        }
    }

    class StreamGobbler extends Thread {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println(type + ">" + line);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        ScriptExecutorSnippet exe = new ScriptExecutorSnippet();
        exe.execute("ls");
        // exe.execute("dir d:\\yz\\");
        // exe.execute("dir");
        // exe.execute("cls");
    }
}
