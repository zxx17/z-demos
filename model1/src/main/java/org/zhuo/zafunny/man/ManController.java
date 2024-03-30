package org.zhuo.zafunny.man;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/19
 * <p>
 *
 * </p>
 */

@RestController
@RequestMapping("/man")
public class ManController {


    @GetMapping("/sga")
    public boolean seeYouAgain() {
//        try {
//            Thread work1 = new Thread(ManController::openCloudMusic);
//            work1.join();
//            startMusic();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return true;
    }

//
//    private static void openCloudMusic() {
//        try {
//            String computerUserName = System.getProperty("user.name");
//            Runtime.getRuntime().exec("cmd /c start C:\\Users\\"+ computerUserName +"\\Desktop\\网易云音乐.lnk");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void startMusic() {
//        try {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_ALT);
//            robot.keyPress(KeyEvent.VK_P);
//            robot.keyRelease(KeyEvent.VK_P);
//            robot.keyRelease(KeyEvent.VK_ALT);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
