package cn.xiewei.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 蛇形打印二叉树
 */
public class ZlevelOrderTree {

    public static void main(String[] args) {
        TreeNode t=null;
        /*初始化方式1：广义优先法初始化（这种初始化树的方式更容易理解）*/
        List<TreeNode> nodelist = new LinkedList<>();
        createBinaryTreeByBreadth(new Integer[] { 0, 1, 2, 3, 4, 5,null, null, null, 9, 10 },nodelist) ;
        //       createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,9 },nodelist) ;
        t = nodelist.get(0);
        System.out.println("0 , 2 , 1 , 3 , 4 , 5 , 10 , 9 , Disconnected from the target VM, address: '127.0.0.1:12774', transport: 'socket'\n");

        ZlevelOrder01(t);//蛇形打印二叉树
        System.out.println();
        levelOrder02(t);//优于第一种方式，没有使用双向队列

        // todo 有问题 System.out.println("栈后序遍历");
    }

    /**
     * 优于第一种方式，没有使用双向队列
     * @param root
     */
    public static void levelOrder02(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root == null) return ;
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = len - 1 ;i >= 0 ;i--){//奇数层，由于偶数层是从右向左传入节点，那么要反着访问
                TreeNode node = queue.remove(i);
                System.out.print (node.data+" , ");
                if(node.leftChild != null) queue.add(node.leftChild);
                if(node.rightChild != null) queue.add(node.rightChild);
            }

            if(queue.size() == 0) break;
            len = queue.size();
            for(int i = len - 1 ;i >= 0 ;i--){//偶数层，由于奇数层是从左向右传入节点，那么也要反着访问
                TreeNode node = queue.remove(i);//移除下标为i的元素，从后面往前删除
                System.out.print (node.data+" , ");
                if(node.rightChild != null) queue.add(node.rightChild);
                if(node.leftChild != null) queue.add(node.leftChild);
            }
        }
    }

    
    /**
     * 蛇形打印二叉树
     * @param root
     */
    public static void ZlevelOrder01(TreeNode root) {
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


}
