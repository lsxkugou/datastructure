package sort;

import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/6/11 11:24
 * @Description: 归并排序
 */
public class MergeSort {

    /**
     * 排序算法
     * @param arr 待排序数组
     * @param left  指定从左边第几个位置开始排序
     * @param right 指定到右边的第几个位置+1
     */
    public static void sort(int[] arr,int left,int right){
        //递归出口
        if(left==right){
            return;
        }
        int mid=(left+right)/2;
        //左边排序
        sort(arr,left,mid);
        //右边排序
        sort(arr,mid+1,right);

        merge(arr,left,mid+1,right);


    }

    /**
     * merge进行两个有序数组的合并 注意：数组分为两边之后，两边必须为有序的才能进行排序
     * @param arr 传入数组
     */
    static void merge(int[] arr,int left,int right,int rightBound){
        //左指针
        int i=left;
        //中间位置
        int mid=(rightBound+left)>>1;
        //右指针
        int j=right;
        /**
         * 注意！ 不能用
         * int j=mid++;
         * 1.mid++会使mid+1
         * 2.j会等于mid而不是 mid+1
         */
        //排序数组
        int[] temp=new int[rightBound-left];
        //排序数组指针
        int tempIndex=0;
        while(i<=mid&&j<rightBound){
            if(arr[i]<=arr[j]){
                temp[tempIndex]=arr[i];
                tempIndex++;
                i++;
            }else {
                temp[tempIndex]=arr[j];
                tempIndex++;
                j++;
            }
        }
        //当一方已经循环完，而另外一方还有余数的时候,如{1,2,3,4,5,6,7}，后半部分全部比前半部分大
        //那么前半部分会先循环完，而后半部分一个没有循环 这个时候就要强制循环完，直接加入temp
        while(i<=mid){
            temp[tempIndex++]=arr[i++];
        }

        while(j<rightBound){
            temp[tempIndex++]=arr[j++];
        }
        System.out.println(Arrays.toString(temp));

    }
    static void swap(int[] arr,int left,int right){
        int temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;

    }
}
class MergeSortTest{
    public static void main(String[] args) {
        int[] arr={1,4,7,8,3,6,9};
        MergeSort.sort(arr,0,7);
        System.out.println(Arrays.toString(arr));

    }
}