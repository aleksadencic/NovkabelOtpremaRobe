<%-- 
    Document   : delete
    Created on : Sep 5, 2018, 2:17:59 AM
    Author     : Aleksa Dencic
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Otpremnica delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/otpremnica/find">Evidencija otpremnica</a> / Brisanje </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/otpremnica/delete/${otpremnica.brojOtpremnice}" method="post">
                <div class="form-group">
                    <label for="registarskiBroj">Broj otpremnice</label>
                    ${otpremnica.brojOtpremnice}
                </div>
                <div class="form-group">
                    <label for="nazivVozila">Nalog</label>
                    ${otpremnica.nalog}
                </div>
                <div class="form-group">
                    <label for="magacin">Magacin</label>
                    ${otpremnica.magacin}
                </div>
                <div class="form-group">
                    <label for="datumPro">Datum proizvodnje</label>
                    ${otpremnica.datumPro}
                </div>
                <div class="form-group">
                    <label for="datumIzd">Datum izdavanja</label>
                    ${otpremnica.datumIzd}
                </div>
                <div class="form-group">
                    <label for="napomena">Napomena</label>
                    ${otpremnica.napomena}
                </div>
                <br>

                <div class='form-group'>
                    <table class="table table-condensed table-align">
                        <thead>
                            <tr>
                                <th>Rbr</th>
                                <th>Proizvod</th>
                                <th>Kolicina</th>                                
                            </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${otpremnica.stavkeOtpremnice}" var="stavkaOtpremnice" >
                            <tr>
                                <td>
                                    <p class='form-control-static'>
                                        ${stavkaOtpremnice.rbr}
                                    </p>
                                </td> 
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
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>
                </div>                

                <div class='text-left'>
                    <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/otpremnica/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>
                <br><br>
            </form:form>

        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
