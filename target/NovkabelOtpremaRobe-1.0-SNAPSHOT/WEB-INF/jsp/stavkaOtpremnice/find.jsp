<%-- 
    Document   : find
    Created on : Sep 6, 2018, 7:23:30 PM
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
        <title>Evidencija stavki otpremnice</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Evidencija stavki otpremnice br. ${brojOtpremnice}</font></b>
                </div>
            </div>
            <br><br>

            <table class='table table-condensed table-align'>
                <thead>
                    <tr>
                        <th>Proizvod</th>
                        <th>Kolicina</th>                        

                        <th class='text-right'><a href="/stavkaOtpremnice/insert/${brojOtpremnice}" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stavkeOtpremniceList}" var="stavkaOtpremnice" >

                        <tr>                            
                            <td>
                                <p class='form-control-static'>
                                    ${stavkaOtpremnice.proizvod}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${stavkaOtpremnice.kolicina}
                                </p>
                            </td>                                                   
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/stavkaOtpremnice/details/${brojOtpremnice}/${stavkaOtpremnice.rbr}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        <a href="/stavkaOtpremnice/update/${brojOtpremnice}/${stavkaOtpremnice.rbr}" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                                        <a href="/stavkaOtpremnice/delete/${brojOtpremnice}/${stavkaOtpremnice.rbr}" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>               
                        </tr>
                    </c:forEach>                              
                </tbody>
            </table>    
            <br><br>
            <div class='text-right'>
                <a href="/otpremnica/update/${brojOtpremnice}" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></a>
                <!-- <a href="/otpremnica/noChangeItems" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a> -->
            </div>
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
