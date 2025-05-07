<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>


<div class="main">

<h2>科目情報削除</h2>

<p>「Javaプログラミング基礎（F02)」を削除してもよろしいですか。</p>
<input type="submit" style="color: red;/*文字の色を白*/ background-color: blue; /*背景の色を青*/
    border-radius: 3px 3px 3px 3px;  /* 角を丸くする */" value="削除">
<a href="menu.jsp">戻る</a>
<input type="hidden" name="subject_cd" value="${subject_cd}">
<input type="hidden" name="subject_name" value="${subject_name}">
</div>
<%@include file="../footer.jsp" %>