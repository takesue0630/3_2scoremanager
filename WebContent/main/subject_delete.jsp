<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>


<div class="main">

<h2>科目情報削除</h2>

<p>「${subject_name}(${subject_cd})」を削除してもよろしいですか</p>
<form action=SubjectDeleteExecute.action method="get">
<input type="submit" style="color: red;/*文字の色を白*/ background-color: blue; /*背景の色を青*/
    border-radius: 3px 3px 3px 3px;  /*角を丸くする*/" value="削除">

<input type="hidden" name="subject_cd" value="${subject_cd}">
<input type="hidden" name="subject_name" value="${subject_name}">
 </form>

    <br>
    <br>
    <br>
<a href="SubjectList.action">戻る</a>


</div>
<%@include file="../footer.jsp" %>
