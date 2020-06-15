package tree.usageoftree;
import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/6/15 17:49
 * @Description: 堆排序 感觉是改良的冒泡，将各个堆的最大值冒泡到堆顶，然后最大值与最后一个未排序的节点互换位置
 * 重复上述步骤
 */
public class HeapSort {
    public static void sort(int[] arr){
        //定义每次排序从哪个非叶子节点的length 每次循环之后length-1
        int length=arr.length-1;
        for(int i = 0; i <arr.length-1; i++){
            //每次调整堆的父节点下标
            int parent = (length-1)/2;
            //循环调整堆结构，将最大的放置到堆顶
            for(; parent>=0; parent--){
                //处理产生的越界问题
                int leftChild;
                int rightChild;
                //说明此时为为单分支排列 解决了左右子节点是否为单节点判断问题
                if(parent*2+1>length-1){
                    leftChild=parent*2+1;
                    rightChild=parent*2+1;
                }else {
                    leftChild=parent*2+1;
                    rightChild=parent*2+2;
                }
                int temp = Math.max(arr[leftChild], arr[rightChild]);
                //已经说明子节点中有大于父节点的值
                if(temp>arr[parent]){
                    if(arr[leftChild]>=arr[rightChild]){
                        arr[leftChild]=arr[parent];
                    }else{
                        arr[rightChild]=arr[parent];
                    }
                    arr[parent]=temp;
                }
             }
            //最大值已经到了堆顶，这个时候与最后一个节点互换位置
            int swap = arr[0];
            arr[0] = arr[length];
            arr[length] = swap;

            length--;

         }
    }
}
class  HeapSortTest{
    public static void main(String[] args) {
        int[] arr={6,8,7,9,0,1,3,2,4,5,11,-1};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
