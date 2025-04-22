<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div style="width:100%;">

<h2 style="background-color: #DCDCDC;">学生 管理</h2>

<a href="StudentCreate.action" style="text-align: right;">新規登録</a>

<form action="" method="get">

<label>入学年度</label>
<select></select>

<label>クラス</label>
<select></select>

<input type="checkbox">在学中

<input type="submit" value="絞込み">

</form>
<c:choose >
	<c:when test="${ size>0 }">
		<div>検索結果${ size }件</div>

		<table>
		<thead>
		<th>入学年度</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>クラス</th>
		<th>在学中</th>
		</thead>

		<c:forEach var="student" items="${list}">
			<tr>
			<td>${ student.entYear }</td>
			<td>${ student.no }</td>
			<td>${ student.name }</td>
			<td>${ student.classNum }</td>
			<td class="text-center">
				<c:choose>
					<c:when test="${ student.isAttend }">〇</c:when>
					<c:otherwise>×</c:otherwise>
				</c:choose>
			</td>
			<td><a href="StudentUpdate.action?no=${ student.no }&&">変更</a></td>
			<tr>
		</c:forEach>

		</table>
	</c:when>
	<c:otherwise>
		<div>学生情報が存在しませんでした</div>
	</c:otherwise>
</c:choose>

</div>


<%@include file="../footer.jsp" %>