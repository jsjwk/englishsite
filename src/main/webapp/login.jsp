<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
<h1>用户登录页面</h1>

<form action="/account.do">

<table>
<tr><td>用户名</td> <td><input type="text" name="passport"/> </td>  </tr>
<tr><td>密码</td> <td><input type="text" name="password"/> </td>  </tr>
</table>
<button type="submit">登录</button>
</form>

<a href="/preReg.do"><h2>去注册页面</h2></a>
</body>
</html>