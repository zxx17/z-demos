package org.zxx17.model2.algo.let;

import java.util.HashMap;
import java.util.Map;

/**
 * 141环形链表.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/19
 **/
public class HasCycle {

    /**
     * hash表法，如果同一个节点出现两次，则是环形链表
     */
    public boolean hasCycle(S5.ListNode head) {
        Map<S5.ListNode, Integer> map = new HashMap<>();
        while (head != null) {
            if (map.containsKey(head)) {
                return true;
            }
            map.put(head, 1);
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针
     * 初始化指针1head 指针2head.next  指针1每次移动一步，指针2每次移动两步，如果指针2反过来追上指针1，则是环形链表
     */
    public boolean hasCycle2(S5.ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        S5.ListNode slow = head;
        S5.ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                // 快指针走到末尾，则不是环形链表
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


}
