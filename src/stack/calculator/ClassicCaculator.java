package stack.calculator;

import java.util.Stack;

/**
 * 主要思路，只有加减乘除，通过栈去实现
 * 1.创建两个栈，一个是符号栈，一个是数据栈
 * 2.扫描字符串，数字直接入栈，若是符号则进行比较（若符号栈为空则直接入栈）
 *  2.1若扫描到的符号优先级高于符号栈栈顶符号的优先级，则直接入符号栈
 *  2.2若小于或等于，则从符号栈弹出栈顶符号，且从数栈中弹出两个值，第一个弹出的与第二个弹出的进行计算，运算完毕后结果入栈
 *3.最后，当字符串扫描到尾部的时候，跳出循环依次运算即可
 */
public class ClassicCaculator {
    //优先级判断
    public static Double opperContrast(String oper) {
        if (oper.equals("*") || oper.equals("/"))
            return 1.0;
        if (oper.equals("+") || oper.equals("-"))
            return 0.0;
        else {
            return -1.0;
        }
    }

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

    //运算器主方法
    public static double calculator(String s) {
        Stack<Double> numStack = new Stack();
        Stack<String> operStack = new Stack();
        //数据字符串
        String numTemp="";
        //符号字符串
        String operTemp="";
        //最终结果
        Double finalResult=0.0;
        for (int i = 0; i < s.length();) {
            //因为有多位数的情况所以抽出符号的时候要进行循环 下面循环是找出单个的数字用的
            while (true) {
                //防止数组越界
               if(i==s.length())
                   break;
                if (isOper(s.substring(i,i+1)))
                    //是符号，退出循环并进行下一阶段的判断，且此时numTemp中已经保存好了数据
                    break;
                else {
                    numTemp+=s.substring(i,i+1);
                    i++;
                }
            }
            //数据压栈
            numStack.push(Double.parseDouble(numTemp));


            //取出下一个符号字符，并赋值 i++
            //也得防止数组超界
            if(i<s.length()) {
                operTemp = s.substring(i, i + 1);
                i++;
            }
            //判断字符
            //若operStack为空，则直接入栈
            if(operStack.isEmpty()){
                operStack.push(operTemp);
            }else {
                //分两种情况
                //1.当前符号比栈顶符号大的时候直接入栈 ，且i!=length 因为当结尾数字前一个符号的时候，栈为空，就直接
                if(opperContrast(operTemp)>opperContrast(operStack.peek()))
                    operStack.push(operTemp);
                else {
                    //当前符号小于或等于的时候 num进行运算，运算结束后运算符再入栈
                    Double num2=numStack.pop();
                    Double num1=numStack.pop();
                    String oper=operStack.pop();
                    //包装类的自动拆箱装箱
                    Double result=caculateNum(num1,num2,oper);
                    numStack.push(result);
                    if(i!=s.length())
                        operStack.push(operTemp);

                }
                
                
            }
            //记住清空numTemp的数据，因为numTemp会进行累加
            numTemp="";
        }
        //循环到底之后，符号栈中应该只剩下了+- 这个时候在进行运算即可
        {
            while(operStack.size()>0){
                //当前符号小于或等于的时候
                Double num2=numStack.pop();
                Double num1=numStack.pop();
                String oper=operStack.pop();
                //包装类的自动拆箱装箱
                finalResult=caculateNum(num1,num2,oper);
                numStack.push(finalResult);
            }
        }
        return finalResult;
    }
}



class test{
    public static void main(String[] args) {
        //注意：输出为false,字符串与char不能进行这样的比较
        //        System.out.println("+".equals('+'));

        System.out.println(ClassicCaculator.calculator("11-5+1.2*5/10"));
    }
}