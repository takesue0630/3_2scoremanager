<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 追加 -->
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div style="background-color: lightgray;">
<h2> 科目情報登録</h2>
</div>

<form action="SubjectCreateExecute.action" method="get">
<label>科目コード</label><br>
<input type="text" name="cd" value="${cd}" maxlength="3" placeholder="科目コードを入力してください" required><br>
<!-- エラーメッセージ -->
<c:if test="${not empty error}">
    <p style="color:#FFCC66;">${error}</p>
</c:if>
<label>科目名</label><br>
<input type="text" name="name" value="${name}" maxlength="20" placeholder="科目名を入力してください" required>
<br>

<button type="submit"
    style="color: white; background-color: blue; border-radius: 3px;">
    登録
</button>
</form>

<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>
