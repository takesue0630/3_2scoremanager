package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Student;
import bean.Subject;

//まだ未完成
public class TestDao extends DAO{
	private String baseSql="select * from student join school where cd=?";

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

	public List<Test> filter(int entYear,String classNum,Subject subject,int num,School school)throws Exception{
		return pass;
	}

	public boolean save(List<Test> list) throws Exception{
		return pass;
	}

	public boolean save(Test test,Connection connection) throws Exception{
		return pass;
	}
}
