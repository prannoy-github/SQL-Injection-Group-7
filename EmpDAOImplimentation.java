package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bo.EmpBO;


public class EmpDAOImplimentation implements EmpDAO {
	
	// initializing obj
	PreparedStatement ps = null;
	Statement st = null;
	Connection con = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	EmpBO bo = null;

	@Override
	public int checkEmp(String name, String no) {

		int cnt = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establishing jdbc connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pranay","pranay");
			// prepare query
			String qry = "select count(*) from empdetails where ename='" + name + "' and eid='" + no + "'";
			//create statement
			st = con.createStatement();
			//execute query
			rs = st.executeQuery(qry);

			
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			System.out.println("count="+cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} // catch
		return cnt;
	}

	@Override
	public EmpBO getEmp(String name, String no) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establishing jdbc connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pranay","pranay");
			// prepare query
			String qry="select eid,ename,dept,address,salary from empdetails where ename='" + name + "' and eid='" + no + "'";
			
			st = con.createStatement();
			rs1 = st.executeQuery(qry);
			bo = new EmpBO();
			
			if (rs1.next()) {
				bo.setEid(rs1.getString(1));
				bo.setEname(rs1.getString(2));
				bo.setDept(rs1.getString(3));
				bo.setAddress(rs1.getString(4));
				bo.setSalary(rs1.getString(5));
			} 

		} // try
		catch (Exception e) {
			e.printStackTrace();
		} // catch
		return bo;
	}
	@Override
	public EmpBO editEmp(String no) {
		
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// establishing jdbc connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pranay","pranay");
		// prepare query
		String qry="select eid,ename,dept,address,salary from empdetails where eid='" + no + "'";
		
		st = con.createStatement();
		rs1 = st.executeQuery(qry);
		bo = new EmpBO();
		
		if (rs1.next()) {
			bo.setEid(rs1.getString(1));
			bo.setEname(rs1.getString(2));
			bo.setDept(rs1.getString(3));
			bo.setAddress(rs1.getString(4));
			bo.setSalary(rs1.getString(5));
		} 
		System.out.println("dao:"+bo.getAddress());

	} // try
	catch (Exception e) {
		e.printStackTrace();
	} // catch
	return bo;
	}
	@Override
	public int updateEmp(EmpBO bo) {

		int cnt = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establishing jdbc connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pranay","pranay");
			// prepare query
			String qry = "update empdetails set  ename='" +bo.getEname() + "',dept='"+bo.getDept()+"',address='"+bo.getAddress()+"',salary='"+bo.getSalary()+"' where eid='" + bo.getEid() + "'";
			//create statement
			st = con.createStatement();
			//execute query
			cnt=st.executeUpdate(qry);
			System.out.println("count="+cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} // catch
		return cnt;
	}

	public void destroy() {
		try {
			if (st != null)
				st.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}



	
	
}