package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO{
	private String baseSql="select * from student join school where cd=?";

	public Test get(Student student,Subject subject,School school,int no) throws Exception{
		Test test = new Test();

		test.setSchool(school);
		test.setNo(no);

		return test;
	}

	public List<Student> PostFilter(ResultSet rSet, School school) throws Exception {
	    String cd = school.getCd();
	    List<Student> list = new ArrayList<Student>();
	    while (rSet.next()) {
	        if (rSet.getString("cd").equals(cd)) {
	            Student s = new Student();
	            s.setNo(rSet.getString("no"));
	            s.setName(rSet.getString("name"));
	            s.setEntYear(rSet.getInt("ent_year"));
	            s.setClassNum(rSet.getString("class_num"));
	            s.setIsAttend(rSet.getBoolean("is_attend"));
	            School sc = new School();
	            sc.setCd(rSet.getString("cd"));
	            sc.setName(rSet.getString("name"));
	            s.setSchool(sc);
	            list.add(s); // ✅ Student型のリストに追加
	        }
	    }
	    return list;
	}


	public List<Test> filter(int entYear, String classNum, String subject, int num, School school) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st;

	    // SQL文の修正
	    st = con.prepareStatement(
	    	"select * from test join student on test.student_no=student.no where ent_year=? and test.class_num=? and subject_cd=? and test.no=? and test.school_cd=?;"
	    );
	    // パラメータの設定
	    st.setInt(1, entYear);
	    st.setString(2, classNum);
	    st.setString(3, subject);
	    st.setInt(4, num);
	    st.setString(5, school.getCd());

	    // クエリ実行
	    ResultSet rs = st.executeQuery();

	    // 結果を格納するリスト
	    List<Test> list = new ArrayList<>();

	    // 結果セットを処理
	    while (rs.next()) {
			Test test=new Test();
			Student student=new Student();
			student.setNo(rs.getString("student.no"));
			student.setName(rs.getString("student.name"));
			student.setEntYear(rs.getInt("ent_year"));
			test.setStudent(student);
			test.setClassNum(rs.getString("test.class_num"));
			Subject s=new Subject();
			s.setCd(subject);
			test.setSubject(s);
			test.setSchool(school);
			test.setNo(rs.getInt("test.no"));
			test.setPoint(rs.getInt("point"));
			list.add(test);
		}

	    // クリーンアップ
	    st.close();
	    con.close();

	    return list;
	}

	public boolean save(List<Test> list) throws Exception {
		Connection con=getConnection();
		boolean result=true;

		for (Test test:list) {
			if (save(test,con)) {
				result=false;
			}
		}

		con.close();

		return result;
	}

	public boolean delete(String student, String subject, String school, int no) throws Exception {
		Connection con=getConnection();
		PreparedStatement st;
		boolean result = false;

		st=con.prepareStatement(
			"DELETE FROM test WHERE student_no=? and subject_cd=? and school_cd=? and no=?"
		);
        st.setString(1, student);
        st.setString(2, subject);
        st.setString(3, school);
        st.setInt(4, no);

        int count = st.executeUpdate();  // ← SQL実行

	    if (count > 0) {
	        result = true;  // 1行以上追加されたら成功
	    }

		st.close();
		con.close();

        return result;
	}

	private boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement st;
		boolean result;

		st=connection.prepareStatement(
			"update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=?"
		);
		st.setInt(1, test.getPoint());
		st.setString(2, test.getStudent().getNo());
		st.setString(3, test.getSubject().getCd());
		st.setString(4, test.getSchool().getCd());
		st.setInt(5, test.getNo());

		if (st.executeUpdate()>0) {
			result=true;
		} else {
			result=false;
		}

		st.close();

		return result;
	}
}
