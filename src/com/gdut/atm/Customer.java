package com.gdut.atm;

import java.util.Arrays;
import java.util.Scanner;

class Customer {
    private String name;//名字
    private int account;//账号
    private char[] code;//密码
    private double balance;//余额
    private double limit;//额度
    Scanner scanner = new Scanner(System.in);



    //查询
    public void query(){
        System.out.println("=============您当前账户详情信息如下===============");
        System.out.println("卡号：" + account);
        System.out.println("户主：" + name);
        System.out.println("余额：" + balance);
        System.out.println("当次取现额度：" + limit);
    }

    //存款
    public void deposit(){
        System.out.println("=============欢迎您进入账户存款操作===============");
        System.out.println("请您输入存款金额（金额不小于0）：");
        double depositData = 0.0;
        while(true) {
            depositData = scanner.nextDouble();
            if (depositData >= 0) {
                balance += depositData;
                System.out.println("存款成功");
                break;
            } else {
                System.out.println("输入金额错误，请重新输入：");
            }
        }
    }

    //取款
    public void withdraw(){
        System.out.println("=============欢迎您进入账户取款操作===============");
        System.out.println("请您输入取款金额：");
        double withdrawData = 0.0;
        while(true) {
            withdrawData = scanner.nextDouble();
            if (withdrawData > this.balance){
                System.out.println("余额不足，请重新输入金额：");
            }else if (withdrawData < 0){
                System.out.println("输入金额数据错误，请重新输入：");
            } else if (withdrawData > limit) {
                System.out.println("取款失败，超过本次取款额度，请重新输入：");
            } else{
                balance -= withdrawData;
                System.out.println("取款成功，请拿走你的钞票");
                break;
            }
        }
    }

    //转账
    public void transfer(Customer cus){
        System.out.println("您当前转账额度为：" + this.limit);
        System.out.println("请您输入转账金额：");
        double transferData = 0.0;
        while(true) {
            transferData = scanner.nextDouble();
            if (transferData > this.limit){
                System.out.println("超过当次转账额度，请重新输入：");
            } else if (transferData < 0) {
                System.out.println("输入数据有误，请重新输入：");
            } else if (transferData > this.balance) {
                System.out.println("余额不足，请重新输入：");
            }else{
                cus.balance += transferData;
                this.balance -= transferData;
                System.out.println("转账成功，转账金额为：" + transferData);
                break;
            }
        }
    }

    //修改密码
    public void modify(){
        System.out.println("=============欢迎您进入账户密码修改操作===============");
        char[] modifyCode = new char[20];
        char[] modifyCode02 = new char[20];
        label1:
        while(true){
            System.out.println("请输入您的新六位数字密码：");
            modifyCode = scanner.next().toCharArray();
            if (modifyCode.length != 6 ){
                System.out.println("输入密码格式不对，请重新输入新六位数字密码：");
            }else{
                for (int i = 0; i < modifyCode.length; i++) {
                    if (!(modifyCode[i] >= '0' && modifyCode[i] <= '9')){
                        System.out.println("输入密码格式不对，请重新输入新六位数字密码：");
                        break label1;
                    }
                }
                if(Arrays.equals(modifyCode, this.getCode())){
                    System.out.println("密码与原密码相同，请重新输入！");
                    continue;
                }

                System.out.println("请再次输入您的新六位数字密码：");
                while(true){
                    modifyCode02 = scanner.next().toCharArray();
                    if (modifyCode02.length != 6){
                        System.out.println("密码不一致，请重新输入：");
                    }else {
                        if(Arrays.equals(modifyCode02, modifyCode)){
                            for (int i = 0; i < modifyCode02.length; i++) {
                                code[i] = modifyCode02[i];
                            }
                            System.out.println("密码修改成功！");
                            break label1;
                        }else {
                            System.out.println("密码不一致，请重新输入：");
                        }
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public char[] getCode() {
        return code;
    }

    public void setCode(char[] code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}














