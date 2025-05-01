package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
public class SubjectDao extends DAO{
	public Subject get(String cd,School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st=con.prepareStatement(
				"SELECT * FROM subject WHERE CD = ? AND SCHOOL_CD = ?"
				);
		st.setString(1, cd);
		st.setString(2, school.getCd());
		ResultSet rs=st.executeQuery();

		Subject s=new Subject();
		if (rs.next()) {
			s.setCd(rs.getString("cd"));
			School sc=new School();
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
		}

		st.close();
		con.close();

		return s;
	}

	//まだ未完成
	public List<Subject> filter(School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st = con.prepareStatement(
			"select * from subject where school_cd=?"
		);
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();

		List<Subject> list=new ArrayList<Subject>();
		while (rs.next()) {
			Subject s=new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setShool(school);
			list.add(s);
		}

		st.close();
		con.close();

		return list;
	}

	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();
		PreparedStatement st;
		boolean result = true;

		st=con.prepareStatement(
				"UPDATE subject SET cd = ?, name = ? WHERE cd = ? and school_cd = ?"
				);

	    st.setString(1, subject.getCd());       // 新しいcd
	    st.setString(2, subject.getName());     // 新しいname
	    st.setString(3, subject.getCd());
	    st.setString(4, subject.getSchool().getCd());

	    return result;

	}


    public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();
		PreparedStatement st;
		boolean result = true;

		st=con.prepareStatement(
				"DELETE FROM subject WHERE CD = ? and NAME = ?"
			);
	        st.setString(1, subject.getCd());
	        st.setString(2, subject.getName());

			st.close();
			con.close();

	        return result;
	 }
}