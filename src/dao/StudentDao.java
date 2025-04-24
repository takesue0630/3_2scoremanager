package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO{

	private String baseSql="select * from student join school where cd=?";

	public Student get(String no) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from student join school on student.school_cd=school.cd where no=?"
		);
		st.setString(1, no);
		ResultSet rs=st.executeQuery();

		Student s=new Student();
		if (rs.next()) {
			s.setNo(rs.getString("no"));
			s.setName(rs.getString("name"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setClassNum(rs.getString("class_num"));
			s.setIsAttend(rs.getBoolean("is_attend"));
			School sc=new School();
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
			s.setSchool(sc);
		}

		st.close();
		con.close();

		return s;
	}

	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception{
		String cd = school.getCd();
		List<Student> list=new ArrayList<Student>();
		while (resultSet.next()) {
			if (resultSet.getString("cd").equals(cd)) {
				Student s=new Student();
				s.setNo(resultSet.getString("no"));
				s.setName(resultSet.getString("name"));
				s.setEntYear(resultSet.getInt("ent_year"));
				s.setClassNum(resultSet.getString("class_num"));
				s.setIsAttend(resultSet.getBoolean("is_attend"));
				School sc=new School();
				sc.setCd(resultSet.getString("cd"));
				sc.setName(resultSet.getString("name"));
				s.setSchool(sc);
				list.add(s);
			}
		}
		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception{

		Connection con=getConnection();
		PreparedStatement st;

		if (isAttend) {
			st=con.prepareStatement(
				baseSql+" and ent_year=? and class_num=? and is_attend=?"
			);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
			st.setBoolean(4, isAttend);
		} else {
			st=con.prepareStatement(
				baseSql+" and ent_year=? and class_num=?"
			);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
		}

		ResultSet rs=st.executeQuery();
		List<Student> list=postFilter(rs,school);

		st.close();
		con.close();

		return list;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception{

		Connection con=getConnection();
		PreparedStatement st;

		if (isAttend) {
			st=con.prepareStatement(
				baseSql+" and ent_year=? and is_attend=?"
			);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setBoolean(3, isAttend);
		} else {
			st=con.prepareStatement(
				baseSql+" and ent_year=?"
			);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
		}

		ResultSet rs=st.executeQuery();
		List<Student> list=postFilter(rs,school);

		st.close();
		con.close();

		return list;
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception{

		Connection con=getConnection();
		PreparedStatement st;

		if (isAttend) {
			st=con.prepareStatement(
				baseSql+" and is_attend=?"
			);
			st.setString(1, school.getCd());
			st.setBoolean(2, isAttend);
		} else {
			st=con.prepareStatement(
				baseSql
			);
			st.setString(1, school.getCd());
		}

		ResultSet rs=st.executeQuery();

		List<Student> list=postFilter(rs,school);

		st.close();
		con.close();

		return list;
	}

	public boolean save(Student student) throws Exception{

		Connection con=getConnection();
		PreparedStatement st;
		boolean result;

		if (get(student.getNo()).getNo()==null) {

			st=con.prepareStatement(
				"insert into student values (?,?,?,?,?,?)"
			);
			st.setString(1, student.getNo());
			st.setString(2, student.getName());
			st.setInt(3, student.getEntYear());
			st.setString(4, student.getClassNum());
			st.setBoolean(5, student.getIsAttend());
			st.setString(6, student.getSchool().getCd());

		} else {

			st=con.prepareStatement(
				"update student set name=?,class_num=?,is_attend=? where no=?"
			);
			st.setString(1, student.getName());
			st.setString(2, student.getClassNum());
			st.setBoolean(3, student.getIsAttend());
			st.setString(4, student.getNo());

		}

		if (st.executeUpdate()>0) {
			result=true;
		} else {
			result=false;
		}

		st.close();
		con.close();

		return result;
	}
}
