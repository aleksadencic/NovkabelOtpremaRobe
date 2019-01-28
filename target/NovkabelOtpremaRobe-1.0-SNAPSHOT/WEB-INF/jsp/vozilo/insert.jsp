<%-- 
    Document   : insert
    Created on : Sep 1, 2018, 11:31:49 PM
    Author     : Aleksa Dencic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Vozilo insert</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group" align="center">
                <div class="list-group-item">
                    <b><font size="4"><a href="/vozilo/find">Evidencija vozila</a> / Unos </font></b>
                </div>
            </div>
            <br><br>
            
            <div class="container-fluid">
                <form:form action="/vozilo/insert" method="post" modelAttribute="vozilo">
                    <div class="form-group">
                        <form:label path="registarskiBroj">Registarski broj</form:label>
                        <form:input path="registarskiBroj" class="form-control" id="registarskiBroj" />
                    </div>
                    <div class="form-group">
                        <form:label path="nazivVozila">Naziv vozila</form:label>
                        <form:input path="nazivVozila" class="form-control" id="nazivVozila" />
                    </div>
                    <div class="form-group">
                        <form:label path="spediter.sifraSpeditera">Spediter</form:label>
                        <form:select class="form-control" path="spediter.sifraSpeditera">                                          
                            <form:options items="${spediterList}" itemLabel="nazivSpeditera" itemValue="sifraSpeditera" />
                        </form:select>
                    </div>
                    <br>                        
                    <div class='text-right'>
                        <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                        <a href="/vozilo/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                    </div>
                        
                </form:form>
            </div>         
        </div>
        <div class="col-6 col-md-1"></div>
    </body>              
</html>

