package sort;

import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/6/9 11:08
 * @Description: 快速排序，核心思想是 一个位于数组最右侧的pivot 以及两个交换指针
 */
public class QuickSort {
    /**
     * 递归调用 递归出口是leftBound>=rightBound的时候
     * @param arr 待排序数组
     * @param leftBound 左边界，开始扫描的最左侧
     * @param rightBound 右边界，开始扫描的最右侧
     * @return left 即交换过后pivot的Index
     */
    public static void sort(int[] arr,int leftBound, int rightBound){
        if(leftBound>=rightBound){
            System.out.println(Arrays.toString(arr));
            return;
        }
        int mid=partition( arr,leftBound, rightBound);
        //右边排序
        sort(arr,mid+1,rightBound);
        //左边排序 注意，这个为mid，否则mid就没有参与排序 因为有些情况是 2 1 3 当最后一个值大于前面全部值
        //的时候，right left指针碰于1，此时，实际上是不满足1为真正pivot的情况。因此，mid需要再次进行partition
        sort(arr,leftBound,mid);
    }


    /**
     * 分界函数，将<pivot左侧的值放在Pivot左边，>pivot的放在右边
     * @param arr 待排序数组
     * @param leftBound 左边界，开始扫描的最左侧
     * @param rightBound 右边界，开始扫描的最右侧
     * @return left 即交换过后pivot的Index
     */
    static int partition(int[] arr,int leftBound, int rightBound) {
        int pivot = rightBound;
        int left = leftBound;
        int right = rightBound - 1;

        //当left==right的时候，pivot的左右已经全部交换完成
        while (left != right) {
            //两个while循环用于找到不符合partition规则的值的index
            while (right >left && arr[left] <= arr[pivot]) {
                left++;
            }
            while (right >left && arr[right] >= arr[pivot]) {
                right--;
            }
            swap(arr, left, right);

        }
        //当两个指针重合之后，即可以交换pivot和重合的位置
        //重合位置上的数字一定大于pivot 因为循环结束的要求一定是left移动到了right指针上，而right指针指向的值总是大于pivot的
        //if的原因是：若array[pivot]的值大于全部值的时候，两个指针就会在right的原始值碰面，若没有if,会直接触发交换
        if(arr[left]>arr[pivot]){
          swap(arr,pivot,left);
        }
        return left;
    }
    static int[] swap(int[] arr, int left, int right){
        int temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
        return arr;
    }
}
class QuickSortTest{
    public static void main(String[] args) {
        int[] array={2,1,3};
        int[] array1={7,3,2,8,1,9,5,4,6,10};
        int[] array3={7,3,2,8,1,9,5,4,6,0};
        QuickSort.sort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
