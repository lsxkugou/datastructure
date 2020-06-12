package hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: longsx
 * @DateTime: 2020/6/12 21:37
 * @Description: 手写hashtable 哈希值的计算是核心，这里假设所有id的分布为正常的随机分布 所以直接id%size为散列值
 * 默认Id为不同
 */
public class MyHashtable {
    //不能这样写，JAVA不支持泛型数组
//    LinkedList<Emp>[] empHash;
    /**
     * 一个顺序表嵌套一个链表完成哈希表
     */
    ArrayList<LinkedList<Emp>> empHash;
    int size;

    /**
     * 添加员工函数
     * @param emp 员工对象
     */
    void add(Emp emp){
        int hash=hashFun(emp.id);
        empHash.get(hash).add(emp);
    }
    void printAll(){
        for (LinkedList<Emp> Link : empHash) {
            for (Emp emp : Link) {
                System.out.printf("%d%s\t",emp.id,emp.name);
            }
            System.out.println("");
        }
        System.out.println("------------------------");
    }
     /**
     * 删除员工函数
     * @param id 员工id
     */
    void delete(int id){
        int hash=hashFun(id);
        empHash.get(hash).remove(searchOne(id));
    }

    /**
     * 查询单个员工
     * @param id 员工id
     * @return 员工id
     */
    Emp searchOne(int id){
        int hash=hashFun(id);
            for (Emp emp1 : empHash.get(hash)) {
                if(emp1.id==id){
                    return emp1;
                }
            }
        System.out.println("该人员未被收录");
            return null;
        }

    /**
     * 修改特定员工
     * @param id 员工对象
     */
    void modify(int id,Emp emp){
        if(searchOne(id)!=null){
        searchOne(id).id=emp.id;
        searchOne(id).name=emp.name;
        }
        else {
            add(emp);
            System.out.println("添加"+emp.toString());
        }
    }
    public MyHashtable(int size) {
        this.size=size;
        this.empHash = new ArrayList<LinkedList<Emp>>();
        //进行初始化
        for(int i=0;i<size;i++){
            empHash.add(new LinkedList<Emp>());
        }
    }
    int hashFun(int id){
        return id%size;
    }
}

class Emp{
     int id;
     String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = "姓名"+name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class MyHashTableTest{
    public static void main(String[] args) {
        int size=5;
        MyHashtable myHashtable = new MyHashtable(size);
        for(int i=0;i<size*4;i++){
            myHashtable.add(new Emp(i,""+i));
        }
        myHashtable.printAll();
        myHashtable.modify(41,new Emp(41,""+41));
        myHashtable.printAll();
        myHashtable.modify(41,new Emp(41,"已经修改了"));
        myHashtable.printAll();
        myHashtable.delete(41);
        myHashtable.printAll();

    }

}
