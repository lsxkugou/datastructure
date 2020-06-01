package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: longsx
 * @DateTime: 2020/6/1 20:35
 * @Description: 插入排序(递增)，将待排序序列分为两部分，一为有序序列，二为混乱序列，每次循环将混轮序列
 * 的下一位按照递增或递减的方式插入到有序序列中
 */
public class InsertSort {
    public static int[] sort(int[] array){
        //定义有序序列
//        int[] orderArray=new int[array.length];
//        int oderArrayIndex=0;

        //
        //无序序列即为剩下的数

        //有序序列默认有array[0]
        for(int i=1;i<array.length;i++){
            //array[i]为需要进行插入的元素
            for(int j=0;j<i;j++){
                //当temp小于顺序序列中的元素时，插入到前一个元素里
                int temp=array[i];
                if(array[i]<array[j]){
                    //将i~j依次移动到前一个元素位置
                    for(int z=i;z>j;z--){
                        array[z]=array[z-1];
                    }
                    array[j]=temp;
                    break;
                }
                //若有序序列无法匹配该元素，则array[i]不变，因为它大于所有的有序序列元素，直接放在末尾即可
            }
        }
        return array;
    }
}
class InsertSortTest{
    public static void main(String[] args) {
        int[] ar={7,5,6,1,-1,-8,9};
        System.out.println(Arrays.toString(InsertSort.sort(ar)));
    }
}
