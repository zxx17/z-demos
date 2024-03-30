package org.zhuo.zlearning.mqtt;


/**
 *  蚂蚁舞
 */
public class BootNettyMqttServerThread extends Thread {

    private final int port;

    public BootNettyMqttServerThread(int port){
        this.port = port;
    }

    public void run() {
        BootNettyMqttServer bootNettyMqttServer = new BootNettyMqttServer();
        bootNettyMqttServer.startup(this.port);
    }

}