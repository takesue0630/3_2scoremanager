package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
public class SubjectDao extends DAO{
	public Subject get(String cd,School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st=con.prepareStatement(
				"SELECT * FROM subject WHERE CD = ? AND SCHOOL_CD = ?"
				);
		st.setString(1, cd);
		st.setString(2, school.getCd());
		ResultSet rs=st.executeQuery();

		Subject s=new Subject();
		if (rs.next()) {
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setSchool(school);
		}

		st.close();
		con.close();

		return s;
	}

	//まだ未完成
	public List<Subject> filter1(School school)throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		st = con.prepareStatement(
			"SELECT * FROM SUBJECT  where school_cd = ?"
		);
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();

		System.out.println(rs+"--------");

		List<Subject> list=new ArrayList<Subject>();
		while (rs.next()) {
			Subject s=new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setSchool(school);
			list.add(s);
		}

		st.close();
		con.close();

		return list;
	}

	public List<Subject> filter(School school) throws Exception {
	    List<Subject> list = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        con = getConnection();
	        st = con.prepareStatement(
	            "SELECT * FROM SUBJECT WHERE school_cd = ?"
	        );
	        st.setString(1, school.getCd());
	        rs = st.executeQuery();

	        while (rs.next()) {
	            Subject subject = new Subject();
	            subject.setCd(rs.getString("cd"));
	            subject.setName(rs.getString("name"));
	            subject.setSchool(school); // ← typo 修正
	            list.add(subject);
	        }

	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }

	    return list;
	}
/*
	public List<Subject> get(School school) throws Exception {
	    List<Subject> list = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        con = getConnection();
	        st = con.prepareStatement(
	            "select cd from subject where school_cd = ?  and cd = ? "
	        );
	        st.setString(1, school.getCd());
	        rs = st.executeQuery();

	        while (rs.next()) {
	            Subject subject = new Subject();
	            subject.setCd(rs.getString("cd"));
	            subject.setName(rs.getString("name"));
	            subject.setSchool(school); // ← typo 修正
	            list.add(subject);
	        }

	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }

	    return list;
	}
	*/



	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();
		PreparedStatement st;
		boolean result = true;

		st=con.prepareStatement(
				"UPDATE subject SET cd = ?, name = ? WHERE cd = ? and school_cd = ?"
				);

	    st.setString(1, subject.getCd());       // 新しいcd
	    st.setString(2, subject.getName());     // 新しいname
	    st.setString(3, subject.getCd());
	    st.setString(4, subject.getSchool().getCd());

	    return result;

	}

	public boolean save1(Subject subject) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st;
	    boolean result = false;  // ← 実行成功したかどうかを反映

	    st = con.prepareStatement(
	        "INSERT INTO subject (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)"
	    );

	    st.setString(1, subject.getSchool().getCd());
	    st.setString(2, subject.getCd());
	    st.setString(3, subject.getName());

	    System.out.println("Daoの開始");
	    System.out.println("学校コード: " + subject.getSchool().getCd());
	    System.out.println("科目コード: " + subject.getCd());
	    System.out.println("科目名: " + subject.getName());

	    int count = st.executeUpdate();  // ← SQL実行

	    if (count > 0) {
	        result = true;  // 1行以上追加されたら成功
	    }

	    st.close();
	    con.close();

	    return result;
	}


    public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();
		PreparedStatement st;
		boolean result = true;

		st=con.prepareStatement(
				"DELETE FROM subject WHERE CD = ? and NAME = ?"
			);
	        st.setString(1, subject.getCd());
	        st.setString(2, subject.getName());

			st.close();
			con.close();

	        return result;
	 }
}