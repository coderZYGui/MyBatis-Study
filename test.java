package com._520it._exercise;

import java.util.Scanner;

/*4.模拟登陆流程： 
（1）指定一个账号：admin 密码：66666 
（2）使用Scanner输入用户名和密码,最多有3次机会
（3）判断字符串相等使用equals方法，如果输入的账号与密码都正确则打印出："登陆成功，进入主菜单..." 
（4）如果输错则提示："您输入的账号或密码不正确，请重新输入，您还有?次机会"
（5）如果3次都输错则打印："连续三次登陆失败，退出系统！"*/

public class Number4 {
	public static void main(String[] args) {
		landing();
	}
	public static void landing(){
		for (int  i= 0;  i< 3; i++) {
			System.out.println("请输入您的账号:");
			Scanner sc = new Scanner(System.in);
			String a = sc.next();
			if(!a.equals(DateBase.user)){
				if(2-i != 0){
				System.out.println("您输入的账号或密码不正确，请重新输入，您还有"+(2-i)+"次机会");
				}
			}else{
				System.out.println("请输入您的密码:");
				Scanner sc1 = new Scanner(System.in);
				String a1 = sc.next();
				if(!a1.equals(DateBase.password)){
					if(2-i != 0){
					System.out.println("您输入的账号或密码不正确，请重新输入，您还有"+(2-i)+"次机会");
					}
				}else{
					System.out.println("登陆成功，进入主菜单...");
					DateBase.isture=true ;
					break;
				}
			}
		}
		if(!DateBase.isture){
			System.out.println("连续三次登陆失败，退出系统！");
		}
	}
}
class DateBase{
	public static String user = "admin";
	public static String password = "66666";
	public static boolean isture = false ;
}