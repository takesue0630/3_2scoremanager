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
	    	"select * from test join student on test.student_no=student.no where ent_year=? and test.class_num=? and subject_cd=? and test.no=? and test.school_cd=?"
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
			test.setC
			list.add(test);
		}

	    // クリーンアップ
	    st.close();
	    con.close();

	    return list;
	}

	public boolean save(List<String> list) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st = null;  // stを最初にnullで初期化
	    boolean result = true;
	    int listSize = list.size() / 3;

	    // リストの内容を表示
	    System.out.println("リストのサイズ: " + listSize);
	    for (String item : list) {
	        System.out.println(item);
	    }

	    try {
	        for (int i = 0; i < listSize; i++) {
	            // リストの要素を1つずつ変数に代入
	            String studentNo = list.get(i * 3); // 1つ目の値
	            String pointStr = list.get(i * 3 + 1); // 2つ目の値
	            String subjectName = list.get(i * 3 + 2); // 3つ目の値
	            String num = list.get(i * 3 + 3); // 4つ目の値
	            String subject = list.get(list.size()-1);

	            // デバッグ出力
	            System.out.println("学生番号: " + studentNo);
	            System.out.println("科目名: " + subjectName);
	            System.out.println("点数: " + pointStr);
	            System.out.println("回数: " + num);
	            System.out.println("科目コード"+subject);

	            // SQL文の修正　(入学年,クラス,番号学生番号,氏名にあう生徒の点数を上書きする)
	            st = con.prepareStatement(
	                "UPDATE test " +
	                "SET point = ? " +
	                "WHERE student_no = ? "+
	                "and subject_cd = ?"
	            );

	            // パラメータの設定
	            st.setInt(1, Integer.parseInt(pointStr));  // 点数を設定
	            st.setString(2, studentNo);  // 学生番号を設定
	            st.setString(3,subject);

	            // クエリ実行
	            st.executeUpdate();  // executeUpdateに修正
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        result = false;  // エラー時にfalseを返す
	    } finally {
	        if (st != null) {
	            try {
	                st.close();  // stを確実に閉じる
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();  // conを確実に閉じる
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return result;
	}

	public boolean delete(String studentNo) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = null;
	    boolean result = false;

	    try {
	        st = con.prepareStatement(
	            "DELETE FROM test WHERE student_no = ?"
	        );
	        st.setString(1, studentNo);
	        int rowsAffected = st.executeUpdate();
	        result = (rowsAffected > 0);
	    } finally {
	        if (st != null) st.close();
	        con.close();
	    }

	    return result;
	}

	public boolean save(Test test, Connection connection) throws Exception {
	    String sql = "INSERT INTO test (student_no, subject, score) VALUES (?, ?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, test.getNo());
	        stmt.setString(2, test.getSubject().getName());
	        stmt.setInt(3, test.getPoint());

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0; // 成功したら true を返す
	    }
	}
}
