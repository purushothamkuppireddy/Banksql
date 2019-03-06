package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import com.bank.dao.Register;
import com.bank.model.User;

public class Registerimpl implements Register {
	 static User u =new User();
	
 public  User setInfo()
	{
		Random r=new Random();
		
	Scanner sc=new Scanner(System.in);
	System.out.println("enter details ");
	System.out.println("eneter aadharno");
	u.setAdharno(sc.nextLong());
	boolean b=validate();
	if(b) {
		System.out.println("enter name");
		u.setName(sc.next());
		long accountno= r.nextLong();
		u.setAccountno(Math.abs(accountno));
		System.out.println("enter phone");
		u.setPhone(sc.nextLong());
		System.out.println("eneter password");
		u.setPwd(sc.nextLong());
		System.out.println("enter balance");
		u.setAmount(sc.nextInt());
	//System.out.println("Accno:"+u.getAccountno());
	return u;
	}
	return null;
	
	}
	
	
	boolean validate() {
		boolean b = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from user where aadhar_no=?");
			ps.setLong(1, u.getAdharno());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Acc already exists");
				b = false;
			} else {
				b = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		// TODO Auto-generated method stub

	}

	
	 public void registration() {

		Registerimpl rg = new Registerimpl();
		if(rg.setInfo()!=null) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?,?)");

			ps.setLong(1, u.getAccountno());
			ps.setString(2, u.getName());
			ps.setLong(3, u.getAdharno());
			ps.setLong(4, u.getPhone());
			ps.setLong(5, u.getPwd());
			ps.setInt(6, u.getAmount());
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println("done");
			} else {
				System.out.println("could not insert data");
			}
			System.out.println("acc no is " + u.getAccountno());

			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		

	}



		
		
		
		
	}


