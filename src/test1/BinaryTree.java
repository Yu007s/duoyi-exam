package test1;

/**
 * 自定义二叉树
 */
public class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    /**
     * 向二叉树中插入一个节点
     * @param key 要插入的节点值
     */
    void insert(int key) {
        root = insertRecursive(root, key);
    }

    /**
     * 在给定的二叉树中递归插入一个节点
     * @param root 当前根节点
     * @param key 要插入的节点值
     * @return 更新后的根节点
     */
    Node insertRecursive(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRecursive(root.left, key);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right, key);
        }

        return root;
    }

    /**
     * 在二叉树中搜索指定值的节点
     * @param root 当前根节点
     * @param key 要搜索的节点值
     * @return 找到的节点，如果未找到则返回 null
     */
    Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    /**
     * 删除二叉树中指定值的节点
     * @param key 要删除的节点值
     */
    void delete(int key) {
        root = deleteRecursive(root, key);
    }

    /**
     * 在给定的二叉树中递归删除指定值的节点
     * @param root 当前根节点
     * @param key 要删除的节点值
     * @return 更新后的根节点
     */
    Node deleteRecursive(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRecursive(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRecursive(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);

            root.right = deleteRecursive(root.right, root.key);
        }

        return root;
    }

    /**
     * 找到给定二叉树中最小值的节点值
     * @param root 当前根节点
     * @return 最小值节点的值
     */
    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    /**
     * 中序遍历二叉树（升序输出）
     */
    void inorder() {
        inorderRecursive(root);
    }

    /**
     * 在给定的二叉树中递归进行中序遍历
     * @param root 当前根节点
     */
    void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("原始树:");
        tree.inorder();
        System.out.println();

        System.out.println("\n查询节点 40:");
        Node result = tree.search(tree.root, 40);
        if (result != null) {
            System.out.println("节点 " + result.key + " 已找到");
        } else {
            System.out.println("节点未找到");
        }

        System.out.println("\n删除节点 30:");
        tree.delete(30);
        System.out.println("删除后的树:");
        tree.inorder();
    }
}
