package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.model.User;


public class Loginimpl implements Login {
User u =new User();
	public void login()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter account no");
		long A=sc.nextLong();
		System.out.println("enter the pwd");
		long B=sc.nextLong();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from user where accno=? and pwd=?");
			
			ps.setLong(1,A);
			
			ps.setLong(2, B);
			ResultSet rs = ps.executeQuery();
			
					if (rs.next()) 
					{
						System.out.println("Welcome to online transaction: "+rs.getString(2));
						
						System.out.println("Enter the choice for 1.withdrawl, 2.deposit, 3.exit");
						//long Acc=rs.getLong(1);
						int x=sc.nextInt();
						Transactions t=new Transactions();
				  
						switch(x) {
				  
						case 1: t.withdrawl(rs.getLong(1),rs.getInt(6));
				  			break;
				  
						case 2: t.deposit(rs.getLong(1),rs.getInt(6));
				  			break;
				  
						case 3: t.exit();	
				  			System.exit(0);
				  			break;
						}	
				  		}
					
			
				else {
				System.out.println("Invalid credentials");
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
		
	
	
	
	
}
