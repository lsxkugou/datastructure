package sort;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.net.ServerSocket;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: longsx
 * @DateTime: 2020/5/31 15:16
 * @Description: 选择排序，时间复杂度n^2
 * 每次找到最小的数据放在首位，
 * 第二次排序从i到array.length-1处找到最小的值放在i处 循环array.length次
 */
public class SelectSort {
    /**
     *
     * @param array 数组
     * @return array
     */
    public static int[] sort(int[] array){
        //初始化记录最小值的变量与它的下表
        int min=0;
        int minIndex=0;
        //交换中间变量
        int temp;
        for(int i=0;i<array.length;i++){
            min=array[i];
            for(int j=i;j<array.length;j++) {
                if (min > array[j]) {//重置min
                    min = array[j];
                    minIndex=j;
                }
            }
            //将探测到的最小值与array[i]进行交换
            temp=array[i];
            array[i]=array[minIndex];
            array[minIndex]=temp;

        }
        return array;
    }
}
class SelectSortTest{
    public static void main(String[] args) {
        int[] array={3,9,-1,10,-2};

        System.out.println(Arrays.toString(SelectSort.sort(array)));
    }
}