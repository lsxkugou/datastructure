package tree;

import java.util.ArrayList;

/**
 * @Author: longsx
 * @DateTime: 2020/6/13 21:12
 * @Description: 二叉树+线索化
 */
public class BinaryTree {
    Node head;
    /**
     * 查询节点集合
     */
    ArrayList<Node> resultList;
    /**
     * 线索二叉树进行遍历
     */
    public void scanMiddleOrder(){
        Node node=head;
        while(node!=null){
            //一直遍历 直到找到左树叶子节点
            while(node.left!=null&&node.isThreadedLeft==false){
                node=node.left;
            }

            //按照线索二叉树输出节点，一直到一个非叶子节点，即当前叶子节点的根
            while(node.right!=null&&node.isThreadedRight==true) {
                System.out.println(node);
                node=node.right;
            }
            //循环结束后，代表该节点为根节点，这个时候进行输出根节点
            System.out.println(node);
            //经过上一个循环，现在的node为根节点，这个时候就要遍历右子树，所以要切换到右边
            node=node.right;

        }
    }
    /**
     * 按照中序遍历设置线索二叉树
     * 重载
     */
    void ThreadTree(){
        ThreadTree(head);
        for(int i=0;i<resultList.size();i++){
            if(i>0) {
                if (resultList.get(i).isThreadedLeft == true) {
                    resultList.get(i).left = resultList.get(i - 1);
                }
            }
            if(i<resultList.size()-1) {
                if (resultList.get(i).isThreadedRight == true) {
                    resultList.get(i).right = resultList.get(i + 1);
                }
            }
        }
    }

    /**
     * 将所有中序遍历得到的有了顺序的节点存入resultList 并设置左右标记值
     * @param node 节点
     */
    void ThreadTree(Node node){
        if(node.left!=null){
            ThreadTree(node.left);
        }
        if(node.left==null) {
            node.isThreadedLeft = true;
        }
        resultList.add(node);
        if(node.right!=null){
            ThreadTree(node.right);
        }
        if(node.right==null) {
            node.isThreadedRight = true;
        }
    }

    public BinaryTree(Node head) {
        this.head = head;
        resultList=new ArrayList<Node>();
    }
    /**
     * 前序遍历
     */
    public void preOrder(){
      head.preOrder();
    }

    /**
     * 中序遍历
     */
    public void middleOrder(){
    head.middleOrder();
    }
    /**
     * 后序遍历
     */
    public void laterOrder(){
      head.laterOrder();

    }

    /**
     * 重载方法
     * @param id id
     * @param data data
     * @return 结果集
     */
    public ArrayList<Node> search(int id,String data){
        search(head,id,data);
        return resultList;
    }
    /**
     * 查找方法 重载可以消除一个参数
     * @param node 节点
     * @param id 查找id
     * @param data 查找数据域
     */
    public void search(Node node ,int id,String data){
        if(id==node.id||data.equals(node.data)){
            resultList.add(node);
        }
        if(node.left!=null){
            search(node.left,id,data);
        }
        if(node.right!=null){
            search(node.right,id,data);
        }
    }

    /**
     * 子树或叶子节点直接删除
     * @param id id
     */
    public void delNode(int id){
        head.delNode(id);
    }
}
class Node{
    int id;
    String data;
    Node left;
    Node right;

    boolean isThreadedLeft=false;
    boolean isThreadedRight=false;

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }
    public void delNode(int id){
        if(this.right.id==id){
            this.right=null;
            return;
        }
        if(this.left.id==id){
            this.left=null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(id);
        }
        if(this.right!=null){
            this.right.delNode(id);
        }

    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder(){
        if(this.left!=null){
            this.left.middleOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.middleOrder();
        }
    }
    /**
     * 后序遍历
     */
    public void laterOrder(){
        if(this.left!=null){
            this.left.laterOrder();
        }
        if(this.right!=null){
            this.right.laterOrder();
        }
        System.out.println(this);

    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
class BinaryTreeTest{
    public static void main(String[] args) {
        System.out.println("添加实验***************************************************");
        Node node1=new Node(1,"1");
        Node node2=new Node(2,"2");
        Node node3=new Node(3,"3");
        Node node4=new Node(4,"4");
        Node node5=new Node(5,"5");
        Node node6=new Node(6,"6");
        BinaryTree binaryTree = new BinaryTree(node1);
        binaryTree.head.left=node2;
        binaryTree.head.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        binaryTree.middleOrder();
//        System.out.println("查找实验***************************************************");
//        System.out.println(binaryTree.search(1,"1"));
//        System.out.println("子数删除实验***************************************************");
//        binaryTree.delNode(2);
//        binaryTree.laterOrder();
        System.out.println("线索二叉树实验，线索化完后不能使用常规遍历方法****************************************************");
        binaryTree.ThreadTree();
        binaryTree.scanMiddleOrder();

    }
}