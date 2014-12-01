<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Foosball Booking Sample Client</title>

    <link type="text/css" rel="stylesheet"
          href="./webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet"
          href="./resources/css/bootstrap-datetimepicker.min.css"/>
    <script type="text/javascript" src="./webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="./webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./webjars/momentjs/2.8.3/moment.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>

<div class="jumbotron">
    <h1>Foosball Booking Sample Client</h1>

    <h2>Booking</h2>
    <form class="form-horizontal" role="form" method="post" action="/foosball-booking-client/booking">
        <div class="form-group">
            <label class="col-sm-1 control-label" for="begin">Begin</label>

            <div class="col-sm-2">
                <div class="input-group time" id="timepicker1">
                    <input name="beginTime" type='text' class="form-control" placeholder="Time" data-date-format="HH:mm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                    </span>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="input-group date" id="datepicker1">
                    <input name="beginDate" type='text' class="form-control" placeholder="Date" data-date-format="DD.MM.YYYY"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#timepicker1').datetimepicker({ pickDate: false});
                });
                $(function () {
                    $('#datepicker1').datetimepicker({ pickTime: false});
                });
            </script>
        </div>


        <div class="form-group">
            <label class="col-sm-1 control-label" for="end">End</label>

            <div class="col-sm-2">
                <div class="input-group time" id="timepicker2">
                    <input name="endTime" type='text' class="form-control" placeholder="Time" data-date-format="HH:mm"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                    </span>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="input-group date" id="datepicker2">
                    <input name="endDate" type='text' class="form-control" placeholder="Date" data-date-format="DD.MM.YYYY"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#timepicker2').datetimepicker({ pickDate: false});
                });
                $(function () {
                    $('#datepicker2').datetimepicker({ pickTime: false});
                });
            </script>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-1" for="Comment">Comment</label>

            <div class="col-sm-3">
                <input id="Comment" name="comment" type='text' class="form-control" placeholder="Comment"/>
            </div>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
