package test1;

/**
 * 二叉树的节点
 */
public class Node {
    int key;
    // 左节点
    Node left;

    // 又节点
    Node right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}
