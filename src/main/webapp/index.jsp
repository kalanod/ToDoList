<%@ page import="com.calanco.todolist.adapters.DataAdapter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.calanco.todolist.model.ListItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Articles</title>
    <style>
        <jsp:include page="css/index.css"/>
    </style>
    <title>ToDoList</title>
</head>
<body>
<%
    DataAdapter adapter = new DataAdapter(request.getContextPath());%>
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="doAdd btn btn-primary">Добавить</button>
            </div>
        </div>
    </div>
</div>
<div class="header">
    <h3 style="horiz-align: left; display: inline-block">Списки</h3>
    <h3 style="horiz-align: right; display: inline-block">Списки</h3>
</div>
<div class="data">
    <div class="accordion" id="accordionPanelsStayOpenExample">
        <%
            ListItem list = adapter.getLists();
            for (int i = 0; i < list.getArrayList().size(); i++) {
        %>
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                        data-bs-target="#akk<%=i%>" aria-expanded="true"
                        aria-controls="akk<%=i%>">
                    <%=((ListItem) list.getArrayList().get(i)).getTitle()%>
                </button>
            </h2>
            <div id="akk<%=i%>" class="accordion-collapse collapse show">
                <div class="accordion-body">
                    <%=adapter.print((ListItem) list.getArrayList().get(i), new StringBuilder())%>

                </div>
            </div>
        </div>
        <%}%>
    </div>

</div>
<button id="root" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> добавить список</button>
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