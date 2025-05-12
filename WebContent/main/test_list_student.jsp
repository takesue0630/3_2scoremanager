<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">成績一覧（学生）</h2>

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
			<input type="text" value="${ no }" required name="f4" placeholder="学生番号を入力してください" style="width:100%; height:25px;">
		</div>

		<div style="text-align:center; width:15%; margin:auto;">
			<input type="submit" value="検索">
		</div>
	</form>
</div>

<c:choose>
	<c:when test="${ list.size()>0 }">
		<div>氏名：${ student.name }(${ student.no })</div>

		<table style="width:100%;">
			<thead>
				<th style="text-align:left; width:50%;">科目名</th>
				<th style="text-align:left; width:20%;">科目コード</th>
				<th style="text-align:left; width:15%;">回数</th>
				<th style="text-align:left; width:15%;">点数</th>
			</thead>

			<tbody>
				<c:forEach var="testliststudent" items="${list}">
					<tr>
						<td>${ testliststudent.subjectName }</td>
						<td>${ testliststudent.subjectCd }</td>
						<td>${ testliststudent.num }</td>
						<td>${ testliststudent.point }</td>
					<tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div>成績情報が存在しませんでした</div>
	</c:otherwise>
</c:choose>

<%@include file="../footer.jsp" %>