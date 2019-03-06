package com.bank.driver;

import java.util.Scanner;

import com.bank.daoimpl.Loginimpl;
import com.bank.daoimpl.Registerimpl;


public class App {

	public static void main(String[] args) {
		System.out.println("enter a choice");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();
		
			switch(ch)
			{
			case 0:		Registerimpl reg=new Registerimpl();
						reg.registration();
						break;
		
			case 1:   Loginimpl l=new Loginimpl();
						l.login();
						break;
			}
	
		}
	

		
		
		
	}


