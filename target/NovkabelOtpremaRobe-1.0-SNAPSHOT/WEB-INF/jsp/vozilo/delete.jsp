<%-- 
    Document   : delete
    Created on : Sep 1, 2018, 11:30:48 PM
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
        <title>Vozilo delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/vozilo/find">Evidencija vozila</a> / Brisanje </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/vozilo/delete/${vozilo.registarskiBroj}" method="post">
                <div class="form-group">
                    <label for="registarskiBroj">Registarski broj</label>
                    ${vozilo.registarskiBroj}
                </div>
                <div class="form-group">
                    <label for="nazivVozila">Naziv vozila</label>
                    ${vozilo.nazivVozila}
                </div>
                <div class="form-group">
                    <label for="spediter">Spediter</label>
                        ${vozilo.spediter.nazivSpeditera}
                </div>
                <br>
                
                <div class='text-left'>
                    <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/vozilo/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form>
            
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
