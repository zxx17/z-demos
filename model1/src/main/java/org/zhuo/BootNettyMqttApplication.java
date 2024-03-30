package org.zhuo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zhuo.zlearning.mqtt.BootNettyMqttServerThread;


/**
 * 蚂蚁舞
 */
@SpringBootApplication
public class BootNettyMqttApplication implements CommandLineRunner {
    public static void main( String[] args ) {
        SpringApplication app = new SpringApplication(BootNettyMqttApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 启动  1883
        int port = 1883;
        BootNettyMqttServerThread bootNettyMqttServerThread = new BootNettyMqttServerThread(port);
        bootNettyMqttServerThread.start();
    }
}