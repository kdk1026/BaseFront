<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f8f9fa;
        color: #343a40;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        text-align: center;
    }
    h1 {
        font-size: 3em;
        margin-bottom: 0.5em;
    }
    p {
        font-size: 1.2em;
        margin-bottom: 1em;
    }
    .button-container {
        display: flex;
        justify-content: center;
        gap: 1em;
    }
    .button-container a {
        padding: 0.5em 1em;
        border: 1px solid #007bff;
        border-radius: 5px;
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }
    .button-container a:hover {
        background-color: #007bff;
        color: #fff;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p>${message}</p>
        <div class="button-container">
            <a href="javascript:history.back()">뒤로가기</a>
            <a href="/">홈페이지로 돌아가기</a>
        </div>
    </div>
</body>
</html>