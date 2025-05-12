package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends DAO{
	private String baseSql="select * from test join subject on test.subject_cd=subject.cd where";

	public List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		List<TestListStudent> list=new ArrayList<TestListStudent>();
		while (rSet.next()) {
			TestListStudent t=new TestListStudent();
			t.setSubjectName(rSet.getString("name"));
			t.setSubjectCd(rSet.getString("cd"));
			t.setNum(Integer.parseInt(rSet.getString("no")));
			t.setPoint(Integer.parseInt(rSet.getString("point")));
			list.add(t);
		}
		return list;
	}

	public List<TestListStudent> filter(Student student) throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st=con.prepareStatement(
			baseSql+" student_no=?"
		);
		st.setString(1, student.getNo());

		ResultSet rs=st.executeQuery();
		List<TestListStudent> list=postFilter(rs);

		st.close();
		con.close();

		return list;
	}
}