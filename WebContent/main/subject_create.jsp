<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>
<div style="background-color: lightgray;">
<h2> 科目情報登録</h2>
</div>


<form action="SubjectCreateExecute.action" method="get">
<label>科目コード</label>
<br>
<input type="text" name="cd" value="${cd}"  maxlength="3"  placeholder="科目コードを入力してください" required>
<br>
<label>科目名</label>
<br>
<input type="text" name="cd" value="${cd}" maxlength="20"  placeholder="科目名を入力してください"  required>
<br>

    <button type="submit"
    style="color: white;/*文字の色を白*/ background-color: blue; /*背景の色を青*/
    border-radius: 3px 3px 3px 3px;  /* 角を丸くする */">登録</button>
</form>
<a href="subject_list.jsp">戻る</a>

<%@include file="../footer.jsp" %>