<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    String subject = (String) request.getAttribute("subject");
%>
<html>
<head>
    <title>Books for <%= subject %></title>
    <link rel="stylesheet" href="css/bookcss.css"/>
</head>
<body>
<div class="background">
    <div class="card">
        <h2>Books for Subject: <%= subject %></h2>
        <h3>Available books: <%= books.size() %></h3>

        <% if (books != null && !books.isEmpty()) { %>
            <ul class="book-list">
                <% for (Book book : books) { %>
                    <li>
                        <a href="<%= book.getFilePath() %>" download>
                            📘 <%= book.getName() %>
                        </a>
                    </li>
                <% } %>
            </ul>
        <% } else { %>
            <p class="no-books">No books available for this subject.</p>
        <% } %>
    </div>
</div>
</body>
</html>
