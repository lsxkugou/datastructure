package search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/6/12 10:26
 * @Description: 二分查找 必须为有序
 * 插值查找与二分差不多 Mid为left+(value-left)/right-left 只适用于待查找数组等比例的情况
 * 斐波那契也是同样的道理 mid变为了黄金分割点
 */
public class BinarySearch {
    static ArrayList<Integer> result=new ArrayList<Integer>();
    /**
     *
     * @param arr 待查找数组
     * @param left 查找左边界
     * @param right 查找右边界
     * @param value 待查找值
     * @return 包含所有value的index集合
     */
    public static ArrayList<Integer> search(int[] arr,int left,int right,int value){
        int mid=(left+right)>>1;
        if(left>right){
            throw new RuntimeException("该值不在传入数组中");
        }
        if(arr[mid]>value){//说明在左侧
            search(arr,left,mid,value);
        } else if(arr[mid]<value){
            search(arr,mid,right,value);
        }else{//mid==value说明找到
            result.add(mid);
            //向右遍历指针
            int indexRight=mid+1;
            //向左遍历指针
            int indexLeft=mid-1;
            //先左后右遍历
            while(0<=indexLeft&&indexLeft<=arr.length-1&&arr[indexLeft]==value){
                result.add(indexLeft);
                indexLeft--;
            }
            while(0<=indexRight&&indexRight<=arr.length-1&&arr[indexRight]==value){
                result.add(indexRight);
                indexRight++;
            }

            return result;
        }
        return result;

    }
}
class BinarySearchTest{
    public static void main(String[] args) {
        int[] arr={1,2,4,4,6,6,7};
        ArrayList<Integer> result = BinarySearch.search(arr, 0, arr.length - 1, 6);
        System.out.println(result.toString());
    }
}
