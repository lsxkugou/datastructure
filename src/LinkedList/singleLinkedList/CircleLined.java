package LinkedList.singleLinkedList;
//约瑟夫问题与环链表 这种方式会破坏原有链表结构 可以进行复制？
public class CircleLined {
    //param 抛几次
    static int[] john(LinkedList linkedList, int param){
        int length=linkedList.getLength();
        //定义放回数组与index
        int[] result=new int[length];
        int index=0;

        Node temp=linkedList.headNode.nextNode;
//        将最后一个元素与head头结点元素进行相连
        for(int i=0;i<length-1;i++){
            temp=temp.nextNode;
        }
        //将第一个节点与最后一个节点相连
        temp.nextNode=linkedList.headNode.nextNode;
        //重置指针
        temp=linkedList.headNode.nextNode;
        //处理约瑟夫问题
        //不能用while(temp!=null){来进行判断 因为最后一个节点会自连
        for(int circle=0;circle<length;circle++){
            //找到需要记录并删除的节点
            //找到前一个节点
            for(int i=0;i<param-1;i++){
                temp=temp.nextNode;
            }
            //记录节点
            result[index]=temp.nextNode.data;
            //删除后一个节点
            temp.nextNode=temp.nextNode.nextNode;
            index++;
            temp=temp.nextNode;
        }
        return result;
    }
}
