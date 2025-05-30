<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
    List<String> subjects = (List<String>) request.getAttribute("subjects");
    String semester = (String) request.getAttribute("selectedSemester");
%>
<html>
<head>
    <title>Subjects for <%= semester %></title>
    <link rel="stylesheet" href="css/subjectcss.css">
</head>
<body>
<div class="overlay">
    <div class="card">
        <h2>Semester: <%= semester %></h2>

        <% if (subjects != null && !subjects.isEmpty()) { %>
            <form method="post" action="BookServlet">
                <div class="form-group">
                    <label for="subject">Select Subject:</label>
                    <select id="subject" name="subject" required>
                        <% for (String subject : subjects) { %>
                            <option value="<%= subject %>"><%= subject %></option>
                        <% } %>
                    </select>
                </div>
                <input type="hidden" name="semester" value="<%= semester %>"/>
                Note: If your subject is not in the list, please check the semester before and the one after it.
                <button type="submit" class="submit-btn">Show Books</button>
            </form>
        <% } else { %>
            <p class="error-message">No subjects available for the selected semester.</p>
        <% } %>
    </div>
</div>
</body>
</html>
