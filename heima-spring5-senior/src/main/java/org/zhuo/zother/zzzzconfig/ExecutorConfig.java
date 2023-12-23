package org.zhuo.zother.zzzzconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/20
 * <p>
 * 为什么不推荐直接使用@Async？
 * 因为在Springboot环境中，@Async默认使用的线程池最大线程数是Integer.MAX，并且阻塞队列的大小也是Integer.MAX，这显然是不合理的，
 * 所以我们最好自己定义线程池，然后指定@Async的value属性。
 * </p>
 */

@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    /**
     * 核心线程数(默认线程数)
     */
    private final int corePoolSize = 10;
    /**
     * 最大线程数
     */
    private final int maxPoolSize = 20;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 60;
    /**
     * 缓冲队列大小
     */
    private final int queueCapacity = 10;

    @Bean
    public Executor asyncServiceExecutor() {
        log.info("=====start asyncServiceExecutor=====");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置空闲时间
        executor.setKeepAliveSeconds(keepAliveTime);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);
        //配置线程前缀名
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy（策略）：不在新线程中执行任务，而是让调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();
        return executor;
    }
}
