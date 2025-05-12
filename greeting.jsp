<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        body {
            font-family: 'メイリオ', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ffffff;
        }

        header {
            background: linear-gradient(to bottom, #e6f0fa, #ffffff);
            height: 50px;
            line-height: 50px;
            padding: 0 20px;
            font-size: 20px;
            font-weight: bold;
            border-bottom: 1px solid #ccc;
            position: relative;
        }

        .user-info {
            position: absolute;
            right: 20px;
            top: 15px;
            font-size: 12px;
        }

        .user-info a {
            margin-left: 10px;
            text-decoration: none;
            color: blue;
        }

        nav {
            float: left;
            width: 180px;
            background-color: #f5f5f5;
            padding: 20px 10px;
            height: calc(100vh - 50px);
            border-right: 1px solid #ddd;
            box-sizing: border-box;
        }

		nav a, nav strong {
    		display: block;
    		color: #0000cc;
    		margin-bottom: 10px;
    		text-decoration: none;
    		font-size: 13px;
		}


        nav strong {
            margin-top: 20px;
        }

        main {
            margin-left: 200px;
            padding: 20px;
        }

        h2 {
            font-size: 16px;
            border-bottom: 2px solid #ccc;
            padding-bottom: 5px;
            margin-bottom: 20px;
        }

        .menu-section {
            display: flex;
            gap: 20px;
        }

        .card {
            width: 220px;
            min-height: 120px;
            border-radius: 8px;
            padding: 15px;
            box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
        }

        .student-card {
            background-color: #eddcdc;
        }

        .grade-card {
            background-color: #d9ebd8;
        }

        .subject-card {
            background-color: #d5e3f0;
        }

        .card a {
            display: block;
            margin: 8px 0;
            color: #0000cc;
            text-decoration: none;
            font-size: 14px;
        }

        .card div {
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <header>
        得点管理システム
        <div class="user-info">
            <%
                String username = (String) session.getAttribute("username");
                if (username == null) {
            %>
                ゲストさん
                <a href="login.jsp">ログイン</a>
            <%
                } else {
            %>
                <%= username %>さん
                <a href="logout.jsp">ログアウト</a>
            <%
                }
            %>
        </div>
    </header>

    <!-- サイドメニュー -->
    <nav>
    	<!-- URL -->
        <a href="#">メニュー</a>
        <a href="#">学生管理</a>
        <strong>成績管理</strong>
        <a href="#">成績登録</a>
        <a href="#">成績参照</a>
        <a href="#">科目管理</a>
    </nav>

    <!-- メインメニュー -->
    <main>
        <h2>メニュー</h2>
        <div class="menu-section">
            <div class="card student-card">
                <a href="#">学生管理</a>
            </div>
            <div class="card grade-card">
                <div>成績管理</div>
                <a href="#">成績登録</a>
                <a href="#">成績参照</a>
            </div>
            <div class="card subject-card">
                <a href="#">科目管理</a>
            </div>
        </div>
    </main>
</body>
</html>
