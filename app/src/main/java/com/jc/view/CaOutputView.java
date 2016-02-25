package com.jc.view;

import android.app.Activity;
import android.widget.TextView;

import com.jc.calculator.R;

import org.w3c.dom.Text;

/**
 * Created by YJZJB0051 on 2016-02-25.
 */
public class CaOutputView {

    private TextView tv;

    //在CaOutputView中,不需要通知Activity发生了什么
    //只需要显示结果即可
    //所以只需利用Activity提供的方法
    //获取TextView对象用于显示结果即可

    //Java是一种强类型语言,即所有变量,参数,返回值等都必须指明类型
    //例如变量 tv,在声明时需指定其类型
    //同理, findViewByid()方法返回的是一个View对象
    //虽然我们知道 R.id.ResultOutput实质是一个 TextView对象
    //单编译器会认为 tv 的类型声明与返回的类型不符
    //所以要对 findViewByid()返回值进行类型转换,这在Java中是经常出现的
    public CaOutputView(Activity ac) {
        tv = (TextView) ac.findViewById(R.id.ResultOutput);
    }

    //定义CaOutputView拥有的方法
    //将输入的结果显示出来
    //函数实质是调用TextView的方法
    //虽然这样显得有点"多余",但当你的类功能想扩展或变复杂时
    //这样的封装是很有意义的
    public void outputData(String str) {
        //这里可以添加自己想做的东西,例如显示结果进行处理等
        tv.setText(str);
    }

    //可以方便扩展出不同的方法,只需要改变自己内部逻辑
    //这就是MVC的好处之一
}
