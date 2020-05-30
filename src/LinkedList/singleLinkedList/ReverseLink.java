package LinkedList.singleLinkedList;
//让链表进行翻转
public class ReverseLink {

    protected static LinkedList reverseLink(LinkedList linkedList){
        //
        /**一共定义三个指针
         *传入序列指针
         * reverse的定位指针
         * 一直指向reverse序列的尾部指针
         */
        //定义新的逆转头结点
        Node reverseHead=new Node(0);
        //定义指向逆转链表的一个指针用于保存上一个插入节点，让下一个插入节点的Next域指向它
        Node reverseTemp=reverseHead.nextNode;
        //指向头结点后的第一个节点
        Node temp=linkedList.headNode.nextNode;
        if(temp==null){
            throw new RuntimeException("链表为空");
        }
        //第一个节点插入不该在循环中，因为不用设置尾节点
            reverseHead.nextNode = temp;
            temp=temp.nextNode;

        int length=linkedList.getLength();
        /** 使用头插法进行插入 由第二个节点插入，直接插在头结点后面，且尾连下一个节点。遍历长度为Length
        注意，这里不能遍历直到指针为空为止，因为temp总是指向head.next.next 无限循环
        错误
        while(temp!=null){
            //保存插入后，原先head后的第一个节点地址
            reverseTemp=reverseHead.nextNode;
            reverseHead.nextNode=temp;
            //先让temp进行移动，再赋值尾节点
            temp=temp.nextNode;
            //赋值尾结点
            reverseHead.nextNode.nextNode=reverseTemp;
        }
        LinkedList result=new LinkedList();
        result.headNode=reverseHead;
        return  result;
    }
         **/
        //定义reverseLink的尾结点用于斩断循环
        Node last=reverseHead.nextNode;
        //头结点已经插入，所以只需要循环length-1
        for(int i=0;i<length-1;i++){
            //保存插入后，原先head后的第一个节点地址
            reverseTemp=reverseHead.nextNode;
            reverseHead.nextNode=temp;
            //先让temp进行移动，再赋值尾节点
            temp=temp.nextNode;
            //赋值尾结点
            reverseHead.nextNode.nextNode=reverseTemp;
            //尾结点向后移动
            last=last.nextNode;
            //当循环遍历到最后一个节点的上一个节点的时候进行斩断，因为循环的原因last累加的长度是length-1，无法累加到length，所以要last.next.next
            if(i==length-2)
                last.nextNode.nextNode=null;
        }
        /**
         * 重点，现在最后两个元素是首位相连的，这个时候必须将尾部元素置空，不然在输出的时候会循环输出这两个元素
         */

        LinkedList result=new LinkedList();
        result.headNode=reverseHead;
        return  result;
    }
}
