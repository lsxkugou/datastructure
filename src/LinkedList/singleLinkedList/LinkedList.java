package LinkedList.singleLinkedList;

public class LinkedList {
    //头结点
    Node headNode;

    public LinkedList() {
        headNode=new Node(0);
    }

    //直接尾部添加结点
    void addNode(Node node){
        int index=1;
        Node temp;
        temp=headNode;
        while(true){
            if (temp.nextNode==null){
                temp.nextNode=node;
                System.out.println("成功在"+index+"处插入结点");
                break;
            }else{
                temp=temp.nextNode;
                index++;
            }
        }
    }

    //根据data大小进行插入 升序
    void addNodeByOder(Node node){
        int index=1;
        Node later;
        Node before;
        before=this.headNode;
        later=headNode.nextNode;
//        如果链表为空
        if (later==null){
            later=node;
            before.nextNode=node;
            System.out.println("成功在1处插入结点");
            return;
        }
            
        while (true){
            if(later==null){
                later=node;
                before.nextNode=node;
                System.out.println("成功在"+index+"处插入结点");
                index++;
                break;
            }

            //如果插入结点的值小于等于遍历结点的值则进行插入，插入到later与before之间
            else if ((node.data<=later.data)){

                before.nextNode=node;
                node.nextNode=later;
                System.out.println("成功在"+index+"处插入结点");
                break;
            }
            index++;
            before=later;
            later=later.nextNode;
        }
    }
    //输出链表所有数据 
    void showAll(){
        Node temp;
        temp=headNode.nextNode;
        while (true){
            if(headNode.nextNode==null){
                System.out.println("该链表为空");
                break;
            }else {
                if (temp==null){
                    break;
                }
                System.out.printf("%d\t",temp.data);
                System.out.println();
                temp=temp.nextNode;

            }
        }
    }

    //判断链表的长度
    int getLength(){
        Node temp=headNode.nextNode;
        int length=0;
        while(temp!=null){
            temp=temp.nextNode;
            length++;
        };
        return length;
    }

    //删除特定顺序结点
    void delNode(int position){
        Node later;
        Node before;
        before=this.headNode;
        later=headNode.nextNode;
        int length=this.getLength();
        if(position<1||position>length){
            System.out.println("输入长度错误，该链表长"+length);
            return;
        }
        //index和Later始终处于同一位置 经历完循环后，later指向要删除的节点
        for(int i=1;i<position;i++){
            before=later;
            later=later.nextNode;
        }
        before.nextNode=later.nextNode;
    }

    //修改节点值
    void modify(int position,int data){
        Node later;
        Node before;
        before=this.headNode;
        later=headNode.nextNode;
        int length=this.getLength();
        if(position<1||position>length){
            System.out.println("输入长度错误，该链表长"+length);
            return;
        }
        //index和Later始终处于同一位置 经历完循环后，later指向要删除的节点
        for(int i=1;i<position;i++){
            before=later;
            later=later.nextNode;
        }
        later.data=data;
    }

}

class Node{
    int data;
    Node nextNode;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", nextNode=" + nextNode +
                '}';
    }
}

class Test{
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
//        linkedList.addNodeByOder(new Node(1));
        linkedList.addNodeByOder(new Node(1));
        linkedList.addNodeByOder(new Node(4));
        linkedList.addNodeByOder(new Node(5));

        linkedList.addNodeByOder(new Node(9));
//        linkedList.addNodeByOder(new Node(11));
//        linkedList.addNodeByOder(new Node(41));
//        linkedList.addNodeByOder(new Node(42));
//        linkedList.addNodeByOder(new Node(462));

//        linkedList.addNodeByOder(new Node(3));
//        linkedList.showAll();
//        System.out.println("链表长"+linkedList.getLength());
//        linkedList.delNode(4);
//        linkedList.showAll();
//        System.out.println("链表长"+linkedList.getLength());
//        linkedList.modify(1,6);
//        linkedList.showAll();
//        System.out.println("链表长"+linkedList.getLength());
        //反转链表
//        ReverseLink.reverseLink(linkedList).showAll();
        //反转输出
//            PrintReverse.print(linkedList);
        //约瑟夫问题测试
        int[] a=CircleLined.john(linkedList,2);
        for(int i:a)
            System.out.printf("%d\t",i);
    }
}

