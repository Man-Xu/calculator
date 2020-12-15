package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//用于储存数据模型
public class MyViewModel extends ViewModel {
    public MutableLiveData<String> mainNum;//主数值
    public boolean havePoint = false;//是否含有小数点
    public String sign1 = "",sign2="";//储存运算符号
    public String num[] = {"",""};//储存参与计算的数值

    public MutableLiveData<String> getMainNum() {
        if (mainNum == null) {
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");

        }
        return mainNum;
    }

    public void setMainNum(String n) {
        if (mainNum.getValue().equals("0")) {
            mainNum.setValue(n);
        } else {
            mainNum.setValue(mainNum.getValue() + n);
        }
    }

    public String mainNumwithNum_0_Tocal() {
        String value = "0";
        if (mainNum.getValue().contains(".") || num[0].contains(".")) {
            switch (sign1) {
                case "+":
                    value = String.valueOf(Double.valueOf(num[0])+Double.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0])-Double.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0])*Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0])/Double.valueOf(mainNum.getValue()));
            }
        }else {
            switch (sign1) {
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
            }
        }
        return value;
    }
    public String mainNumWithNum_1_Tocal(){
        String value = "0";
        if (mainNum.getValue().contains(".") || num[1].contains(".")) {
            switch (sign2) {
                case "*":
                    value = String.valueOf(Double.valueOf(num[1])*Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1])/Double.valueOf(mainNum.getValue()));
            }
        }else {
            switch (sign2) {
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
            }
        }
        return value;
    }
}
