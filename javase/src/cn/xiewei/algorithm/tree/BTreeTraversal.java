package cn.xiewei.algorithm.tree;

import java.util.*;

public class BTreeTraversal {
    static class TreeNode {
        private Integer data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

    }

    /**
     * 层次遍历（广度优先方式），构建一个二叉树
     * 传参数的时候，广度优先，更容易让人理解一点
     * @param inputList
     * @return
     */
    public static void createBinaryTreeByBreadth(Integer[] inputList, List<TreeNode> nodelist) {

        /*1.【生成树的所有节点】：数组中的每个值，都构建一个TreeNode节点对象，存储到nodelist集合中*/
        for (int nodeindex = 0; nodeindex < inputList.length; nodeindex++) {
            TreeNode treeNode=null;
            if (inputList[nodeindex]!=null){
                treeNode = new TreeNode(inputList[nodeindex]);
            }
            nodelist.add(treeNode);
        }
        /*1.【树的各节点之间建立引用关系】：为nodelist集合中，各TreeNode节点中的左右子节点构建，引用关系
        * 2.循环遍历二叉树的父节点，为每个父节点设置，左子节点和右子节点
        * 3.二叉树 父节点的数量为：nodelist.size() / 2 - 1
        * 4.如果，一棵树不是完成二叉树，倒数第二层的节点，没有子节点，那么这个节点也是叶子节点，不是父节点
        * 4.二叉树 的最后一个父节点要单独处理，因为，可能没有右子树，这样，数组会为null，存在数组下标越界的风险
        * */
        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
            //给所有父节点设定子节点
            //编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1
            nodelist.get(index).setLeftChild(nodelist.get(index * 2 + 1));
            nodelist.get(index).setRightChild(nodelist.get(index * 2 + 2));
        }
        //最后一个父节点：因为最后一个父节点可能没有右孩子，所以单独处理
        int index = nodelist.size() / 2 - 1;
        //为最后一个父节点，设置左孩子
        nodelist.get(index).setLeftChild(nodelist.get(index * 2 + 1));
        //为最后一个父节点，设置右孩子：如果数组的长度为奇数"最后一个父节点"才有右孩子
        if (nodelist.size() % 2 == 1) {
            nodelist.get(index).setRightChild(nodelist.get(index * 2 + 2));
        }
    }

    /**
     * 先序遍历（深度优先）方式，构建一个二叉树
     *
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        // 用递归的形式构建树，其实和先序遍历的顺序是一样的
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }



    /**
     * 先序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null ) {
            return;
        }

        System.out.print(node.data + " ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    /**
     * 先序遍历 使用栈实现
     */
    public static void preStackOrder(TreeNode node) {
        if (node == null || node.data==null) {
            return;
        }
        // 根左右
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null ) {
                System.out.print(node.data);
                // 记录下访问过的节点
                stack.push(node);
                node = node.leftChild;
            }
            // 当左边都遍历完
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.rightChild;
            }
        }

    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.leftChild);
        System.out.print(node.data + " ");
        inOrder(node.rightChild);
    }

    /**
     * 中序遍历使用栈实现
     *
     * @param node
     */
    public static void inStackOrder(TreeNode node) {
        // 左 中 右
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.data);
                node = node.rightChild;
            }
        }

    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.print(node.data + " ");
    }

    /**
     * todo 待定栈后序遍历
     *
     * @param node
     */
    public static void postStackOrder(TreeNode node) {
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            // 左右中
            while (node != null) {
                if (node != lastNode) {
                    System.out.println("将node放到push:" + node.data);
                    stack.push(node);
                }
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println("将node Pop:" + node.data);
                if (lastNode == node.rightChild) {
                    System.out.print("node左节点为空:" + node.data);
                    lastNode = node;
                } else {
                    node = node.rightChild;
                }
            }
        }
    }

    /**
     * 二叉树 层次遍历，广度优先
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        /*
        * LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
        * 压入元素(添加)：add()、offer()
            相同：未超出容量，从队尾压入元素，返回压入的那个元素。
            区别：在超出容量时，add()方法会对抛出异常，offer()返回false

          弹出元素(删除)：remove()、poll()
            相同：容量大于0的时候，删除并返回队头被删除的那个元素。
            区别：在容量为0的时候，remove()会抛出异常，poll()返回false
        * */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//指定的元素插⼊此队列
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();//获取并移除此队列的头，如果此队列为空，则返回 null
            System.out.print(node.data + " ");
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.offer(node.rightChild);

            }
        }

    }

    /**
     * 二叉树 层次遍历 知道自己遍历的层数
     * <p>
     * 思路：采取使用一个记录每层个数的一个值来判断什么时候完成遍历的。
     * 我们首先就应该在循环外面把根节点放入队列，然后用此时队列的大小做循环取数，并将子节点放到队列里面
     * 循环完之后，一层也就结束了，此时该层所有子节点也都入队了，更新下一层的每层个数
     *
     *
     * @param root
     */
    public static void levelOrderCount(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);// 向队列尾部插入元素
        int n = queue.size();
        while (!queue.isEmpty()) {

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.data + " ");
                if (node.leftChild != null) {
                    queue.offer(node.leftChild);
                }

                if (node.rightChild != null) {
                    queue.offer(node.rightChild);
                }
            }
            System.out.println("一层结束");
            n = queue.size();
        }
    }

    /**
     * 蛇形打印二叉树
     * @param root
     */
    public static void ZlevelOrder(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if(root != null) deque.add(root);
        while(!deque.isEmpty()) {
            // 打印奇数层
            for(int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                //打印本层节点，然后，将本层节点的子节点加入队列中，为打印下一层做准备
                System.out.print (node.data+" , ");
                // 先左后右加入下层节点
                if(node.leftChild != null)
                    deque.addLast(node.leftChild);
                if(node.rightChild != null)
                    deque.addLast(node.rightChild);
            }
            if(deque.isEmpty()) break; // 若为空则提前跳出
            // 打印偶数层
            for(int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                System.out.print (node.data+" , ");

                // 先右后左加入下层节点
                if(node.rightChild != null) deque.addFirst(node.rightChild);
                if(node.leftChild != null) deque.addFirst(node.leftChild);
            }
        }
    }


    public static void main(String[] args) {
        TreeNode t=null;
        /*初始化方式1：广义优先法初始化（这种初始化树的方式更容易理解）*/
        List<TreeNode> nodelist = new LinkedList<>();
        createBinaryTreeByBreadth(new Integer[] { 0, 1, 2, 3, 4, 5,null, null, null, 9, 10 },nodelist) ;
        //       createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,9 },nodelist) ;
        t = nodelist.get(0);
        BTreeTraversal.preOrder(t);
        System.out.println("先序遍历");
        preStackOrder(t);
        System.out.println("栈先序遍历");
        BTreeTraversal.inOrder(t);
        System.out.println("中序遍历");
        inStackOrder(t);
        System.out.println("栈中序遍历");
        BTreeTraversal.postOrder(t);
        System.out.println("后序遍历");
        postStackOrder(t);
        levelOrder(t);
        System.out.println("层次遍历");
        levelOrderCount(t);
        System.out.println("层次遍历知道自己的层数");

        ZlevelOrder(t);//蛇形打印二叉树


        // todo 有问题 System.out.println("栈后序遍历");
    }

}
