package LinkedList.singleLinkedList;

import java.util.Stack;

//方式1 翻转链表再打印 不推荐 因为会破坏链表的原有结构
//方式2 利用栈 反序 记得使用栈
public class PrintReverse {

    static void print(LinkedList linkedList){
        Stack stack = new Stack<LinkedList>();
        Node temp=linkedList.headNode;
        int length=linkedList.getLength();
        for(int i=0;i<length;i++){
            temp=temp.nextNode;
            stack.push(temp);
        }
        while (stack.size()>0){
            System.out.println(stack.pop().toString());
        }
    }
}
