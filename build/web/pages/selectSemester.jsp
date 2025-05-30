<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
    List<String> semesters = (List<String>) request.getAttribute("semesters");
%>
<html>
<head>
    <title>Select Semester</title>
    <link rel="stylesheet" href="css/semcss.css">
</head>
<body>
<div class="background">
    <div class="card">
        <h2>Select Your Semester</h2>
        <form method="post" action="SubjectServlet">
            <div class="form-group">
                <label for="semester">Semester:</label>
                <select id="semester" name="semester" required>
                    <% if (semesters != null) {
                        for (String sem : semesters) { %>
                            <option value="<%= sem %>"><%= sem %></option>
                    <%  }
                    } else { %>
                        <option disabled>No semesters available</option>
                    <% } %>
                </select>
            </div>
            <input type="submit" value="Continue" class="submit-btn"/>
        </form>
    </div>
</div>
</body>
</html>
