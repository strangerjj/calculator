package com.jc.interfaces;

/**
 * Created by YJZJB0051 on 2016-02-26.
 */

//这里定义计算器模型的接口约束
    //面向接口编程的好处之一,就是几时以后换了另一种算法模型
    //只需它继续实现这个接口,程序中使用模型处的代码就比用改变
public interface ICalculator {
    //接收操作数输入
    void pushOperand(String operand);
    //接收运算符输入
    //当接收到运算符输入时,计算则有可能发生,并返回结果
    double pushOperate(String operate);
    //重置清零
    void reset();
}
