package com.dewen.addTwoNumbers;


class Solution {
    public static ListNode addTwoNumbers() {
        // 递归
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        return add2(l1, l2, 0);

    }

    static ListNode add(ListNode l1, ListNode l2, int a) {

        // 都没next节点了，切不需要像前一位进1
        if (l1 == null && l2 == null && a == 0) return null;
        // 一个有一个没有的情况，需要先补个0节点
        if (l1 == null) l1 = new ListNode(0);
        if (l2 == null) l2 = new ListNode(0);

        int x = l1.val;
        int y = l2.val;
        int sum = x + y + a;
        // 表示十位数值
        a = sum / 10;
        // 保留个位数值给val变量
        ListNode result = new ListNode(sum % 10);
        // 递归调用
        result.next = add(l1.next, l2.next, a);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(addTwoNumbers());
//        int a = 18 / 10;
//        System.out.println(a);
    }

    static ListNode add2(ListNode l1, ListNode l2, int a) {
        // 处理节点val
        if (l1 == null && l2 == null && a == 0) return null;
        if (l1 == null) l1 = new ListNode(0);
        if (l2 == null) l2 = new ListNode(0);
        // 相加
        int sum = l1.val + l2.val + a;
        return new ListNode(sum % 10, add2(l1.next, l2.next, sum / 10));
    }
}