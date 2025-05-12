package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO{
	private String baseSql="select * from test join student on test.student_no=student.no where";

	public List<TestListSubject> postFilter(ResultSet rSet) throws Exception{
		List<TestListSubject> list=new ArrayList<TestListSubject>();
		while (rSet.next()) {
			TestListSubject t=new TestListSubject();
			t.setEntYear(Integer.parseInt(rSet.getString("ent_year")));
			t.setStudentNo(rSet.getString("student_no"));
			t.setStudentName(rSet.getString("name"));
			t.setClassNum(rSet.getString("test.class_num"));
			Map<Integer,Integer> points=new HashMap<>();
			t.setPoints(points);
			t.setPoint(Integer.parseInt(rSet.getString("test.no")), Integer.parseInt(rSet.getString("point")));
			list.add(t);
		}
		return list;
	}

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st=con.prepareStatement(
			baseSql+" ent_year=? and test.class_num=? and subject_cd=? and test.school_cd=?"
		);
		st.setInt(1, entYear);
		st.setString(2, classNum);
		st.setString(3, subject.getCd());
		st.setString(4, school.getCd());

		ResultSet rs=st.executeQuery();
		List<TestListSubject> list=postFilter(rs);

		st.close();
		con.close();

		return list;
	}
}
