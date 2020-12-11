package com.cablesfb.modeldao;

import java.sql.*;

import com.cablesfb.helper.Connector;
import com.cablesfb.helper.Validate;
import com.cablesfb.model.Person;


public class PersonDAO  implements Validate{

	
	public Person validate(Person per) {
		try {
			int r = 0;
			Connection con;
			Connector cn = new Connector();
			con = cn.getLoginConnection();
			PreparedStatement ps;
			ResultSet rs;
			String sql = "SELECT * FROM `usuario` WHERE correo = ? AND contraseña = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, per.getEmail());
			ps.setString(2, per.getPassword());
			rs = ps.executeQuery();


			while(rs.next()) {
				r=r+1;
				per.setEmail(rs.getString("correo"));
				per.setPassword(rs.getString("contraseña"));
				per.setName(rs.getString("nombre"));
			}
			if(r==1) {
				return per;
			}else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("error de validacion");
			return null;
		}
		
	}

}
