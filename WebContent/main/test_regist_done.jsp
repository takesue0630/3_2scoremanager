<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>
<style>
  .center-message {
  display: flex;
  justify-content: center;
  align-items: center;
  /*height: 5vh;  画面の高さに合わせる */
  font-size: 15px;
  }
        .message-box {
            background-color: #d4edda;
            padding: 8px 340px; /* 最小限の余白 */
            border-radius: 6px;
            text-align: center;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
</style>

<div class = "main">
  <h2 style="background-color: #DCDCDC; padding:10px;">成績管理</h2>
    <div class="center-message">
    <div class="message-box">
      登録が完了しました。<br>
    </div>
    </div>
    <a href="StudentList.action">戻る</a>　　　
    <a href="TestList.action">成績参照</a>
</div>

<%@include file="../footer.jsp" %>