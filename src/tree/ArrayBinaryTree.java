package tree;

/**
 * @Author: longsx
 * @DateTime: 2020/6/14 15:12
 * @Description: 顺序二叉树
 * 左子节点 2n+1 右子节点 2n+2
 */
public class ArrayBinaryTree {
    private static void preOrder(int[] arr){
        preOrder(arr,0);
    }
    public static void preOrder(int[] arr,int n){
        //输出根节点
        System.out.printf("%d\t",arr[n]);
        //进行左子树递归
        if (2*n+1<arr.length){
            preOrder(arr,2*n+1);
        }
        //进行右递归调用
        if (2*n+2<arr.length){
            preOrder(arr,2*n+2);
        }
    }
}
class ArrayBinaryTreeTest{
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree.preOrder(arr,0);
    }
}
