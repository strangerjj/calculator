package com.jc.calculator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.jc.interfaces.ICalculator;
import com.jc.model.CalModel;
import com.jc.view.CaInputView;
import com.jc.view.CaOutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Activity有其自身的生命周期,每个生命周期都对应着一个钩子函数
//如: onCreate(), onStart(), onResume(), onPause(), onStop() 等
//可以在对应的钩子函数中编写代码
//一般我们会在Activity被创建时添加我们的代码

public class MainActivity extends Activity implements CaInputView.InputHappend {

    //在这里,可以直接使用CaInputView和CaOutput类即可
    private CaInputView civ;
    private CaOutputView cov;
    private ICalculator calModel;

    //通过一个字符串在连续输入数字时讲数字连在一起:"8"+"8"="88"
    private String number = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //调用父类的构造函数
        //传入的Bundle对象savedInstanceState用于信息的记录
        //这里先不展开
        super.onCreate(savedInstanceState);

        //Activity就是通过SetContentView()方法加载视图
        //通过R类的介绍,想必已经知道传入参数的意义了
        //这里相当于 this.setContentView(), this指向MainActivity实例本身
        //可以说Context使Activity具有"超级功能"
        //另外,Java中的this始终会指向实例本身,不会像js中的this那样作用域发生改变
        //所以可以很放心的省略而不用显示指明,但要清楚是因什么而可以使用这些方法
        //例如,我们在构造其他类的时候,在其内部就不能使用一些方法了
        //这是在初次编写Android经常遇到的困惑,为什么有的函数突然就"失效"了
        setContentView(R.layout.activity_main);

        //初始化CaInputView类
        //因为在CaInputView中,定义了两个参数
        //一个是需要传入Activity的实例,那传入自身则可,所以用this
        //第二个参数,我们目的是想通知MainActivity用户的行为
        civ = new CaInputView(this, this);
        cov = new CaOutputView(this);
        calModel = new CalModel();

//        //这里构造一个测试栈来测试一下popOpOffStack函数
//        Stack<String> test = new Stack<String>();
//        test.push("2");
//        test.push("+");
//        test.push("1");
//        test.push("*");
//        test.push("2");
//        test.push("-");
//        test.push("3");
//        double result = CalModel.popOpOffStack(test);
//        //利用我们的CaOutputView实例输入
//        //由于result是double类型,所以要转型显示
//        cov.outputData(String.valueOf(result));
    }

    @Override
    public void operandIn(String operand) {
        //对首位为"0"作处理
        number = number.equals("0")? operand : number + operand;
        //当回调函数执行时,在CaOutputView中显示出来
        cov.outputData(number);
    }

    @Override
    public void operateIn(String operate) {
        //equalIgnoreCase()忽略大小写
        if (operate.equalsIgnoreCase("c")) {
            calModel.reset();
            number = "0";
            cov.outputData(number);
            return;//终止后面的代码执行
        }
        //输入操作符时先将"累计"的number压入栈再将自己压入
        calModel.pushOperand(number);
        double result = calModel.pushOperate(operate);
        //如果是整数则消除小数点 8.0->8
        if (result % 1d == 0d) {
            int tmp = Double.valueOf(result).intValue();
            cov.outputData(String.valueOf(tmp));
        }
        else {
            cov.outputData(String.valueOf(result));
        }
        number = "0";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
