package sort;

/**
 * @Author: longsx
 * @DateTime: 2020/6/11 16:45
 * @Description: 计数排序算法，适合大量重复数据在一个限定的小的范围 如
 * {1,1,1,1,1,9,9,9,9,9,5,5,5,4,6,3,7,5,3,2,0} 0~9
 */
public class CountSort {
    /**
     *
     * @param arr 传入一个范围为{0,9}数组
     * @return\
     */
    static void sort(int[] arr){
        //定义转换数组
        int[]  sort= new int[10];
        for (int i : arr) {
            sort[i]++;
        }
        //进行输出
        for (int i=0;i<sort.length;i++) {
            for(int j=sort[i];j>0;j--){
                System.out.printf("%d\t",i);
            }
        }

    }
}
class CountSortTest{
    public static void main(String[] args) {
        int[] arr={1,1,1,1,1,9,9,9,9,9,5,5,5,4,6,3,7,5,3,2,0};
        CountSort.sort(arr);

    }
}
