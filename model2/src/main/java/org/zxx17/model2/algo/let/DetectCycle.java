package org.zxx17.model2.algo.let;

import java.util.HashMap;
import java.util.Map;

/**
 * 142环形链表2.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/19
 **/
public class DetectCycle {


    /**
     * hash表法，如果同一个节点出现两次，则是环形链表
     * 对比141 这里返回的就是节点值
     */
    public S5.ListNode detectCycle(S5.ListNode head) {
        Map<S5.ListNode, Integer> map = new HashMap<>();
        while (head != null) {
            if (map.containsKey(head)) {
                return head;
            }
            map.put(head, 1);
            head = head.next;
        }
        return null;
    }


    /**
     * 快慢指针法
     */
    public S5.ListNode detectCycle2(S5.ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        S5.ListNode slow = head;
        S5.ListNode fast = head;

        // 使用快慢指针寻找相遇点
        // 相遇
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;

        } while (slow != fast);

        // 寻找环的入口
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // 或者 return fast
    }





}
