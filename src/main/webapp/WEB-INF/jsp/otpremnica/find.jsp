<%-- 
    Document   : find
    Created on : Sep 5, 2018, 2:18:20 AM
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
        <title>Evidencija otpremnica</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
                        
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Evidencija otpremnica</font></b>
                </div>
            </div>
            <br><br>
            
            <table class='table table-condensed table-align'>
                <thead>
                    <tr>
                        <th>Broj otpremnice</th>
                        <th>Nalog</th>
                        <th>Magacin</th>
                        <th>Datum proizvodnje</th>
                        <th>Datum izdavanja</th>
                        <th>Napomena</th>

                        <th class='text-right'><a href="/otpremnica/insert" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${otpremnicaList}" var="otpremnica" >

                        <tr>
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.brojOtpremnice}
                                </p>
                            </td> 
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.nalog}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.magacin}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.datumPro}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.datumIzd}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${otpremnica.napomena}
                                </p>
                            </td>                             
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/otpremnica/details/${otpremnica.brojOtpremnice}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        <a href="/otpremnica/update/${otpremnica.brojOtpremnice}" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                                        <a href="/otpremnica/delete/${otpremnica.brojOtpremnice}" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
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

