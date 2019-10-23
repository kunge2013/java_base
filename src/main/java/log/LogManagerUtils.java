package log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogManagerUtils {
    // 初始化LogManager
    static {
        // 读取配置文件
        ClassLoader cl = LogManager.class.getClassLoader();
        InputStream inputStream = null;
        if (cl != null) {
            inputStream = cl.getResourceAsStream("log.properties");
        } else {
//            inputStream = LogManagerUtils.class.getClassLoader()
//                    .getSystemResourceAsStream("/log.properties");
            try {
                inputStream =  new FileInputStream("E:\\workspace\\java_base\\src\\resources\\log.properties");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        java.util.logging.LogManager logManager = java.util.logging.LogManager
                .getLogManager();
        try {
            // 重新初始化日志属性并重新读取日志配置。
            logManager.readConfiguration(inputStream);
        } catch (SecurityException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * 获取日志对象
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class clazz) {
        Logger logger = Logger
                .getLogger(clazz.getName());
        return logger;
    }

    public static void main(String[] args) {
        Logger logger = getLogger(LogManagerUtils.class);
        logger.info("hahahahh");
    }
}
