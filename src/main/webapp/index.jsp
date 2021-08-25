<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="store.HbrCategoryStore" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="index.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>To Do List</title>
</head>

<body>

<%
    HttpSession hs = request.getSession();
    User user = (User) hs.getAttribute("user");
    List<Category> categories = HbrCategoryStore
            .instOf().findAll();
%>

<div class="container pt-3">
    <ul class="nav">

        <% if (user == null) { %>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Зарегистрироваться</a>
        </li>
        <% } else { %>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">  <%=user.getLogin()%> | Выйти</a>
        </li>
        <% } %>
    </ul>

        <% if (user != null) { %>
    <div class="container pt-3">
        <form>
            <div class="form-group ">
                <label for="description">Добавить задачу </label>
                <div class="col-sm-5">
                    <textarea rows="5" cols="45" name="description" id="description" placeholder="Введите описание задачи"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-form-label col-sm-3" for="itemCats" style="font-weight: 900">Выберете категорию</label>
                <div class="col-sm-5">
                    <select class="form-control" name="itemCats" id="itemCats" multiple>
                        <% for (Category cat : categories) { %>
                            <option value='<%=cat.getId()%>'><%=cat.getName()%></option>

                        <% } %>

                   <%--    <c:forEach items="${allCategories}" var="category">
                           <option value='<c:out value="${category.id}"/>'><c:out value="${category.name}"/></option>
                       </c:forEach>--%>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-primary" onclick="sendData(<%=user.getId()%>)">Submit</button>
        </form>
    </div>
        <% } %>


<div class="container pt-3">
    <div class="form-group form-check">
        <input type="checkbox" id="checkbox"> Показать все задачи</input>
    </div>

  <table id="table" class="table table-bordered" style="width:100%">
      <thead>
      <tr>
          <th>ID</th>
          <th>Description</th>
          <th>Created</th>
          <th>Status</th>
          <th>Action</th>
      </tr>
    </thead>


  </table>
</div>

<script>

    <% if (user != null) { %>
    setInterval(() => {
        checkCheckbox(<%=user.getId()%>);
    }, 1000)
    <% } %>
</script>

</body>
</html>