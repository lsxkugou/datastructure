package 队列;

import java.util.Scanner;

//环形队列

/**
 * 整个环形队列最重要的思想是'%'取模
 * 第二重要的思想是判断rear在front上面还是下面 这样就可以正确的算出长度与输出队列
 */
public class ArrayQueen {
//    必须从0开始 不然当front=-1，rear=maxSize-1的时候+1取模会导致两者相减为0
    int rear=0;
    int front=0;
//    实际容量取maxSize-1
    int maxSize=0;
    int ArrayQueen[];
    public static void main(String[] args) {
        ArrayQueen arrayQueen = new ArrayQueen(10);
        arrayQueen.add(1);
        arrayQueen.add(2);
        arrayQueen.add(3);
        arrayQueen.add(4);
        arrayQueen.add(5);
        arrayQueen.add(6);

        arrayQueen.add(7);
        arrayQueen.add(8);
        arrayQueen.add(9);
        arrayQueen.get();
        arrayQueen.get();
        arrayQueen.get();

        arrayQueen.add(10);

        arrayQueen.showAll();
//        Scanner scanner = new Scanner(System.in);
////        输出一个菜单
//        while(true){
//            System.out.println("s(show):显示队列");
//            System.out.println("e(exit):退出程序");
//            System.out.println( "a(add):添加数据到队列");
//            System.out.println("g(get):从队列取出数据");
//            System.out.println( "h(head):查看队列头的数据");
//            char key = scanner.next().charAt(0);//接收一个字符
//            switch (key){
//                case 's': arrayQueen.showAll();
//                break;
//                case 'e':
//                    scanner.close();
//                    System.exit(0);
//                    case ''
//
//
//            }
//
//        }
    }

//    初始化队列
     ArrayQueen(int maxSize) {
        this.maxSize=maxSize;
        ArrayQueen=new int[maxSize];
    }

//    判断队列是否为空
    boolean isEmpty(){
        if((rear)%maxSize-(front)%maxSize==0)
            return true;
        else
            return false;
    }
    
//    判断队列是否已经满了
    boolean isFull(){
        /**有两种情况
         * 第一种是rear在front上面，相减为maxSize-1
         * 第二种是rear在front下面一格，相减为-1
         */

        if((rear%maxSize-front%maxSize==maxSize-1)||(rear%maxSize-front%maxSize==-1))
            return true;
        else
            return false;
    }
    
//    取出队列长度
    int arrayQueenLength(){
        if (rear%maxSize-front%maxSize>0){
            return rear%maxSize-front%maxSize;
        }else{
           return rear%maxSize+maxSize-front%maxSize;
        }


    }
//    插入数据 插入数据的值
    int add(int element){
        //先判断是否满了
        if (this.isFull())
            throw new RuntimeException("队列满");
        else {//进行插入
            ArrayQueen[(rear + 1) % maxSize] = element;
            rear++;
            System.out.println("插入队列"+ArrayQueen[(rear) % maxSize]);
            return ArrayQueen[(rear) % maxSize];
        }
    }
    //取出末尾数据
    int get(){
//        判断是否为空
    if(this.isEmpty())
        throw new RuntimeException("队列为空");
    else {//数据取出 并删除数据 front++
        int result=ArrayQueen[(front+1)%maxSize];
        front++;
        System.out.println("取出元素"+result);
        return result;}
    }

//    显示队列
    void showAll(){
        /**
         * 难点是：
         * 1.同样注意rear与front的位置关系
         * 2.length用来判断循环的次数 而 maxSize-front(%之后)来防止数组超限
         */
        System.out.println("-------------------------------------------------该队列长"+this.arrayQueenLength()+"-------------------------------------------------");
        rear=(rear)%maxSize;
        front=(front+1)%maxSize;
        int length=this.arrayQueenLength();
        for(int i=0;i<length;i++){
            if(front<maxSize){
                System.out.println(ArrayQueen[front]);
                front++;
            }else{
                System.out.println(ArrayQueen[maxSize-front]);
                front++;
            }
        }
    }
}
