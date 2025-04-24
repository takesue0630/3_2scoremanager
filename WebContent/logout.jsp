<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログアウト</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f4f7f6;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }

        .header {
            background-color: #e9ecef;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .header h1 {
            color: #333;
            margin: 0;
            text-align: center;
        }

        .logout-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .message-box {
            background-color: #d4edda;
            color: #155724;
            padding: 15px;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .login-link {
            color: #007bff;
            text-decoration: none;
        }

        .login-link:hover {
            text-decoration: underline;
        }

        .copyright {
            margin-top: 20px;
            color: #777;
            font-size: 0.9em;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>得点管理システム</h1>
    </div>
    <div class="logout-container">
        <h2>ログアウト</h2>
        <div class="message-box">
            ログアウトしました。
        </div>
        <p><a class="login-link" href="<%= request.getContextPath() %>/login.jsp">ログイン画面へ</a></p>
    </div>
    <div class="copyright">
        © 2023 TIC 大原学園
    </div>
</body>
</html>