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

	public boolean save(List<String> list) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st = null;  // stを最初にnullで初期化
	    boolean result = true;
	    int listSize = list.size() / 4;

	    // リストの内容を表示
	    System.out.println("リストのサイズ: " + listSize);
	    for (String item : list) {
	        System.out.println(item);
	    }

	    try {
	        for (int i = 0; i < listSize; i++) {
	            // リストの要素を1つずつ変数に代入
	            String studentNo = list.get(i * 4); // 1つ目の値
	            String pointStr = list.get(i * 4 + 1); // 2つ目の値
	            String subjectName = list.get(i * 4 + 2); // 3つ目の値
	            String num = list.get(i * 4 + 3); // 4つ目の値

	            // デバッグ出力
	            System.out.println("学生番号: " + studentNo);
	            System.out.println("科目名: " + subjectName);
	            System.out.println("点数: " + pointStr);
	            System.out.println("回数: " + num);

	            // SQL文の修正　(入学年,クラス,番号学生番号,氏名にあう生徒の点数を上書きする)
	            st = con.prepareStatement(
	                "UPDATE test " +
	                "SET point = ? " +
	                "WHERE student_no = ? " +
	                "AND no = ? " +
	                "AND point = 100 " +//ここの点数をSQLの点数を持ってくる
	                "AND EXISTS ( " +
	                "SELECT 1 " +
	                "FROM subject " +
	                "WHERE subject.name = ? " +
	                "AND cd = test.subject_cd )"
	            );

	            // パラメータの設定
	            st.setInt(1, Integer.parseInt(pointStr));  // 点数を設定
	            st.setString(2, studentNo);  // 学生番号を設定
	            st.setString(3, num);  // 回数を設定
	            st.setString(4, subjectName);  // 科目名を設定

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

	public boolean save1(List<String> list) throws Exception {
	    // データベース接続
	    Connection con = getConnection();
	    PreparedStatement st = null;  // stを最初にnullで初期化
	    boolean result = true;
	    int listSize = list.size() / 4;

	    // リストの内容を表示
	    System.out.println("リストのサイズ: " + listSize);
	    for (String item : list) {
	        System.out.println(item);
	    }

	    try {
	        for (int i = 0; i < listSize; i++) {
	            // リストの要素を1つずつ変数に代入
	            String studentNo = list.get(i * 4); // 1つ目の値
	            String pointStr = list.get(i * 4 + 1); // 2つ目の値
	            String subjectName = list.get(i * 4 + 2); // 3つ目の値
	            String num = list.get(i * 4 + 3); // 4つ目の値
	            String subject = list.get(listSize);

	            // デバッグ出力
	            System.out.println("学生番号: " + studentNo);
	            System.out.println("科目名: " + subjectName);
	            System.out.println("点数: " + pointStr);
	            System.out.println("回数: " + num);

	            // SQL文の修正　(入学年,クラス,番号学生番号,氏名にあう生徒の点数を上書きする)
	            st = con.prepareStatement(
	                "UPDATE test " +
	                "SET point = ? " +
	                "WHERE student_no = ? "
	            );

	            // パラメータの設定
	            st.setInt(1, Integer.parseInt(pointStr));  // 点数を設定
	            st.setString(2, studentNo);  // 学生番号を設定

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

	public boolean save(Test test,Connection connection) throws Exception{
		return pass;
	}
}
