package com.gdut.atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyAtm {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        int number;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("=============欢迎您进入到MY_ATM系统===============");
            System.out.println("1、登录账户");
            System.out.println("2、注册账户");
            System.out.println("3、退出系统");
            System.out.println("请您选择操作：");
            number = scanner.nextInt();
            switch (number){
                case 1:
                    if(customers.isEmpty()){
                        System.out.println("当前系统无任何账户，请先注册在登陆！");
                    }else{
                        System.out.println("=============欢迎您进入到登录操作===============");
                        int inAccount;
                        Customer cus1 = null;
                        boolean isSignIn = false;
                        while(true) {
                            System.out.println("请您输入登录的卡号：");
                            inAccount = scanner.nextInt();
                            for (Customer customer : customers) {
                                cus1 = customer;
                                if (inAccount == cus1.getAccount()) {
                                    while (true) {
                                        System.out.println("请输入您的登录密码：");
                                        if (Arrays.equals(customer.getCode(), scanner.next().toCharArray())) {
                                            isSignIn = true;
                                            System.out.println("欢迎您，" + cus1.getName() + "先生/女士进入到系统，您可开始办理你的业务了");
                                            break;
                                        } else {
                                            System.out.println("您的密码不正确！");
                                        }
                                    }
                                }
                            }
                            if(!isSignIn){
                                System.out.println("卡号不存在，请确认！");
                            }else {
                                break;
                            }
                        }
                        label2:
                        while(true){
                            System.out.println("=============欢迎您进入到操作界面===============");
                            System.out.println("1、查询");
                            System.out.println("2、存款");
                            System.out.println("3、取款");
                            System.out.println("4、转账");
                            System.out.println("5、修改密码");
                            System.out.println("6、返回登录界面");
                            System.out.println("7、注销账户");
                            System.out.println("请您输入操作命令：");
                            number = scanner.nextInt();
                            switch (number){
                                case 1:
                                    cus1.query();
                                    break;
                                case 2:
                                    cus1.deposit();
                                    break;
                                case 3:
                                    cus1.withdraw();
                                    break;
                                case 4:
                                    if(customers.size() == 1){
                                        System.out.println("没有可以转账的用户，请先注册新用户！");
                                        break;
                                    }
                                    label3:
                                    while(true) {
                                        System.out.println("=============欢迎您进入账户转账操作===============");
                                        System.out.println("请输入转账目标账户：");
                                        inAccount = scanner.nextInt();
                                        Customer cus2;
                                        boolean isTransfer = false;
                                        for (Customer customer : customers) {
                                            cus2 = customer;
                                            if (cus2.getAccount() == inAccount) {
                                                System.out.println("请输入目标用户的姓名");
                                                String inName = scanner.next();
                                                if (cus2.getName().equals(inName)) {
                                                    isTransfer = true;
                                                    cus1.transfer(cus2);
                                                    break label3;
                                                } else {
                                                    System.out.println("转账失败，目标用户姓名错误！");
                                                }
                                            }
                                        }
                                        if (!isTransfer){
                                            System.out.println("账户不存在，请重新输入！");
                                        }
                                    }
                                    break;
                                case 5:
                                    cus1.modify();
                                    break;
                                case 6:
                                    break label2;
                                case 7:
                                    customers.remove(cus1);
                                    System.out.println("账户注销成功");
                                    break label2;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("=============欢迎您进入到开户操作===============");
                    Customer cus = new Customer();
                    System.out.println("请您输入账户名称：");
                    cus.setName(scanner.next());
                    char[] code = new char[6];
                    char[] code1;
                    boolean isSetCode = false;
                    label1:
                    while(!isSetCode) {
                        System.out.println("请您输入账户密码：");
                        code1 = scanner.next().toCharArray();
                        if(code1.length != 6){
                            System.out.println("密码格式不正确，请输入六位数字密码！");
                        }else{
                            for (char c : code1) {
                                if (!(c >= '0' && c <= '9')) {
                                    System.out.println("密码格式不正确，请输入六位数字密码！");
                                    continue label1;
                                }
                            }
                            isSetCode = true;
                        }
                        for (int i = 0; i < 6; i++) {
                            code[i] = code1[i];
                        }
                        cus.setCode(code1);
                    }
                    boolean isSetLimit = false;
                    while (!isSetLimit) {
                        System.out.println("请您输入确认密码：");
                        code1 = scanner.next().toCharArray();
                        if(!(Arrays.equals(code1, cus.getCode()))){
                            System.out.println("两次输入的密码不一致！");
                        }else{
                            while(true) {
                                System.out.println("请设置当次取现额度：");
                                double limit01 = scanner.nextDouble();
                                if (limit01 < 0) {
                                    System.out.println("输入错误，请重新输入");
                                } else {
                                    cus.setLimit(limit01);
                                    isSetLimit = true;
                                    int account01 = (int) (Math.random() * 100000000);
                                    System.out.println("恭喜您，" + cus.getName() + "先生/女士，您开户完成，" +
                                            "您的卡号是：" + account01);
                                    cus.setAccount(account01);
                                    customers.add(cus);
                                    break;
                                }
                            }
                        }

                    }
                    break;
                case 3:
                    return;

            }
        }
    }
}
