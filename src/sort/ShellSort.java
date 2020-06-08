package sort;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: longsx
 * @DateTime: 2020/6/8 10:12
 * @Description: shell排序 分组思想+组内插入排序 从大到小
 */
public class ShellSort {
    /**
     *
     * @param array
     * @return
     */
    static int[] sort(int[] array){
        //gap为步长 如arr[3] arr[0] 最后一次插入排序为全排列 gap将等于1
        //分组循环
        for (int gap=array.length/2;gap>=1;gap/=2){

            //分组内插入排序，i 代表无序序列第一个元素，而i-gap代表了有序序列的第一个元素
           for(int i=gap;i<array.length;i++){
               //定义temp来保存待插入元素
               int temp=array[i];
               //定义有序序列指针
               int j=i;
               //将待插入元素从有序序列的大端向小端逐个比较，若比较失败，则将元素进行位移
                   while (j - gap >= 0 && temp < array[j - gap]) {
                       //直接进行移动
                       array[j] = array[j - gap];
                       j -= gap;
                   }
               /**当while停止的时候就代表j已经指向了正确的位置，有三种情况
                * 1.j=0，有序序列中所有的数字都比带比较数字大
                * 2.j=i，直接不用插入，有序序列中所有数字都比待比较数字小
                * 3.中间进行插入
                */
               array[j]=temp;
           }
        }
        return array;
    }
}
class ShellSortTest{
    public static void main(String[] args) {
        int[] array=new int[100];
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<100;i++){
            array[i]=(int)(Math.random()*1000);
        }
        System.out.println(Arrays.toString(ShellSort.sort(array)));

    }

}

/**
 * 比较希尔排序与快速排序
 */
class Compare{
    public static void main(String[] args) {
        /**
         * 希尔排序比插入排序快了非常多
         */
        System.out.println("希尔排序实验");
        int[] array=new int[320000];
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<320000;i++){
            array[i]=(int)(Math.random()*320000);
        }
        ShellSort.sort(array);
        String now=simpleDateFormat.format(new Date());
        System.out.println("排序前"+now);
        String after=simpleDateFormat.format(new Date());
        System.out.println("排序后"+after);
        System.out.println("------------------------------------------------------");


        System.out.println("插入排序实验");
        String now1=simpleDateFormat.format(new Date());
        System.out.println("排序前"+now1);

        InsertSort.sort(array);

        String after1=simpleDateFormat.format(new Date());
        System.out.printf("排序后"+after1);
        System.out.println("------------------------------------------------------");
    }
}
