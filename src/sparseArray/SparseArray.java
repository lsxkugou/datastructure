package sparseArray;

public class SparseArray {

    public static void main(String[] args) {
        //假设有11行11列 有两个点个点 值为1 2
        int chessArray[][]=new int[11][11];
        chessArray[4][2]=1;
        chessArray[9][3]=2;
        chessArray[9][4]=2;

        int[][] result=SparseArray.transferSparseArray(chessArray);

        for(int[] row:result){
            for(int chess:row){
                System.out.printf("%d\t",chess);
            }
            System.out.println();
        }
    }

    static int[][] transferSparseArray(int[][] chessArray){
        //必须知道有几个棋子 必须进行遍历
        int count=0;
        for(int[] row:chessArray){
            for(int chess:row){
                if (chess!=0)
                    count++;
            }
        }
        //转换为稀疏数组 稀疏数组有散列2行 x,y，值 如果用集合 可以直接用一个循环搞定 在循环中可以增加一个参量进行计算
        int[][] result=new int[count][3];
        //再次遍历原有数组 并添加 注意：行数代表了有几个值
        int rowNum=0;//行计数器
        int countRow=0;
        for(int[] row:chessArray){
            /**
             * 注意：！！！循环行的坐标值必须在行内，每行进行归零
             */
            int colNum=0;//循环列的坐标值

            for(int chess:row){
                if (chess!=0) {
                    //进行添加
                    result[countRow][0] = rowNum;
                    result[countRow][1] = colNum;
                    result[countRow][2] = chess;
                    countRow++;
                }
                colNum++;
            }
            rowNum++;
        }
        return result;
    }
    /**
     * 转换为.data文件
     */
    static void trasferdata(){

    }
    /**
     * .data文件转换为稀疏数组并读出
     */
}
