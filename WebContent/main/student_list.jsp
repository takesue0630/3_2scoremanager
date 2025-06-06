<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">学生管理</h2>

<p style="text-align: right;"><a href="StudentCreate.action">新規登録</a></p>

<c:if test="${ not empty errorMsg }">
	<div style="color:red; padding:10px;">${ errorMsg }</div>
</c:if>

<form action="StudentList.action" method="get">
	<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px; display:flex;">
		<div style="width:30%; padding:10px;">
			<label>入学年度</label>
			<br>
			<select name="f1" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ ent_year_set }">
					<option value="${ year }" <c:if test="${ year==f1 }">selected</c:if>>${ year }</option>
				</c:forEach>
			</select>
		</div>

		<div style="width:30%; padding:10px;">
			<label>クラス</label>
			<br>
			<select name="f2" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="num" items="${ class_num_set }">
					<option value="${ num }" <c:if test="${ num==f2 }">selected</c:if>>${ num }</option>
				</c:forEach>
			</select>
		</div>

		<div style="text-align:center; width:20%; margin:auto;">
			<input type="checkbox" name="f3">
			<label>在学中</label>
		</div>

		<div style="text-align:center; width:20%; margin:auto;">
			<input type="submit" value="絞込み">
		</div>
	</div>
</form>

<c:choose >
	<c:when test="${ list.size()>0 }">
		<div>検索結果${ list.size() }件</div>

		<table style="width:100%;">
			<thead>
				<th style="text-align:left; width:20%;">入学年度</th>
				<th style="text-align:left; width:20%;">学生番号</th>
				<th style="text-align:left; width:25%;">氏名</th>
				<th style="width:15%;">クラス</th>
				<th style="width:10%;">在学中</th>
			</thead>

			<tbody>
			<c:forEach var="student" items="${list}">
				<tr>
					<td style="border-top:2px solid #DCDCDC; padding:5px;">${ student.entYear }</td>
					<td style="border-top:2px solid #DCDCDC; padding:5px;">${ student.no }</td>
					<td style="border-top:2px solid #DCDCDC; padding:5px;">${ student.name }</td>
					<td style="border-top:2px solid #DCDCDC; padding:5px; text-align:center;">${ student.classNum }</td>
					<td style="border-top:2px solid #DCDCDC; padding:5px; text-align:center;">
						<c:choose>
							<c:when test="${ student.isAttend }">〇</c:when>
							<c:otherwise>×</c:otherwise>
						</c:choose>
					</td>
					<td style="border-top:2px solid #DCDCDC; padding:5px; text-align:center;"><a href="StudentUpdate.action?no=${ student.no }&&">変更</a></td>
				<tr>
			</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div>学生情報が存在しませんでした</div>
	</c:otherwise>
</c:choose>

<%@include file="../footer.jsp" %>