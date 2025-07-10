package cn.hgplan.linkedlist;

import static cn.hgplan.linkedlist.ListNode.buildList;
import static cn.hgplan.linkedlist.ListNode.printList;

public class SortListSolution {
    // 归并排序实现
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 快慢指针找中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; // 切断链表

        // 递归排序左右两部分
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // 合并有序链表
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // 测试入口
    public static void main(String[] args) {
        ListNode head = buildList(new int[]{4, 2, 1, 3}); // 使用工具类构建链表
        ListNode sorted = new SortListSolution().sortList(head);
        printList(sorted); // 打印结果
    }
}