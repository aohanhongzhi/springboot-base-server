package hxy.base.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
    private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        log.info("\n<============ \uD83D\uDE80  JAVA版本:{}  CPU核心数:{}  \uD83D\uDE80 ============>", System.getProperty("java.version"), Runtime.getRuntime().availableProcessors());
        SpringApplication.run(ServerApplication.class, args);
        shutdownHook();
    }

    /**
     * 程序关闭监听函数，程序正常关闭可以邮件通知
     */
    private static void shutdownHook() {
        Thread shutdownThread = new Thread(() -> {
            //  发送邮件,监听服务关闭
            log.error("\n====>后端服务正在关闭,当前程序启动时间");
        });
        shutdownThread.setName("app-shutdown@thread");
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }

}
