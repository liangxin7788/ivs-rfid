package com.fun.business.sharon.biz.business.learn.arithmetic;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 层序遍历demo
 */
public class LevelOrder {

    public static void main(String[] args) {
        // 创建二叉树
        MyTreeNode treeNode = new MyTreeNode("A");
        treeNode.left = new MyTreeNode("B");
        treeNode.right = new MyTreeNode("C");
        treeNode.left.left = new MyTreeNode("D");
        treeNode.left.left.right = new MyTreeNode("G");

        treeNode.right.left = new MyTreeNode("E");
        treeNode.right.right = new MyTreeNode("F");

        //执行层序遍历方法
        levelOrder(treeNode);

    }

    private static void levelOrder(MyTreeNode treeNode) {
        if (treeNode == null)
            return;
        // 利用队列先进先出的特性
        Queue<MyTreeNode> queue = new LinkedBlockingQueue<>();
        MyTreeNode curr;
        queue.add(treeNode);
        while (!queue.isEmpty()){
            curr = queue.remove();
            System.out.println(curr.value);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }

    }

}
class MyTreeNode{

    String value;

    MyTreeNode left;

    MyTreeNode right;

    public MyTreeNode(String value){
        this.value = value;
    }

}