package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO{
	public ClassNum get(String class_num, School school) throws Exception{

		ClassNum cn = new ClassNum();
		cn.setClass_num(class_num);
		cn.setSchool(school);

		return cn;
	}

	public List<String> filter(School school) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from class_num where school_cd=?"
		);
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();

		List<String> list=new ArrayList<>();

		while (rs.next()) {
			list.add(rs.getString("class_num"));
		}

		st.close();
		con.close();

		return list;
	}

	public boolean save(ClassNum classNum) throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into class_num values (?,?)"
		);
		st.setString(1, classNum.getSchool().getCd());
		st.setString(2, classNum.getClass_num());

		boolean result;

		if (st.executeUpdate()>0) {
			result=true;
		} else {
			result=false;
		}

		st.close();
		con.close();

		return result;
	}

//	save実装
}
