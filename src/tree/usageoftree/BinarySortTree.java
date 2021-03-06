package tree.usageoftree;
import java.util.ArrayList;

/**
 * @Author: longsx
 * @DateTime: 2020/6/21 15:43
 * @Description: 二叉排序树 left<middle<right AVL树
 *
 */
public class BinarySortTree {
    //右调整时的AVL平衡因子 左-右>1
    final static int BALANCE_FACTOR_RIGHT= 1;

    //左调整时的AVL平衡因子 左-右<-1
    final static int BALANCE_FACTOR_LEFT = -1;

    BstNode head;
    public void preOrder(){
        head.preOrder(head);
    }
    public void middleOrder(){
        head.middleOrder(head);
    }
    public BinarySortTree(BstNode head) {
        this.head = head;
    }
    public void add(BstNode bstNode){
        head.add(bstNode);
        //进行AVL调整
        avl();
    }
    private void avl(){
        if(head.left!=null&&head.right!=null){
            //左-右>BALANCE_FACTOR_RIGHT 进行右旋转
            if(BstNode.height(head.left)-BstNode.height(head.right)>BALANCE_FACTOR_RIGHT){
                head.rightRotate();
            }
            //左-右<BALANCE_FACTOR_LEFT 进行左旋转
            if(BstNode.height(head.left)-BstNode.height(head.right)<BALANCE_FACTOR_LEFT){
                head.leftRotate();
            }
        }
    }
    public BstNode[] searchOne(int searchValue){
        BstNode[] bstNodes = new BstNode[2];
        return head.searchOne(head,searchValue,bstNodes);
    }

    /**
     * AVL平衡二叉树调整
     */
    public void AVL(){}

    /**
     * 删除节点
     * 分为三种情况
     * 1.删除的为叶子节点，直接删除
     * 2.删除的节点仅有一个子节点
     *      2.1若该子节点为左子节点，则直接代替被删除节点
     *      2.2若该子节点为右子节点，则直接替换被删除节点
     * 3.删除的节点有双节点
     * @param value
     */
    public void deleteNode(int value){
        //1.遍历查找该节点，判断三种情况
        BstNode[] bstNodes = searchOne(value);
        BstNode parent = bstNodes[0];
        BstNode bstNode = bstNodes[1];
        //1.待删除节点为叶子节点
        if(bstNode.left==null&&bstNode.right==null){
            //直接删除
            if(parent.left==bstNode){
                parent.left=null;
            }
            if(parent.right==bstNode){
                parent.right=null;
            }
        }
        //2.待删除节点有一个子节点
        if((bstNode.left==null&&bstNode.right!=null)||(bstNode.left!=null&&bstNode.right==null)) {
            //若是左子节点，则用左子节点替代，若是右子节点，则用右子节点替代
            if(parent.left==bstNode){
                parent.left=bstNode.left;
            }
            if(parent.right==bstNode){
                parent.right=bstNode.right;
            }
        }
        //3.待删除节点有两个子节点
        if(bstNode.left!=null&&bstNode.right!=null){
            //在左子树选择最大的节点进行代替，其实也就是从根节点左拐一次之后一直向右遍历 该节点一定比待删除节点小但是在左树中是最大的
            BstNode biggestNodeOfLeftTree = bstNode.left;
            //找到该节点
            while (biggestNodeOfLeftTree.right != null) {
                biggestNodeOfLeftTree = biggestNodeOfLeftTree.right;
            }
            //删除该biggestNodeOfLeftTree 若该节点有左子节点，则按照1.待删除节点为叶子节点进行删除 注：biggestNodeOfLeftTree一定没有右子节点
            deleteNode(biggestNodeOfLeftTree.value);
            //将biggestNodeOfLeftTree替换待删除节点
            biggestNodeOfLeftTree.left = bstNode.left;
            biggestNodeOfLeftTree.right = bstNode.right;
            //如果要删除的点正好是整个bstTree的根节点，则必须进行将head重新赋值，不然遍历还是从原先的根节点遍历
            if(head==bstNode){
                head = biggestNodeOfLeftTree;
            }
            bstNode = biggestNodeOfLeftTree;
        }
    }


}
class BstNode implements Comparable<BstNode>{
    int value;
    BstNode left;
    BstNode right;

    public BstNode(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(BstNode o) {
        return this.value-o.value;
    }

    /**
     * 二叉排序树增加节点
     * @param bstNode 节点
     */
     void add(BstNode bstNode){
        //若当前节点的值大于本节点
        if (bstNode.compareTo(this)>0){
            //可以认为这个是函数出口
            if (this.right==null){
                this.right=bstNode;
            }else{
                //向右一直递归添加
                this.right.add(bstNode);
            }
        }else {//小于或等于的情况
            //可以认为这个是函数出口
            if (this.left==null){
                this.left=bstNode;
            }else{
                //向右一直递归添加
                this.left.add(bstNode);
            }
        }
    }

    @Override
    public String toString() {
        return "BstNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     * @param bstNode 节点
     */
    void preOrder(BstNode bstNode){
        System.out.println(this.toString());
        if(this.left!=null){
            this.left.preOrder(this.left);
        }
        if(this.right!=null){
            this.right.preOrder(this.right);
        }
    }

    /**
     * 左旋转方法
     */
    void leftRotate() {
    //创建新的结点，以当前根结点的值
        BstNode newNode = new BstNode(value);
    //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
    //把新的结点的右子树设置成带你过去结点的右子树的左子树
        newNode.right = right.left;
    //把当前结点的值替换成右子结点的值
        value = right.value;
    //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
    //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;
    }

    /**
     * 右旋转方法
     */
     void rightRotate() {
        BstNode newNode = new BstNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left. value;
        left = left.left;
        right = newNode;
    }


    /**
         * 中序遍历 在bst树中也就是从大到小遍历
         * @param bstNode 节点
         */
    void middleOrder(BstNode bstNode){
        if(this.left!=null){
            this.left.middleOrder(this.left);
        }
        System.out.println(this.toString());
        if(this.right!=null){
            this.right.middleOrder(this.right);
        }
    }
    /**
     * 查找单一节点 返回父亲和孩子 0 为父节点  1 为值相等的节点
     * @param bstNode 节点
     */
    BstNode[] searchOne(BstNode bstNode,int searchValue,BstNode[] bstNodes){
        //若head就是需要找的节点
        if(this.value==searchValue){
            bstNodes[1] = this;
            return bstNodes;
        }
        if(this.left!=null&&this.left.value==searchValue){
            bstNodes[0] = this;
            bstNodes[1] = this.left;
            return bstNodes;
        }
        if(this.left!=null&&this.right.value==searchValue){
            bstNodes[0] = this;
            bstNodes[1] = this.right;
            return bstNodes;
        }
        if(this.left!=null){
            this.left.searchOne(this.left,searchValue,bstNodes);
        }
        if(this.right!=null){
            this.right.searchOne(this.right,searchValue,bstNodes);
        }
        return bstNodes;
    }


    /**
     * 返回以该节点为根节点树的高度
     * @param bstNode 节点
     * @return 树高
     */
    static int height(BstNode bstNode){
        int height = 0;
        ArrayList<Integer> max = new ArrayList<Integer>();
        max = heightTree(max,bstNode,height);
        //初始化maxHeight
        int maxHeight = max.get(0) ;
        for (Integer h : max) {
            if(maxHeight<=h){
                maxHeight = h;
            }
        }
        return maxHeight++;
    }

    /**
     * 左子树高度
     * @param max 存放所有叶子节点的高度
     * @param bstNode 节点
     * @param height 高度
     * @return 高度
     */
    private static ArrayList<Integer> heightTree(ArrayList<Integer> max,BstNode bstNode,int height){
       //若为叶子节点，则计入数组中
        if(bstNode.right==null&&bstNode.left==null){
            max.add(height);
            //记录叶子节点高度后清除height
            height = 0;
        }
        //向左递归
        if(bstNode.left!=null){
            height++;
            heightTree(max,bstNode.left,height);
        }
        //向右递归
        if(bstNode.right!=null){
            height++;
            heightTree(max,bstNode.right,height);
        }
        return max;
    }


}
class BinarySortTreeTest{
    public static void main(String[] args) {
        //测试 6,4,8,2,7
        BinarySortTree binarySortTree = new BinarySortTree(new BstNode(5));
        binarySortTree.add(new BstNode(6));
        binarySortTree.add(new BstNode(4));
        BstNode bstNode8 = new BstNode(8);
        binarySortTree.add(bstNode8);
        binarySortTree.add(new BstNode(2));
        binarySortTree.add(new BstNode(7));
        binarySortTree.preOrder();
        System.out.println("中序从小到大遍历********************************************");
        binarySortTree.middleOrder();
        System.out.println("删除根节点5之后进行中序从大到小遍历*****************************");
        binarySortTree.deleteNode(5);
        binarySortTree.middleOrder();
        System.out.println("删除叶子节点7之后进行中序从大到小遍历***************************");
        binarySortTree.deleteNode(7);
        binarySortTree.middleOrder();
        binarySortTree.add(new BstNode(7));
        BstNode node9 = new BstNode(9);
        binarySortTree.add(node9);
        System.out.println(BstNode.height(bstNode8));


    }
}