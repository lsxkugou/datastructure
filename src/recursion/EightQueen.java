package recursion;

/**
 * 八皇后问题 8*8棋盘，摆八个皇后
 * 八个皇后的米字格不能有其他皇后
 * 用一维数组表示棋子的位置 queen[i] i表示行 同时也保存了这是第几个皇后，因为一行仅有且必须有一个皇后，queen[i]中
 * 保存的值表示了在棋盘的第几列
 * 1.一定是一行最多有一个皇后
 * 2.在放第n个皇后的时候要去判断是否与八皇后关系冲突 同一列，同一斜线
 * 2.2同一列很好判断，只需要queen[n]!=queen[i]即可
 * 2.3同一斜线，即斜率为1或者-1  Math.abs((queen[n]-queen[i])/(n-i))!=1即可
 */
public class EightQueen {
    /**
     *放置皇后方法
     * @param n n表示第几个皇后 第几行
     * @return
     */
    int[] queen=new int[8];
    //次数
    int way=0;
    void setQueen(int n){
        //递归出口
        if(n==8){
            //进行回溯 找出一个解
            print();
            System.out.printf("%s\n","-----------------------------------------------------------");
            way++;
        }else {
            for (int i=0;i<8;i++){
                //依次放置皇后
                queen[n]=i;
                //如果判断可以放置
                if (judge(n)){
                    //开始放置n+1个皇后，开始递归
                    setQueen(n+1);
                }
                //若冲突了，则继续循环，将皇后放在本行的下一列
            }
        }

    }

    /**
     * 检查皇后是否冲突
     * @param n n表示第几个皇后 第几行
     * @return
     */
    boolean judge(int n){
        //判断从第一个点到第n-1个点 与n的位置
        for(int i=0;i<n;i++) {
            //不能在同一列，也不能在斜率为1或-1
            //不能用 if ((queen[i]！=queen[n])&&(Math.abs((queen[n]-queen[i])/(n-i))！=1)){
            //                return true;
            //            }else {
            //                return false;
            //            }
            //因为这样虽然表面上是逆否命题，两个命题是相等的。但是外面有循环，这样的话，只要有一个符合条件就会返回true了，逻辑错了
//            if ((queen[i]==queen[n])||(Math.abs((queen[n]-queen[i])/(n-i))==1)){
//                return false;
//            }
            if ((queen[i]==queen[n])||(Math.abs((queen[n]-queen[i]))==Math.abs(n-i))){
                return false;
            }
//            注意：
//            Math.abs((queen[n]-queen[i])/(n-i))==1)和Math.abs((queen[n]-queen[i]))==Math.abs(n-i))
//            这两个不相等，第一个-1 1带进去也会返回false 但此时真正斜率为-2 而不是1或-1
//            这个esle return true也不能加，因为这就意味着只要有一个满足条件该点就可以放置皇后，实际上，是所有的点都必须满足条件。
//                必须要等到全部循环结束后，返回true
//            else {
//                return true;
//            }
        }
        //若n==0则会来到这个块，直接返回true 因为是第一个元素插入
        //或者judge完所有循环之后，返回true
    return true;
    }

    void print(){
        for(int i:queen){
            System.out.printf("%d\t",i);
        }
        System.out.println();
    }
}
class TestEightQueen{
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.setQueen(0);
        System.out.println("一共有"+eightQueen.way+"种解法");
    }
}

