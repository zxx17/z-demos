package org.zxx17.model2.algo.hot100;

/**
 * 148排序链表.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/20
 **/
public class SortList {

    /**
     * 思路1：双指针，慢指针指向的值遇到比较小的，则交换，比较大的则快指针继续向前直到链表结尾，然后慢指针向前一步，慢指针走到末尾则结束  超时了！！！！！！！！！！！！！
     */
    public S5.ListNode sortList(S5.ListNode head) {
        if (head == null || head.next == null) {
            return head; // 如果链表为空或只有一个节点，直接返回头结点
        }

        S5.ListNode current = head;
        while (current != null) {
            S5.ListNode minNode = current;
            S5.ListNode next = current.next;
            while (next != null) {
                if (next.val < minNode.val) {
                    minNode = next;
                }
                next = next.next;
            }
            swap(current, minNode); // 交换当前节点和最小节点的值
            current = current.next; // 移动到下一个节点
        }
        return head;
    }

    private void swap(S5.ListNode node1, S5.ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

}
