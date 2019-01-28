<%-- 
    Document   : insert
    Created on : Sep 5, 2018, 2:18:28 AM
    Author     : Aleksa Dencic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Otpremnica insert</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group" align="center">
                <div class="list-group-item">
                    <b><font size="4"><a href="/otpremnica/find">Evidencija otpremnica</a> / Unos </font></b>
                </div>
            </div>
            <br><br>

            <div class="container-fluid">
                <form:form action="/otpremnica/insert" method="post" modelAttribute="otpremnica">
                    <div class="form-group">
                        <form:label path="brojOtpremnice">Broj otpremnice</form:label>
                        <form:input path="brojOtpremnice" class="form-control" id="brojOtpremnice" value="${otpremnica.brojOtpremnice}" disabled="true" />
                    </div>
                    <div class="form-group">
                        <form:label path="nalog.sifraNaloga">Nalog</form:label>
                        <form:select class="form-control" path="nalog.sifraNaloga">                                          
                            <form:options items="${nalogList}" itemLabel="sifraNaloga" itemValue="sifraNaloga" />
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label path="magacin.sifraMagacina">Magacin</form:label>
                        <form:select class="form-control" path="magacin.sifraMagacina">                                          
                            <form:options items="${magacinList}" itemLabel="nazivMagacina" itemValue="sifraMagacina" />
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label path="datumPro">Datum proizvodnje</form:label>
                        <fmt:formatDate value="${otpremnica.datumPro}" var="formattedDate" type="date" pattern="yyyy/MM/dd" />                                           
                        <form:input path="datumPro" class="form-control" id="datumPro" value="${formattedDate}"/>
                    </div>
                    <div class="form-group">
                        <form:label path="datumIzd">Datum izdavanja</form:label>
                        <fmt:formatDate value="${otpremnica.datumIzd}" var="formattedDate" type="date" pattern="yyyy/MM/dd" />                                           
                        <form:input path="datumIzd" class="form-control" id="datumIzd" value="${formattedDate}"/>
                    </div>  
                    <div class="form-group">
                        <form:label path="napomena">Napomena</form:label>
                        <form:input path="napomena" class="form-control" id="napomena" />
                    </div>
                    <br>   
                    
                    <div class='text-right'>
                        <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                        <a href="/otpremnica/find" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                    </div>

                </form:form>
            </div>         
        </div>
        <div class="col-6 col-md-1"></div>
    </body>              
</html>
