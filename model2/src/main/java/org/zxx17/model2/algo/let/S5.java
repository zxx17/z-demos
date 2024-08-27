package org.zxx17.model2.algo.let;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 相交链表.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/8
 **/
public class S5 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 相交链表
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA != null ? pA.next : headB;
            pB = pB != null ? pB.next : headA;
        }
        return pA;
    }

    /**
     * 反转链表
     */
    public static ListNode reverseList(ListNode head) {

        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;

    }

    /**
     * 回文链表
     */
    public static boolean isPalindrome(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();

        // 第一次遍历链表，将所有节点的值压入栈
        ListNode current = head;
        while (current != null) {
            stack.addLast(current.val);
            current = current.next;
        }

        // 第二次遍历链表，同时从栈中弹出并比较值
        current = head;
        while (!stack.isEmpty() && current != null) {
            if (current.val != stack.removeLast()) {
                return false;
            }
            current = current.next;
        }

        // 如果链表和栈同时结束，说明链表是回文链表
        return stack.isEmpty() && current == null;
    }


    /**
     * 主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        ListNode head = null;
        ListNode cur = null;
        while (sc.hasNext()){
            int value = sc.nextInt();
            if (value == -1){
                break;
            }
            // 创建新的节点
            ListNode newNode = new ListNode(value);
            if (head == null){
                head = newNode;
                cur = head;
            }else {
                cur.next = newNode;
                cur = cur.next;
            }
        }
        sc.close();


        ListNode listNode = reverseList(head);
        while (listNode != null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }


}
