package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;//组件存储
    private MyViewModel myViewModel;//数据模型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);//获取所有控件
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型

        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {//监听mainNum数据发生改变
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让myTextView显示mainNum的数据
                //让textView显示式子
                if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("")){
                        binding.textView.setText(myViewModel.getMainNum().getValue());
                    }else{
                        binding.textView.setText(myViewModel.num[0]+myViewModel.sign1+myViewModel.getMainNum().getValue());
                    }
                }else {//如果是像a+b*c这种形式的式子时
                    binding.textView.setText(myViewModel.num[0]+myViewModel.sign1+myViewModel.num[1]+myViewModel.sign2+myViewModel.getMainNum().getValue());
                }
            }
        });
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("5");
            }
        });binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("6");
            }
        });binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("9");
            }
        });
        binding.button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint=true;
                }
            }
        });
        binding.button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="+";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果是像a+b这种形式的式子
                    myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else {//如果是像a+b*c这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果是像a+b这种形式的式子
                    myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else {//如果是像a+b*c这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="*";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else  if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按顺序进行计算
                        myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                        myViewModel.sign1="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{
                        myViewModel.num[1]=myViewModel.getMainNum().getValue();
                        myViewModel.sign2="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果是像 a+b*c这种形式的式子时
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2="*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="/";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else  if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按顺序进行计算
                        myViewModel.num[0]=myViewModel.mainNumwithNum_0_Tocal();
                        myViewModel.sign1="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{
                        myViewModel.num[1]=myViewModel.getMainNum().getValue();
                        myViewModel.sign2="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果是像 a+b*c这种形式的式子时
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2="/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.sign2="";
                myViewModel.sign1="";
                myViewModel.num[1]="";
                myViewModel.num[0]="";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint=false;
            }
        });
        binding.button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumwithNum_0_Tocal());
                        if(myViewModel.getMainNum().getValue().contains("."))
                            myViewModel.havePoint=true;
                        else
                            myViewModel.havePoint=false;
                        myViewModel.num[0]="";
                        myViewModel.sign1="";
                    }
                }else{
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumwithNum_0_Tocal());
                    if(myViewModel.getMainNum().getValue().contains("."))
                        myViewModel.havePoint=true;
                    else
                        myViewModel.havePoint=false;
                    myViewModel.num[0]="";
                    myViewModel.sign1="";
                }

                binding.textView.setText(myViewModel.getMainNum().getValue());
            }
        });
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().getValue().equals("0");
                    }
                }
            }
        });

    }
}