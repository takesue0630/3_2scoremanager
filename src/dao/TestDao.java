package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

//まだ未完成
public class TestDao extends DAO{
	private String baseSql="select * from student join school where cd=?";

	//未完成
	public Test get(Student student,Subject subject,School school,int no) throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st = con.prepareStatement(
			"select * from subject where school_cd=?"
		);
		return pass;
	}

	public List<Test> PostFilter(ResultSet rSet,School school)throws Exception{
		return pass;
	}

	public List<Test> filter(int entYear,String classNum,String subject,int num,School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st = con.prepareStatement(
			"SELECT s.ent_year,t.class_num,sub.name,t.no "
			+ "FROM student as s "
			+ "JOIN test as t "
			+ "ON s.school_cd = t.school_cd "
			+ "JOIN subject as sub "
			+ "ON t.school_cd = sub.school_cd"
		);
		st.setInt(1, entYear);
		st.setString(2, school.getCd());

		return pass;
	}

	public boolean save(List<Test> list) throws Exception{
		return pass;
	}

	public boolean save(Test test,Connection connection) throws Exception{
		return pass;
	}
}
