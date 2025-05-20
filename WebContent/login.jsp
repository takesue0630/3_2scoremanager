<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .login-container {
        width: 300px;
        margin: 50px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        text-align: center;
    }

    .error-message { /* エラーメッセージのスタイル */
        color: red;
        margin-bottom: 10px;
    }

    h2 {
        margin-bottom: 20px;
    }

    input[type="text"],
    input[type="password"] {
        width: calc(100% - 12px);
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 3px;
        box-sizing: border-box;
    }

    input[type="checkbox"] {
        margin-right: 5px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        width: 100%;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>

<div class="login-container">
    <form action="<%= request.getContextPath() %>/LoginExecute.action" method="post">

        <h2>ログイン</h2>

        <%-- エラーメッセージの表示 --%>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <input type="text" name="id" placeholder="半角でご入力ください" value="${param.id}" required>

        <input type="password" name="password" placeholder="30文字以内の半角英数字でご入力ください" required>
        <label>
            <input type="checkbox" id="showPassword">パスワードを表示
        </label>

        <input type="submit" value="ログイン">

    </form>
</div>

<script>
    const passwordInput = document.querySelector('input[name="password"]');
    const showPasswordCheckbox = document.getElementById('showPassword');

    showPasswordCheckbox.addEventListener('change', function() {
        passwordInput.type = this.checked ? 'text' : 'password';
    });
</script>

<%@include file="footer.jsp" %>