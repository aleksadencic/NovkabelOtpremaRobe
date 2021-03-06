<%-- 
    Document   : find
    Created on : Sep 1, 2018, 11:31:41 PM
    Author     : Aleksa Dencic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Evidencija vozila</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Evidencija vozila</font></b>
                </div>
            </div>
            <br><br>

            <table class='table table-condensed table-align'>
                <thead>
                    <tr>
                        <th>Registarski broj</th>
                        <th>Naziv vozila</th>
                        <th>Spediter</th>
                        <th class='text-right'><a href="/vozilo/insert" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voziloList}" var="vozilo" >
                        <tr>
                            <td>
                                <p class='form-control-static'>
                                    ${vozilo.registarskiBroj}
                                </p>
                            </td>   
                            <td>
                                <p class='form-control-static'>
                                    ${vozilo.nazivVozila}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${vozilo.spediter.nazivSpeditera}
                                </p>
                            </td>            
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/vozilo/details/${vozilo.registarskiBroj}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        <a href="/vozilo/update/${vozilo.registarskiBroj}" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                                        <a href="/vozilo/delete/${vozilo.registarskiBroj}" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>               
                        </tr>
                    </c:forEach>
                </tbody>
            </table>      
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
