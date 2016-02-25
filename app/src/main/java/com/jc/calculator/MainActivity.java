package com.jc.calculator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.jc.view.CaInputView;
import com.jc.view.CaOutputView;

import java.util.ArrayList;
import java.util.List;

//Activity有其自身的生命周期,每个生命周期都对应着一个钩子函数
//如: onCreate(), onStart(), onResume(), onPause(), onStop() 等
//可以在对应的钩子函数中编写代码
//一般我们会在Activity被创建时添加我们的代码

public class MainActivity extends Activity implements CaInputView.InputHappend {

    //在这里,可以直接使用CaInputView和CaOutput类即可
    private CaInputView civ;
    private CaOutputView cov;

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
    }

    @Override
    public void operandIn(String operand) {
        //当回调函数执行时,在CaOutputView中显示出来
        cov.outputData(operand);
    }

    @Override
    public void operateIn(String operate) {
        cov.outputData(operate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
