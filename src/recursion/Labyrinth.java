package recursion;

/**
 * 用递归回溯算法解迷宫游戏
 * 1：墙； 2：已经走过的路径 3：说明这个点走不通，全部回溯了依旧到不了目标点  0：未走
 */
public class Labyrinth {
    /**
     *
     * @param map 初始化地图
     * @param x 初始点x坐标
     * @param y 初始点y坐标
     * @param y 寻找点x坐标
     * @param y 寻找点y坐标
     * @return
     */
    public static boolean findWay(int[][] map, int x, int y,int finalX,int finalY){
        //递归出口  如果点（finalX,finalY）为2，则找到了点
        if(map[finalX][finalY]==2){
            return true;
        }else if(map[x][y]==0){//若当前点没有走过，则开始递归求路
            //标记已经走过的点
            Labyrinth.printfMap(map);

            map[x][y]=2;
            //寻路策略是 右，上，左，下
            if(findWay(map,x,y+1,finalX,finalY)){
                return true;
            }
            if(findWay(map,x-1,y,finalX,finalY)){
                return true;
            }
            if(findWay(map,x,y-1,finalX,finalY)) {
                return true;
            }
            if(findWay(map,x+1,y,finalX,finalY)) {
                return true;
            }
            else //若以上所有方法均回溯，则代表这个点不可能到终点
            {
                map[x][y] = 3;
                return false;
            }
        }
        else {//当 (x,y) 为1,2,3时 返回false 进行回溯 这是碰到了墙，或者到了死点，又或是返回以前走过的点的时候，直接回溯
            return false;
        }

    }
    public static void printfMap(int[][] map){
        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map.length;j++)
                System.out.printf("%d\t",map[i][j]);
            System.out.println();

        }
        System.out.println("-------------------------------------------------------------");

    }
}

class Test{
    public static void main(String[] args) {
        //定义10*10迷宫 并在第九行做一道围墙 只留一个口子在[8][9]
        int [][] map=new int[10][10];
        //初始化地图
        for(int i=0;i<map.length;i++){
            map[i][0]=1;
            map[i][map.length-1]=1;
        }
        for(int i=0;i<map[0].length;i++){
            map[0][i]=1;
            map[map[0].length-1][i]=1;
        }
        for (int i=0;i<map[0].length-2;i++){
            map[map.length-7][i]=1;
        }
        Labyrinth.findWay(map,1,1,8,8);
    }
}
