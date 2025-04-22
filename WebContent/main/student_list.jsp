<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div style="width:100%;">

<h2 style="background-color: #DCDCDC;">学生管理</h2>

<p style="text-align: right;"><a href="StudentCreate.action">新規登録</a></p>

<form action="" method="get">

<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px; display:flex;">

<div style="width:30%; padding:10px;">
<label>入学年度</label>
<br>
<select name="ent_year" style="width:100%; height:30px;">
	<option value="0">--------</option>
</select>
</div>

<div style="width:30%; padding:10px;">
<label>クラス</label>
<br>
<select name="class_num" style="width:100%; height:30px;">
	<option value="0">--------</option>
</select>
</div>

<div style="text-align:center; width:20%; margin:auto;">
<input type="checkbox">在学中
</div>

<div style="text-align:center; width:20%; margin:auto;">
<input type="submit" value="絞込み">
</div>

</div>

</form>

<c:choose >
	<c:when test="${ size>0 }">
		<div>検索結果${ size }件</div>

		<table style="width:100%;">
		<thead>
		<th style="text-align:left; width:20%;">入学年度</th>
		<th style="text-align:left; width:20%;">学生番号</th>
		<th style="text-align:left; width:25%;">氏名</th>
		<th style="width:15%;">クラス</th>
		<th style="width:10%;">在学中</th>
		</thead>


		<c:forEach var="student" items="${list}">
			<tr>
				<td style="border-top:2px solid #DCDCDC;">${ student.entYear }</td>
				<td style="border-top:2px solid #DCDCDC;">${ student.no }</td>
				<td style="border-top:2px solid #DCDCDC;">${ student.name }</td>
				<td style="text-align:center; border-top:2px solid #DCDCDC;">${ student.classNum }</td>
				<td style="text-align:center; border-top:2px solid #DCDCDC;">
					<c:choose>
						<c:when test="${ student.isAttend }">〇</c:when>
						<c:otherwise>×</c:otherwise>
					</c:choose>
				</td>
				<td style="border-top:2px solid #DCDCDC;"><a href="StudentUpdate.action?no=${ student.no }&&">変更</a></td>
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