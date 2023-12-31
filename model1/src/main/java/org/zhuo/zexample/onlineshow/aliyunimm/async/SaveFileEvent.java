package org.zhuo.zexample.onlineshow.aliyunimm.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/20
 * <p>
 *  接收保存文件事件
 * </p>
 */

@Slf4j
public class SaveFileEvent extends ApplicationEvent {


    public SaveFileEvent(Object source) {
        super(source);
        log.info("=====接收到保存文件事件=====");
    }
}
