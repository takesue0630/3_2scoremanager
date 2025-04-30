<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div class="main">

<h2 style="background-color: #DCDCDC;">科目管理</h2>

<p style="text-align: right;"><a href="SubjectCreate.action">新規登録</a></p>

<table style="width:100%;">
<thead>
	<th style="text-align:left; width:20%;">科目コード</th>
	<th style="text-align:left; width:50%;">科目名</th>
	<th style="width:15%;"></th>
	<th style="width:15%;"></th>
</thead>

<tbody>
<c:forEach var="subject" items="${list}">
	<tr>
		<td>${ subject.cd }</td>
		<td>${ subject.name }</td>
		<td style="text-align:center;"><a href="SubjectUpdate.action?no=${ subject.cd }&&">変更</a></td>
		<td style="text-align:center;"><a href="SubjectDelete.action?no=${ subject.cd }&&">削除</a></td>
	<tr>
</c:forEach>
</tbody>

</table>

</div>


<%@include file="../footer.jsp" %>