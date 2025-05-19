<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">科目情報変更</h2>

<form action="SubjectUpdateExecute.action" method="get" class="insert">
	<div style="margin-top:10px; margin-bottom:10px;">
		<label>科目コード</label>
		<input class="boader_none" type="text" name="cd" value="${ cd }" readonly style="border:none; outline:none; width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
	</div>

	<div style="color:#ffd700;">${ error }</div>

	<div style="margin-top:10px; margin-bottom:10px;">
		<label>科目名</label>
		<input type="text" name="name" maxlength="30" value="${ name }" placeholder="氏名を入力してください" required  style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
	</div>

	<input type="submit" value="変更">
</form>

<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>