package sort;

import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/5/31 10:29
 * @Description: 冒泡排序 时间复杂度最大情况为 n^2 n为数据个数
 */
public class BubleSort {

    /**
     * 排序方法
     * @param array 数组
     * @return array
     * 最大时间复杂度n^2
     */
    static int[] sort(int [] array){
        //定义交换变量
        int temp=0;
        //定义flag用于判断该次循环是否进行了交换，如果没有，则直接瑞出循环
        boolean flag=false;
        //循环array.length-1次，因为每次排列可以把一个最大的数字排列到末尾
        for(int i=0;i<array.length-1;i++){
            //每次排序比较长度为array.length-1-i，因为每次循环一个最大的数字就排到了数组的末尾，下一次就可以少排一次
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    flag=true;
                    temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
            //若一次都没有交换过
        if (!flag){
            break;
            }
        else {
            //重置标志位，进行下一次循环的时候重新进行判断，不然只要有一次交换，后面永远都不可能是false了
            flag=false;
        } }
        return array;
    }
}
class BubleSortTest{
    public static void main(String[] args) {
        int[] array={3,9,-1,10,-2};

        System.out.println(Arrays.toString(BubleSort.sort(array)));
    }
}
