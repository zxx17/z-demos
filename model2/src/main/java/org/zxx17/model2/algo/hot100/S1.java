package org.zxx17.model2.algo.hot100;

/**
 * 两两交换链表中的节点.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/5
 **/
public class S1 {


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        return swapPairs01(head);
    }

    /**
     * 递归法
     */
    public ListNode swapPairs01(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs01(newHead.next);
        newHead.next = head;
        return newHead;
    }




}
