package com.fun.business.sharon.biz.business.learn.leetcode;

/**
 * 逆序存储的数字求和
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以0开头。
 *
 * 如：
 * 2-4-3
 * 5-6-4
 * 等于
 * 7-0-8
 * 因为 342+465=807
 */
public class TwoSumRe {

    public static void main(String[] args) {



    }


}
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