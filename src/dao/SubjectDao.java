package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.School;
import bean.Subject;

//まだ未完成
public class SubjectDao extends DAO{
	public subject get(String cd,School school)throws Exception{
		return pass;
	}

	public List<Subject> filter(School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		return pass;
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