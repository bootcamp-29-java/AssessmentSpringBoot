<%-- 
    Document   : report
    Created on : Sep 24, 2019, 8:35:58 PM
    Author     : ASUS
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <% String id = %> ${printId} <%;%>
            <%
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_assessment", "root", "");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                File report = new File(application.getRealPath("/reports/reportAssessment.jasper"));
                Map param = new HashMap();
                param.put("id", id);
                byte[] bytes = JasperRunManager.runReportToPdf(report.getPath(), param, con);

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outStreaam = response.getOutputStream();
                outStreaam.write(bytes, 0, bytes.length);
                outStreaam.flush();
                outStreaam.close();
            %>
        </body>
    </html>
</f:view>
