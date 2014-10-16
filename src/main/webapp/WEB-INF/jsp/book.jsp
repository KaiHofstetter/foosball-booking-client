<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Foosball Booking Sample Client</title>
    <link type="text/css" rel="stylesheet"
          href="./resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet"
          href="./resources/css/bootstrap-datetimepicker.min.css"/>
    <script type="text/javascript" src="./resources/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./resources/js/moment.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>

<div class="jumbotron">
    <h1>Foosball Booking Sample Client</h1>

    <form role="form" method="post" action="/foosball-booking-client/booking">
        <div class="form-group">
            <label for="beginDateTime">Begin</label>
            <div class="input-group date" id="datetimepicker1">
                <input name="beginDateTime" type='text' class="form-control" placeholder="Begin date and time" data-date-format="hh:mm DD.MMMM YYYY"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker1').datetimepicker();
                });
            </script>
            <label for="endDateTime">End</label>
            <div class="input-group date" id="datetimepicker2">
                <input name="endDateTime" type='text' class="form-control" placeholder="End date and time" data-date-format="hh:mm DD.MMMM YYYY"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker2').datetimepicker();
                });
            </script>
            <label for="Name">Name</label>
            <input name="Name" type='text' class="form-control" placeholder="Name"/>
            <label for="Comment">Comment</label>
            <input name="Comment" type='text' class="form-control" placeholder="Comment"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>
