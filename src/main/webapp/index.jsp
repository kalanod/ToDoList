<%@ page import="com.calanco.todolist.adapters.DataAdapter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.calanco.todolist.model.ListItem" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.calanco.todolist.adapters.UserAdapter" %>
<%@ page import="com.calanco.todolist.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>lists</title>
    <style>
        <jsp:include page="css/index.css"/>
    </style>
    <title>ToDoList</title>
</head>
<body>
<%DataAdapter adapter = new DataAdapter(request.getContextPath(), -1);%>
<!-- Modal -->
<h1 id="tmp"></h1>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Добавить элемент</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Название</label>
                </div>
                <input type="date" id="start" name="trip-start" value="1018-07-22" min="918-01-01" max="1645-12-31"/>
                <select class="form-select" aria-label="Default select example">
                    <option value="0" selected>Выбор категории</option>
                    <option value="1">красный</option>
                    <option value="2">невинный</option>
                    <option value="3">таинственный</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="doAdd btn btn-primary">Добавить</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="shareModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="shareModalLabel">Добавить элемент</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="sharefloatingInput" placeholder="name@example.com">
                    <label for="sharefloatingInput">ID пользователя</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="doShare close btn btn-primary">Добавить</button>
            </div>
        </div>
    </div>
</div>
<% UserAdapter userAdapter = new UserAdapter();
    if (userAdapter.isAuthorized((User) request.getSession().getAttribute("User"))) {
        adapter = new DataAdapter(request.getContextPath(), ((User)request.getSession().getAttribute("User")).getId());%>

<div class="header">
    <h3 style="horiz-align: left; display: inline-block">Сортировка</h3>
    <%if (request.getParameter("sort") != null && request.getParameter("sort").equals("title")) {%>
    <a href="<%=request.getContextPath()%>?sort=time" id="srtBtn" class="btn btn-primary"
       style="horiz-align: right; display: inline-block">По добавлению</a>
    <%} else { %>
    <a href="<%=request.getContextPath()%>?sort=title" id="srtBtn" class="btn btn-primary"
       style="horiz-align: right; display: inline-block">По названию</a>
    <%}%>
    <h3 style="horiz-align: left; display: inline-block">Ваш ID: <%=((User) request.getSession().getAttribute("User")).getId()%>
    </h3>
</div>
<div style="margin-left: auto;
    margin-right: auto; display: block">
    <button id="root" type="button" class="addBase btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#exampleModal">
        добавить список
    </button>
    <div class="data">
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <%
                ListItem list = adapter.getLists();
                if (request.getParameter("sort") != null && request.getParameter("sort").equals("title")) {
                    list.getArrayList().sort(Comparator.comparing(ListItem::getTitle));
                }
                for (int i = 0; i < list.getArrayList().size(); i++) {
            %>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <%
                        String color;
                        switch (list.getArrayList().get(i).getType()) {
                            case 1:
                                color = "red";
                                break;
                            case 2:
                                color = "coral";
                                break;
                            case 3:
                                color = "rebeccapurple";
                                break;
                            default:
                                color = "white";
                        }
                    %>

                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#akk<%=i%>" aria-expanded="true"
                            aria-controls="akk<%=i%>" style="background-color: <%=color%>">
                        <%=((ListItem) list.getArrayList().get(i)).getTitle()%>
                    </button>
                </h2>
                <div id="akk<%=i%>" class="accordion-collapse collapse show">
                    <button id="_<%=i%>" type="button" class="shareBtn btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#shareModal">
                        поделиться
                    </button>
                    <div class="accordion-body">
                        <%=adapter.print(list.getArrayList().get(i))%>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
    <div class="time">
        <%
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            for (int i = 0; i < list.getArrayList().size(); i++) {%>
        <div class="card">
            <div class="card-body">
                <%=list.getArrayList().get(i).getTitle() + " \nвыполнить до " + formatter.format(list.getArrayList().get(i).getDate().getTime())%>
            </div>
        </div>
        <%}%>
    </div>
</div>
<%} else {%>

<form class="row g-3" action="${pageContext.request.contextPath}/login" method="post">
    <div class="col-auto">
        <input type="text" class="form-control" name="username" id="usenameInput" placeholder="">
        <label for="usenameInput">Username</label>
    </div>
    <div class="col-auto">
        <input type="text" class="form-control" name="password" id="passwordInput" placeholder="">
        <label for="passwordInput">password</label>
    </div>
    <div class="col-auto">
        <button type="submit" class="login close btn btn-primary">Добавить</button>
    </div>
</form>
<%}%>
<script src="js/jquery-3.7.1.min.js"></script>
<script>
    <jsp:include page="js/index.js"/>
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>