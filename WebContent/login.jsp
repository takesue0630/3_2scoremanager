<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .login-container {
        max-width: 320px;
        margin: 60px auto;
        padding: 25px 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        text-align: center;
        background-color: #f9f9f9;
    }

    .error-message {
        color: red;
        margin-bottom: 15px;
        font-size: 0.95em;
    }

    h2 {
        margin-bottom: 25px;
        font-size: 1.5em;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 1em;
    }

    .checkbox-container {
        margin-bottom: 15px;
        font-size: 0.95em;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
        font-size: 1em;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>

<div class="login-container">
    <form action="<%= request.getContextPath() %>/LoginExecute.action" method="post">
        <h2>ログイン</h2>

        <!-- エラーメッセージ -->
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <!-- ユーザーID入力 -->
        <label for="id">ユーザーID</label>
        <input type="text" id="id" name="id" placeholder="半角でご入力ください" value="${param.id}" required>

        <!-- パスワード入力 -->
        <label for="password">パスワード</label>
        <input type="password" id="password" name="password" placeholder="30文字以内の半角英数字でご入力ください" required>

        <!-- パスワード表示切替 -->
        <div class="checkbox-container">
            <input type="checkbox" id="showPassword">
            <label for="showPassword">パスワードを表示</label>
        </div>

        <!-- ログインボタン -->
        <input type="submit" value="ログイン">
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const passwordInput = document.getElementById('password');
        const showPasswordCheckbox = document.getElementById('showPassword');

        if (passwordInput && showPasswordCheckbox) {
            showPasswordCheckbox.addEventListener('change', function () {
                passwordInput.type = this.checked ? 'text' : 'password';
            });
        }
    });
</script>

<%@ include file="footer.jsp" %>
