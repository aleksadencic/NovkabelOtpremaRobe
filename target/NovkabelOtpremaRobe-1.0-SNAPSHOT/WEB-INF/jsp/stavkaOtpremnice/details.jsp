<%-- 
    Document   : details
    Created on : Sep 6, 2018, 7:23:22 PM
    Author     : Aleksa Dencic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Stavka otpremnice details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaOtpremnice/find/${brojOtpremnice}">Evidencija stavki otpremnice br. ${brojOtpremnice}</a> / Detalji </font></b>
                </div>
            </div>
            
            <div class='text-right'>
                <a href="/stavkaOtpremnice/update/${brojOtpremnice}" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
                <a href="/stavkaOtpremnice/find/${brojOtpremnice}" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>
            
            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Proizvod</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${stavkaOtpremnice.proizvod}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Kolicina</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${stavkaOtpremnice.kolicina}
                        </p>
                    </div>
                </div>
                        

            </div>
        </div>
        <div class="col-6 col-md-1"></div>
        
    </body>
</html>
