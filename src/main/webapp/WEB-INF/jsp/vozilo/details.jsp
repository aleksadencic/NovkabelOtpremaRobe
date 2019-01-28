<%-- 
    Document   : details
    Created on : Sep 1, 2018, 11:31:04 PM
    Author     : Aleksa Dencic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Vozilo details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/vozilo/find">Evidencija vozila</a> / Detalji </font></b>
                </div>
            </div>
            
            <div class='text-right'>
                <a href="/vozilo/update/${vozilo.registarskiBroj}" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
                <a href="/vozilo/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>
            
            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Registarski broj</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${vozilo.registarskiBroj}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Naziv vozila</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${vozilo.nazivVozila}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Spediter</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${vozilo.spediter.nazivSpeditera}
                        </p>
                    </div>
                </div>
                        

            </div>
        </div>
        <div class="col-6 col-md-1"></div>
        <br><br>
    </body>
</html>
