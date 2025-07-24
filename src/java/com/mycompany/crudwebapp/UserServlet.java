package com.mycompany.crudwebapp; // Adjust package name

import crudwebapp.User;
import crudwebapp.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// If using Jakarta EE 8 or newer, @WebServlet annotation simplifies web.xml configuration
@WebServlet("/UserServlet") // Or "/users" if you prefer
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "list":
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Often, POST requests are used for insert/update operations
        // We can delegate to doGet or handle separately.
        // For simplicity, we'll route based on action parameter for both GET/POST here.
        doGet(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> listUsers = userDAO.getAllUsers();
        request.setAttribute("listUsers", listUsers);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));

        User newUser = new User(name, email, age);
        userDAO.addUser(newUser);
        response.sendRedirect("UserServlet?action=list"); // Redirect to list users
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.getUserById(id);
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(id, name, email, age);
        userDAO.updateUser(user);
        response.sendRedirect("UserServlet?action=list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("UserServlet?action=list");
    }
}