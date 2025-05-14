<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="main.Kamoku" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目情報変更</title>
    <style>
        body {
            font-family: "Meiryo", "Yu Gothic", sans-serif;
            font-size: 14px;
            margin: 0;
            padding: 0;
            background-color: #fdfdfd;
        }

        header {
            background-color: #e3f2fd;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .main-title {
            font-size: 24px;
            font-weight: bold;
        }

        .login-info a {
            font-size: 12px;
            color: #000;
            text-decoration: none;
        }

        .container {
            display: flex;
            min-height: 600px;
        }

        .sidebar {
            width: 160px;
            background-color: #f5f5f5;
            padding: 15px;
            box-sizing: border-box;
        }

        .sidebar a {
            display: block;
            margin-bottom: 12px;
            color: #007bff;
            text-decoration: none;
        }

        .sidebar a:hover {
            text-decoration: underline;
        }

        .content {
            flex: 1;
            padding: 30px 40px;
        }

        .section-title {
            font-size: 18px;
            font-weight: bold;
            background-color: #f0f0f0;
            padding: 10px;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: inline-block;
            width: 100px;
        }

        input[type="text"] {
            width: 350px;
            padding: 6px;
            font-size: 14px;
        }

        .btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 7px 15px;
            font-size: 14px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .error-msg {
            color: red;
            margin-bottom: 20px;
        }

        .footer {
            text-align: center;
            font-size: 12px;
            color: #888;
            padding: 20px;
            border-top: 1px solid #ccc;
        }
    </style>
</head>
<body>

<header>
    <div class="main-title">得点管理システム</div>
    <div class="login-info"><a href="#">ログアウト</a></div>
</header>

<div class="container">
    <div class="sidebar">
        <a href="#">メニュー</a>
        <a href="#">成績管理</a>
        <a href="#">成績登録</a>
        <a href="#">成績参照</a>
        <a href="#">科目編集</a>
    </div>

    <div class="content">
    <%
    String success = request.getParameter("success");
    if ("true".equals(success)) {
%>
    <div style="background-color: #c8e6c9; color: #256029; padding: 10px; margin-bottom: 20px;">
        変更が完了しました
    </div>
<%
    }
%>

        <div class="section-title">科目情報変更</div>

        <%
            List<Kamoku> kamokuList = (List<Kamoku>) session.getAttribute("kamokuList");
            if (kamokuList == null) {
                kamokuList = new ArrayList<Kamoku>();
                kamokuList.add(new Kamoku("F02", "Javaプログラミング基礎"));
                session.setAttribute("kamokuList", kamokuList);
            }

            String defaultCd = "F02";
            String defaultName = "";
            for (Kamoku k : kamokuList) {
                if (k.getCd().equals(defaultCd)) {
                    defaultName = k.getName();
                    break;
                }
            }

            String cd = request.getAttribute("cd") != null ? (String) request.getAttribute("cd") : defaultCd;
            String name = request.getAttribute("name") != null ? (String) request.getAttribute("name") : defaultName;
            String error = (String) request.getAttribute("error");
        %>

        <% if (error != null) { %>
            <div class="error-msg">※<%= error %></div>
        <% } %>

        <form action="KamokuUpdateServlet" method="post">
            <div class="form-group">
                <label class="form-label">科目コード</label>
                <span><%= cd %></span>
                <input type="hidden" name="cd" value="<%= cd %>">
            </div>

            <div class="form-group">
                <label class="form-label">科目名</label>
                <input type="text" name="name" value="<%= name %>" maxlength="20" required>
            </div>

            <div class="form-group">
                <input type="submit" class="btn" value="変更">
            </div>
        </form>

        <div class="form-group">
            <a href="KamokuListServlet">← 戻る</a>
        </div>
    </div>
</div>

<div class="footer">
    © 2023 TIC<br>
    大原学園
</div>

</body>
</html>
