<%-- 
    Document   : delete
    Created on : Sep 6, 2018, 7:22:20 PM
    Author     : Aleksa Dencic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Stavka otpremnice delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaOtpremnice/find/${brojOtpremnice}">Evidencija stavki otpremnice br. ${brojOtpremnice}</a> / Brisanje </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/stavkaOtpremnice/delete/${brojOtpremnice}/${stavkaOtpremnice.rbr}" method="post">                
                <div class="form-group">
                    <label for="nazivVozila">Proizvod</label>
                    ${stavkaOtpremnice.proizvod}
                </div>
                <div class="form-group">
                    <label for="spediter">Kolicina</label>
                    ${stavkaOtpremnice.kolicina}
                </div>
                <br>

                <div class='text-left'>
                    <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/stavkaOtpremnice/find/${brojOtpremnice}" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form>

        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
