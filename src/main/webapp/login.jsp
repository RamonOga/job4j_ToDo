<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src ="login.js"></script>

    <title>To Do List</title>
</head>
<body>
<%
    HttpSession hs = request.getSession();
    User user = (User) hs.getAttribute("user");
%>

<div class="container pt-3">
    <ul class="nav">

        <% if (user == null) { %>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
        </li>
        <% } else { %>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">  <%=user.getLogin()%> | Выйти</a>
        </li>
        <% } %>
    </ul>
</div>

<div class="container pt-3">

    <div class="container pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Авторизация
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/login.do" method="post" req>
                    <div class="form-group">
                        <label for="login">Имя пользователя</label>
                        <input type="text" class="form-control" id="login" name="login" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="text" class="form-control" id="password" name="password" required>
                    </div>

                    <button type="submit" class="btn btn-primary" onclick="validate()">Войти</button>

                    <c:if test="${not empty error}">
                        <div style="color:red; font-weight: bold; margin: 30px 0;">
                            Не верный логин и/или пароль.
                        </div>
                    </c:if>
                    <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Зарегистрироваться</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
</body>
</html>
