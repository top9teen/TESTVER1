package com.test.dao;

import com.test.util.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.test.model.LoginBean;
@Repository
public class LoginDao {

	
	public LoginBean login( String loEmail, String  loPassword) {
		
		LoginBean bean = new LoginBean();
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();
		
		
		try {
			sql.append(" SELECT * FROM  public.login1 WHERE lo_email = ? AND lo_password = ? ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1,loEmail);
			prepared.setString(2,loPassword);
			ResultSet rs = prepared.executeQuery();
			while (rs.next()) {
				bean.setLoEmail(rs.getString("lo_email" ));
				bean.setLoPassword(rs.getString("lo_password"));
				bean.setLoRole(rs.getString("lo_role" ));
			}
		
		
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
		
				
	}
	
	
	
	// end class
}
