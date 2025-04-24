package dao;

import java.sql.ResultSet;

import bean.Student;
import bean.TestListStudent;

//まだ未完成
public class TestListStudentDao extends Dao{
	private String baseSql="select * from student join school where cd=?";

	public List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		return pass;
	}

	public List<TestListStudent> filter(Student student) throws Exception{
		return pass;
	}
}
