package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends DAO{
	public School get(String cd) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from school where cd=?"
		);
		st.setString(1, cd);
		ResultSet rs=st.executeQuery();

		School s=new School();
		if (rs.next()) {
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
		}

		st.close();
		con.close();

		return s;
	}
}
