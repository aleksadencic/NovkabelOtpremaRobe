<%-- 
    Document   : update
    Created on : Sep 6, 2018, 7:24:06 PM
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
        <title>Stavka otpremnice update</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaOtpremnice/find/${brojOtpremnice}">Evidencija stavki otpremnice br. ${brojOtpremnice}</a> / Izmena </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/stavkaOtpremnice/update/${brojOtpremnice}/${stavkaOtpremnice.rbr}" method="post" modelAttribute="stavkaOtpremnice" >

                
                
                <div class="form-group">
                    <form:label path="proizvod.sifraProizvoda">Proizvod</form:label>
                    <form:select class="form-control" path="proizvod.sifraProizvoda">                                          
                        <form:options items="${proizvodList}" itemLabel="nazivProizvoda" itemValue="sifraProizvoda" />
                    </form:select>
                </div>

                <div class="form-group">
                    <form:label path="kolicina">Kolicina</form:label>
                    <form:input path="kolicina" class="form-control" id="kolicina" value="${kolicina}" />
                </div>
                <br>                        
                <div class='text-right'>
                    <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                    <a href="/stavkaOtpremnice/find/${brojOtpremnice}" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>

            </form:form>                

        </div>

        <div class="col-6 col-md-1"></div>

    </body>
</html>
