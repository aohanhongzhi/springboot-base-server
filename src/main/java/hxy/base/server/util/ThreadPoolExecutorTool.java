package hxy.base.server.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author eric
 * @description
 * @date 2021/12/23
 */
public class ThreadPoolExecutorTool {
    /**
     * 阿里巴巴建议自己手动新建线程池，一定要指定最大线程数。
     * 这里的线程池maximumPoolSize依据CPU核心数来确定核心线程数有多少个。一般是核心数+1
     *
     * 计算密集型和IO密集型：看这个线程实际用途是啥，如果是计算密集型，那么可以少设置一点线程数。
     * 如果是IO密集型，那么可以多设置一点线程数，例如两倍的CPU核心数
     *
     */
    public static ExecutorService POOL = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors(),
            15L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2048), Executors.
            defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
}
