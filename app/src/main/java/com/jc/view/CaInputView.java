package com.jc.view;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;

/**
 * Created by YJZJB0051 on 2016-02-25.
 */
public class CaInputView {
    //首先定义好我们的"通信协议",也就是我们常说的"接口"概念
    //就好比,虽然我知道你的通信地址,但我电话是WCDMA信号
    //而你却使用的是GSM信号,也是收取不了
    //所以这里定义了我们"通信"函数
    public  interface InputHappend {
        //当用户输入运算数时,通知你它输入了"operand"这个数字
        public void operandIn(String operand);
        //当用户输入操作符时,通知你它输入"operate"这个运算符
        public void operateIn(String operate);
    }

    private List<Button> operands;//存放操作数Button
    private List<Button> operates;//存放运算符Button

    //保存这个"联系方式"
    //不需要指明dlg的实质是什么,只需定义它要实现CaInputView.InputHappend这个接口
    //这个就是面向接口编程的概念
    private  CaInputView.InputHappend dlg;

    //在构造函数中,定义了使用CaInputView类需要的两个参数
    //ac是Activity类,用来使用Activity提供的方法
    //delegate是实现了CaInputView.InputHappend的对象,我不用知道它是谁,只要适当时刻通知它即可
    public CaInputView(Activity ac, CaInputView.InputHappend delegate) {
        dlg = delegate;
        operands = new ArrayList<Button>();
        operates = new ArrayList<Button>();

        //获取ac存在系统的资源
        Resources res = ac.getResources();
        //通过Resource类的getIdentifier 方法,获取相应的资源
        //第一个参数为ID名，第二个为资源属性是ID或者是Drawable，第三个为包名
        for (int i=0;i<=9;i++) {
            int id_operand = res.getIdentifier("Operand" + i,"id", ac.getPackageName());
            Button btn_operand = (Button) ac.findViewById(id_operand);
            //压入数组
            operands.add(btn_operand);

            if (i<=5) {
                int id_operate = res.getIdentifier("Operate" + i, "id", ac.getPackageName());
                Button btn_operate = (Button) ac.findViewById(id_operate);
                operates.add(btn_operate);
            }
        }

        //为操作数添加事件,当用户触发时,通知dlg,发生operandIn
        for (Button btn : operands) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button cli_btn = (Button) v;
                    String text = cli_btn.getText().toString();
                    dlg.operandIn(text);
                }
            });
        }

        //为运算符添加事件,当用户触发时,通知dlg,发生operateIn
        for (Button btn :operates) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button cli_btn = (Button) v;
                    String text = cli_btn.getText().toString();
                    dlg.operateIn(text);
                }
            });
        }
    }
}
