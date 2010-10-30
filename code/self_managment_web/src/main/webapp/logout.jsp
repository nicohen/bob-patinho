<% try {
    session.invalidate();
} catch (Exception ignore) {}
response.sendRedirect("index.html");
return; %>