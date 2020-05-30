package stack.niBolanCalculator;
//逆波兰计算器 形式：

import java.util.Stack;

/**逆波兰 后缀算式 相比于中缀，不需要考虑符号优先级，更适合计算机
 * 以空格分开
 * （3+4）*5-6  3 4 + 5 * 6-
 * 注意，是反向扫描
 */
public class NiBolanCalculator {

    //判断是否为运算符
    public static boolean isOper(String oper) {
        return oper.equals("*") || oper.equals("/") || oper.equals("+") || oper.equals("-");
    }
    //运算符运算方法
    public static double caculateNum(double num1, double num2, String oper) {
        double result;
        if (oper.equals("+"))
            return num1 + num2;
        if (oper.equals("-") )
            return num1 - num2;
        if (oper.equals("*"))
            return num1 * num2;
        if (oper.equals("/"))
            return num1 / num2;
        else
            throw new RuntimeException("输入符号有误");
    }
    //中缀表达式转换成后缀表达式

    //计算
    public static Double calculate(String s){

        //定义字符栈
        Stack<String> operStack=new Stack<String>();
        //定义数字栈
        Stack<Double> numStack=new Stack<Double>();

        String ss[]=s.split(" ");
        //定义数组指针
        int index=ss.length-1;
        for(;index>-1;index--){
            //若为符号
            if(isOper(ss[index]))
                operStack.push(ss[index]);
            else{
                numStack.push(Double.parseDouble(ss[index]));
            }
        }

        //进行计算
        while(!operStack.isEmpty()){
            Double num1=numStack.pop();
            Double num2=numStack.pop();
            String oper=operStack.pop();
            Double result=caculateNum(num1,num2,oper);
            numStack.push(result);
        }

        return numStack.peek();

    }

}
class test{
    public static void main(String[] args) {
        String s="3 4 + 5 * 6 -";
        System.out.println(        NiBolanCalculator.calculate(s));
    }
}
