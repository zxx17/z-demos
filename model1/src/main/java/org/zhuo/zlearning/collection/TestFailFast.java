package org.zhuo.zlearning.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/18
 * <p>
 *
 * </p>
 */

public class TestFailFast {

    public static void main(String[] args) {
//        List<String> userNames = new ArrayList<String>(){{
//            add("zxx");
//            add("zxx");
//            add("zxx1");
//            add("zxx2");
//        }};
        List<String> userNames = new CopyOnWriteArrayList<>() {
            {
                this.add("zxx");
                this.add("zxx");
                this.add("zxx1");
                this.add("zxx2");
            }
        };

        Iterator<String> iterator = userNames.iterator();

        for (String userName : userNames) {
            if ("zxx".equals(userName)){
                userNames.remove(userName);
            }
        }
        System.out.println(userNames);

        // 但是迭代器不知道他修改了（在遍历的期间）
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
