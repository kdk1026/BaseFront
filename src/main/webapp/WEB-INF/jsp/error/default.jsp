<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Errpr Page</title>
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
    a {
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }
    a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="container">
        <p>${errors['message']}</p>
        <p><a href="/">홈페이지로 돌아가기</a></p>
    </div>
</body>
</html>