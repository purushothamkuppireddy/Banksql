package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transactions {
Scanner sc=new Scanner(System.in);
//Withdraw
	public void withdrawl( long accno,int amount) 
	{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root", "root");
			System.out.println("enter amount to withdraw");
			int amt=sc.nextInt();
				if(amt>amount)
				{
					System.out.println("insufficient balance plz deposit");
				}
				else
				{
		 		PreparedStatement ps1 = con.prepareStatement("update user set amount=? where accno=?");
				ps1.setInt(1,(amount-amt) );
				ps1.setLong(2,accno);
				ps1.executeUpdate();
				
				PreparedStatement ps2 = con.prepareStatement("select * from user where accno=?");
				ps2.setLong(1,accno );
				ResultSet rs2=ps2.executeQuery();
				
					while(rs2.next()) {
					System.out.println("remaining balance:"+rs2.getInt(6));
					}	
				}
		 	
		 	}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 	}
	}
	
//Deposit
	public void deposit(long accno,int amount)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = con.prepareStatement("update user set amount=? where accno=?");
			System.out.println("eneter amount to be deposited");
			int amt=sc.nextInt();
			ps.setInt(1,amt+amount );
			ps.setLong(2,accno);
			
			ps.executeUpdate();
		
		PreparedStatement ps2 = con.prepareStatement("select * from user where accno=?");
		ps2.setLong(1,accno );
		ResultSet rs2=ps2.executeQuery();
		
			while(rs2.next()) {
			System.out.println("After deposit: "+rs2.getInt(6));
			}	
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void exit()
	{
		System.out.println("Thank you");
		
	}


}
