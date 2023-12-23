package org.zhuo.zother.onlineshow.aliyunimm.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/20
 * <p>
 * 监听 保存文件事件
 * </p>
 */

@Slf4j
@Component
public class SaveFileEventListener implements ApplicationListener<SaveFileEvent> {


    /**
     * 处理保存文件事件
     * @param event 事件信息
     */
    @Async("asyncServiceExecutor")
    @Override
    public void onApplicationEvent(SaveFileEvent event) {
        log.info("=====处理保存文件事件=====");
        MultipartFile file = (MultipartFile) event.getSource();

    }

}
