<%-- 
    Document   : newjspdetails
    Created on : Sep 5, 2018, 2:18:07 AM
    Author     : Aleksa Dencic
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Otpremnica details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/otpremnica/find">Evidencija otpremnica</a> / Detalji </font></b>
                </div>
            </div>

            <div class='text-right'>
                <a href="/otpremnica/update/${otpremnica.brojOtpremnice}" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
                <a href="/otpremnica/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Broj otpremnice</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.brojOtpremnice}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Nalog</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.nalog}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Magacin</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.magacin}
                        </p>
                    </div>
                </div>     

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Datum proizvodnje</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.datumPro}
                        </p>
                    </div>
                </div> 


                <div class='form-group'>
                    <label class='control-label col-xs-4'>Datum izdavanja</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.datumIzd}
                        </p>
                    </div>
                </div> 

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Napomena</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${otpremnica.napomena}
                        </p>
                    </div>
                </div>

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

                        <c:forEach items="${otpremnica.stavkeOtpremnice}" var="stavkaOtpremnice">
                            <tr>
                                <td>${stavkaOtpremnice.rbr}</td>
                                <td>${stavkaOtpremnice.proizvod}</td>
                                <td>${stavkaOtpremnice.kolicina}</td>
                                <td></td>
                            </tr>
                        </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-6 col-md-1"></div>
        <br><br>
    </body>
</html>
