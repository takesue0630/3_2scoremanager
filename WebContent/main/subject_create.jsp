<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 追加 -->
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div style="background-color: lightgray;">
<h2> 科目情報登録</h2>
</div>

<!-- エラーメッセージ表示（追加） -->
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="SubjectCreateExecute.action" method="get">
<label>科目コード</label><br>
<input type="text" name="cd1" value="${cd}" maxlength="3" placeholder="科目コードを入力してください" required>
<br>
<label>科目名</label><br>
<input type="text" name="name1" value="${name}" maxlength="20" placeholder="科目名を入力してください" required>
<br>

<button type="submit"
    style="color: white; background-color: blue; border-radius: 3px;">
    登録
</button>
</form>

<a href="subject_list.jsp">戻る</a>

<%@include file="../footer.jsp" %>
