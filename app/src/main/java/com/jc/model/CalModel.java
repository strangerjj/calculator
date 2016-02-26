package com.jc.model;

import com.jc.interfaces.ICalculator;

import java.util.Stack;

/**
 * Created by YJZJB0051 on 2016-02-26.
 */
public class CalModel implements ICalculator {

    //计算器算法
    public static double popOpOffStack(Stack<String> stack) {
        double result = 0;
        //从栈中获取运算数,由于运算数以字符串的形式储存
        //在用作计算时需转型为可计算类型,这里用double
        //pop()函数为移除栈顶的元素,并返回此元素
        double operand = Double.valueOf(stack.pop());
        //从栈中移除元素后如果栈已为空,则直接返回operand
        if (stack.isEmpty()) {
            return operand;
        }
        //继续从栈中获取操作符,根据操作符类型继续递归调用
        String operate = stack.pop();
        if (operate.equals("+")) {
            result = CalModel.popOpOffStack(stack) + operand;
        }
        else if (operate.equals("-")) {
            result = CalModel.popOpOffStack(stack) - operand;
        }
        else if (operate.equals("*")) {
            result = CalModel.popOpOffStack(stack) * operand;
        }
        else if (operate.equals("/")) {
            result = CalModel.popOpOffStack(stack) / operand;
        }
        return result;
    }

    //记录输入的运算数和操作符的栈
    private Stack<String> dataStack = new Stack<>();
    //是否在输入操作符,对连续输入操作符的情况则视为操作符的替换
    private boolean isOperate = false;

    @Override
    public void pushOperand(String operand) {
        //当输入运算数时直接压入stack,不会触发计算
        dataStack.add(operand);
        isOperate = false;
    }

    @Override
    public double pushOperate(String operate) {
        //当操作符时"+-*/"时,输入会继续
        //所以copy一份当前栈的数据作为参数传入进行计算并返回
        //当操作符时"="号时,则直接使用当前栈作为参数
        //因为"="是意味之后需要重新开始
        double result;
        if (isOperate) {
            dataStack.pop();//如果前一个是操作符,则将它替换
        }
        if (operate.equals("=")) {
            result = CalModel.popOpOffStack(dataStack);
        }
        else {
            @SuppressWarnings("unchecked")
                    Stack<String> tmpStack = (Stack<String>) dataStack.clone();
            result = CalModel.popOpOffStack(tmpStack);
            //计算完前面结果后把输入的运算符压入栈
            dataStack.add(operate);
            isOperate = true;
        }
        return result;
    }

    @Override
    public void reset() {
        dataStack.removeAllElements();
        isOperate = false;
    }
}
