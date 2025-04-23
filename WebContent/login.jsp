<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="header.jsp" %>

<form action="main/Login.action" method="post">

<h2>ログインあ</h2>

<input type="text" name="id" placeholder="半角でご入力ください" required>

<input type="password" name="password" placeholder="30文字以内の半角英数字でご入力ください" required>

<p><input type="checkbox">パスワードを表示</p>

<input type="submit" value="ログイン">

</form>

<%@include file="footer.jsp" %>