<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">成績参照</h2>

<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px;">
	<form action="TestListSubjectExecute.action" method="get" style="display:flex;">
		<div style="text-align:center; width:15%; padding:10px;">
			<p>科目情報</p>
		</div>

		<div style="width:15%; padding:10px;">
			<label>入学年度</label>
			<br>
			<select name="f1" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ ent_year_set }">
					<option value="${ year }">${ year }</option>
				</c:forEach>
			</select>
		</div>

		<div style="width:15%; padding:10px;">
			<label>クラス</label>
			<br>
			<select name="f2" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="num" items="${ class_num_set }">
					<option value="${ num }">${ num }</option>
				</c:forEach>
			</select>
		</div>

		<div style="width:30%; padding:10px;">
			<label>科目</label>
			<br>
			<select name="f3" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="subject" items="${ subject_set }">
					<option value="${ subject.cd }">${ subject.name }</option>
				</c:forEach>
			</select>
		</div>

		<div style="text-align:center; width:15%; margin:auto;">
			<input type="submit" value="検索">
		</div>
	</form>

	<div style="color:#ffd700; margin-left:40px;">${ error }</div>

	<div style="margin:10px; border-bottom:2px solid #DCDCDC;"></div>

	<form action="TestListStudentExecute.action" method="get" style="display:flex;">
		<div style="text-align:center; width:15%; padding:10px;">
			<p>学生情報</p>
		</div>

		<div style="width:30%; padding:10px;">
			<label>学生番号</label>
			<br>
			<input type="text" required name="f4" placeholder="学生番号を入力してください" style="width:100%; height:25px;">
		</div>

		<div style="text-align:center; width:15%; margin:auto;">
			<input type="submit" value="検索">
		</div>
	</form>
</div>

<p style="color:#00bfff;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

<%@include file="../footer.jsp" %>