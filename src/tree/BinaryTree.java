package tree;

import java.util.ArrayList;

/**
 * @Author: longsx
 * @DateTime: 2020/6/13 21:12
 * @Description: 二叉树
 */
public class BinaryTree {
    Node head;
    /**
     * 查询节点集合
     */
    ArrayList<Node> resultList;
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
        node2.right=node6;
        node4.left=node5;
        binaryTree.laterOrder();
        System.out.println("查找实验***************************************************");
        System.out.println(binaryTree.search(1,"1"));
        System.out.println("子数删除实验***************************************************");
        binaryTree.delNode(2);
        binaryTree.laterOrder();

    }
}