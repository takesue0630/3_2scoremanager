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

	public List<String> filter(int entYear, String classNum, String subject, int num, School school) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st;

	    // SQL文の修正
	    st = con.prepareStatement(
	    	    "SELECT s.ent_year, t.class_num, t.student_no, s.name " +
	    	    "FROM student AS s " +
	    	    "JOIN test AS t ON s.no = t.student_no " +
	    	    "JOIN subject AS sub ON t.school_cd = sub.school_cd " +
	    	    "WHERE s.ent_year = ? " +
	    	    "AND t.class_num = ? " +
	    	    "AND sub.name = ? " +
	    	    "AND t.no = ?"
	    	);
	    // パラメータの設定
	    st.setInt(1, entYear);
	    st.setString(2, classNum);
	    st.setString(3, subject);
	    st.setInt(4, num);

	    // クエリ実行
	    ResultSet rs = st.executeQuery();

	    // 結果を格納するリスト
	    List<String> list = new ArrayList<>();

	    // 結果セットを処理
	    while (rs.next()) {
	        // データをリストに追加
	    	while (rs.next()) {
	    	    String row = rs.getInt("ent_year") + "," +
	    	                 rs.getString("class_num") + "," +
	    	                 rs.getString("student_no") + "," +
	    	                 rs.getString("name");
	    	    list.add(row);
	    	}
	    }
	    // クリーンアップ
	    st.close();
	    con.close();

	    return list;
	}

	public List<String> filter1(int entYear, String classNum, String subject, int num, School school) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st;

	    // SQL文の修正
	    st = con.prepareStatement(
	        "SELECT s.ent_year, t.class_num, sub.name, t.no "
	        + "FROM student AS s "
	        + "JOIN test AS t ON s.school_cd = t.school_cd "
	        + "JOIN subject AS sub ON t.school_cd = sub.school_cd "
	        + "WHERE s.ent_year = ? "
	        + "AND t.class_num = ? "
	        + "AND sub.name = ? "
	        + "AND t.no = ?"

			/*select s.ent_year,t.class_num,t.student_no,s.name
			from student as s
			join test as t
			on s.no = t.student_no*/
	    );

	    // パラメータの設定
	    st.setInt(1, entYear);
	    st.setString(2, classNum);
	    st.setString(3, subject);
	    st.setInt(4, num);

	    // クエリ実行
	    ResultSet rs = st.executeQuery();

	    // 結果を格納するリスト
	    List<String> list = new ArrayList<>();

	    // 結果セットを処理
	    while (rs.next()) {
	        // データをリストに追加
	    	while (rs.next()) {
	    	    String row = rs.getInt("ent_year") + "," +
	    	                 rs.getString("class_num") + "," +
	    	                 rs.getString("name") + "," +
	    	                 rs.getInt("no");
	    	    list.add(row);
	    	}
	    }

	    // クリーンアップ
	    st.close();
	    con.close();

	    return list;
	}

	public boolean save(List<Test> list) throws Exception{
		return pass;
	}

	public boolean save(Test test,Connection connection) throws Exception{
		return pass;
	}
}
