<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Foosball Booking Sample Client</title>
    <link type="text/css" rel="stylesheet"
          href="./webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <script type="text/javascript"
            src="./webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="./webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
</head>
<body>

<div class="jumbotron">
    <h1>Foosball Booking Sample Client</h1>
    <h2>Overview</h2>
    <table class="table table-striped" width="800">
        <thead>
        <tr>
            <th width="18%">Begin</th>
            <th width="18%">End</th>
            <th>Name</th>
            <th>Comment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
            <c:set var="begin" value="${booking.getBegin()}"/>
            <c:set var="end" value="${booking.getEnd()}"/>
            <tr>
                <td>${begin.getHour()}:${begin.getMinute()} ${begin.getDayOfMonth()}.${begin.getMonth()} ${begin.getYear()}</td>
                <td>${end.getHour()}:${end.getMinute()} ${end.getDayOfMonth()}.${end.getMonth()} ${end.getYear()}</td>
                <td>${booking.getUser()}</td>
                <td>${booking.getComment()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a class="btn btn-primary btn-lg" role="button" href="http://localhost:8090/foosball-booking-client/bookings">Add booking</a></p>
</div>
</body>
</html>
