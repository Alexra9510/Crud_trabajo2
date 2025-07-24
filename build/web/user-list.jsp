<%@page import="crudwebapp.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="crudwebapp.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <style>
            table { width: 80%; border-collapse: collapse; margin: 20px 0; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            .button-link {
                display: inline-block;
                padding: 8px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-bottom: 10px;
            }
            .button-link:hover {
                background-color: #0056b3;
            }
            .action-button {
                padding: 5px 10px;
                margin: 2px;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            .edit-button { background-color: #ffc107; color: black; }
            .delete-button { background-color: #dc3545; color: white; }
        </style>
    </head>
    <body>
        <h1>User List</h1>
        <p><a href="UserServlet?action=new" class="button-link">Add New User</a></p>

        <%
            List<User> listUsers = (List<User>) request.getAttribute("listUsers");
            if (listUsers != null && !listUsers.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Age</th>
                    <th>Actions</th>
                </tr>
            </thead>
            
                <% for (User user : listUsers) { %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getAge() %></td>
                    
                        <a href="UserServlet?action=edit&id=<%= user.getId() %>" class="action-button edit-button">Edit</a>
                        <a href="UserServlet?action=delete&id=<%= user.getId() %>" class="action-button delete-button" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                    
                </tr>
                <% } %>
            
        </table>
        <% } else { %>
        <p>No users found. Add a new user!</p>
        <% } %>
    </body>
