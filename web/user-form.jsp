<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="crudwebapp.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Form</title>
        <style>
            form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
            label { display: block; margin-bottom: 5px; font-weight: bold; }
            input[type="text"], input[type="email"], input[type="number"] {
                width: calc(100% - 22px);
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            input[type="submit"] {
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
            .back-link {
                display: inline-block;
                margin-top: 15px;
                color: #007bff;
                text-decoration: none;
            }
            .back-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <%
            User user = (User) request.getAttribute("user");
            String title = (user == null) ? "Add New User" : "Edit User";
            String action = (user == null) ? "insert" : "update";
        %>
        <h1><%= title %></h1>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="<%= action %>"/>
            <% if (user != null) { %>
                <input type="hidden" name="id" value="<%= user.getId() %>"/>
            <% } %>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= (user != null) ? user.getName() : "" %>" required/><br/>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= (user != null) ? user.getEmail() : "" %>" required/><br/>

            <label for="age">Age:</label>
            <input type="number" id="age" name="age" value="<%= (user != null) ? user.getAge() : "" %>" required/><br/>

            <input type="submit" value="Save User"/>
        </form>
        <p><a href="UserServlet?action=list" class="back-link">Back to User List</a></p>
    </body>
</html>