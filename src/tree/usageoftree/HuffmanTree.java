package tree.usageoftree;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author: longsx
 * @DateTime: 2020/6/17 5:28
 * @Description: 哈夫曼树的构建+哈夫曼的压缩
 */
public class HuffmanTree {
    /***
     * 构建普通哈夫曼树
     * @param arr 传入数组
     * @return Node
     */
    public static Node createHuffmanTree(Byte[] arr){
        //使用集合更加方便，因为在构建的过程中空间会发生变化
        LinkedList<Node> nodes = new LinkedList<Node>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while(nodes.size()!=1){
            Collections.sort(nodes);
            //创造新的节点，节点值为最小的两节点值之和，左右子节点为最小的两个子节点
            Node mergedNode = new Node(nodes.get(0).weight + nodes.get(1).weight);
            mergedNode.left=nodes.get(0);
            mergedNode.right=nodes.get(1);
            //删除原先的两个节点
            nodes.remove(1);
            nodes.remove(0);
            //添加最大节点
            nodes.add(mergedNode);
        }
        //返回根节点
        return nodes.get(0);
    }
    /**
     * 统计待压缩字符串的每个字符出现的次数
     * @param string 待统计string
     * @return count MAP
     */
    private static Map<String,Integer> count(String string){
        Map<String, Integer> count = new HashMap<String, Integer>();
        char[] text = string.toCharArray();
        for (char c : text){
            String character = String.valueOf(c);
            if(count.containsKey(character)){
                count.put(character,count.get(character)+1);
            }else {
                count.put(character,1);
            }
        }
        return count;
    }
    /**
     * 构建压缩哈夫曼树
     * @param string 传入字符串
     * @return Node头节点
     */
    public static Node compressHuff(String string){
        Map<String,Integer> count = HuffmanTree.count(string);
        LinkedList<Node> nodes= new LinkedList<Node>();
        //将map中的所有数据以Node的形式保存在LikedList中
        count.forEach((k,v)->{
            Node node = new Node(v);
            node.value=k;
            nodes.add(node);
        });

       while(nodes.size()!=1){
           Collections.sort(nodes);
           //创造新的节点，节点值为最小的两节点值之和，左右子节点为最小的两个子节点
           Node mergedNode = new Node(nodes.get(0).weight + nodes.get(1).weight);
           mergedNode.left=nodes.get(0);
           mergedNode.right=nodes.get(1);
           //删除原先的两个节点
           nodes.remove(1);
           nodes.remove(0);
           //添加最大节点
           nodes.add(mergedNode);
       }
        return nodes.get(0);
    }

    /**
     * 将压缩好的哈夫曼树进行编码并储存到Map中进行保存
     * @param node 节点
     * @return 集合
     */
    public static Map<String,Byte> encoding(Node node){
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Byte> encode= new HashMap<String, Byte>();
        return encoding(node,stringBuilder,encode);
    }

    /**
     * 编码的方法重载 递归部分
     * @param node 节点
     * @param stringBuilder 编码
     * @param encode 保存集合
     * @return 保存集合
     */
    private static Map<String, Byte> encoding(Node node, StringBuilder stringBuilder, Map<String, Byte> encode){
        if(node.right==null&&node.left==null){
            String S= String.valueOf(stringBuilder);
            encode.put(node.value,Byte.parseByte(String.valueOf(stringBuilder),2));
            return encode;
        }
        if(node.left!=null){
            stringBuilder.append("0");
            encoding(node.left,stringBuilder,encode);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        if(node.right!=null){
            stringBuilder.append("1");
            encoding(node.right,stringBuilder,encode);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    return encode;
    }

}
class Node implements Comparable<Node>{
    /**
     * 权值
     */
    int weight;
    /**
     * 其中保存的值
     */
    String value;
    Node left;
    Node right;

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", value='" + value + '\'' +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight-o.weight;
    }
}
class HuffmanTreeTest{
    public static void main(String[] args) throws IOException {
        //构建huffMan树实验
//        Byte[] arr={13,7,8,3,29,6,1};
//        HuffmanTree.createHuffmanTree(arr).preOrder();
        //构建压缩树实验
        String s="IIIOOV";
        //获得压缩赫夫曼树
        Node node = HuffmanTree.compressHuff(s);
        //将压缩的赫夫曼树的叶子节点进行编码
        Map<String, Byte> encoding = HuffmanTree.encoding(node);
        int x=0;
        FileOutputStream outputStream = new FileOutputStream("./src/tree/usageoftree/experience of compressing by huffmanTree.txt");
        char[] text = s.toCharArray();
        for (char c : text) {
            //必须转换成String类型才能够拿到value
            Byte compressText=encoding.get(String.valueOf(c));
            outputStream.write(compressText);
        }
        outputStream.close();
        //赫夫曼树的解码
        //将原有的map进行k,v reverse
         HashMap<Byte, String> decodeMap = new HashMap<>();
        encoding.forEach((k,v)->{
            decodeMap.put(v,k);
        });
        //从文件中读取数据并进行解码
        FileInputStream fileInputStream = new FileInputStream("./src/tree/usageoftree/experience of compressing by huffmanTree.txt");
        int length;
        //1兆的缓冲区
        byte[] reading= new byte[1];
        //缓冲区指针
        //read返回的是还有多少个字节剩余，read(xxx)中是放置读取内容存放的位置,read按照缓冲区大小读取最大长度
        while(fileInputStream.read(reading)!=-1){
            byte c = reading[0];
            String decodingText = decodeMap.get(c);
            System.out.printf("%s",decodingText);
        }
    }
}