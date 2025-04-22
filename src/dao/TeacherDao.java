package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends DAO{

	public Teacher get(String id) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from teacher join school on teacher.school_cd=school.cd where id=?"
		);
		st.setString(1, id);
		ResultSet rs=st.executeQuery();

		Teacher t=new Teacher();
		if (rs.next()) {
			t.setId(rs.getString("id"));
			t.setPassword(rs.getString("password"));
			t.setName(rs.getString("name"));
			School sc=new School();
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
			t.setSchool(sc);
		}

		st.close();
		con.close();

		return t;
	}

	public Teacher login(String id, String password) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from teacher join school on teacher.school_cd=school.cd where id=? and password=?"
		);
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		Teacher t=new Teacher();
		if (rs.next()) {
			t.setId(rs.getString("id"));
			t.setPassword(rs.getString("password"));
			t.setName(rs.getString("name"));
			School sc=new School();
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
			t.setSchool(sc);
		}

		st.close();
		con.close();

		return t;
	}
}
