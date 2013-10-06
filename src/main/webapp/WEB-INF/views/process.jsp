<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Logfileloader</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" media="screen">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>"  type="text/css">
    <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-1.9.1.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.periodicalupdater.js'/>"></script>
</head>
<body>
<div class="container">
    The Logfile Processing is Started. <a href="/">back to main menu.</a>
    <div id="content">
        <c:import url="/WEB-INF/views/processing.jsp" />
    </div>
</div>
<script type="text/javascript">
    $(window).load(function(){
        $.PeriodicalUpdater('<c:url value="/ajax/processing"/>', {
            method: 'get', // method; get or post
            data: '', // array of values to be passed to the page - e.g. {name: "John", greeting: "hello"}
            minTimeout: 5000, // starting value for the timeout in milliseconds
            maxTimeout: 10000, // maximum length of time between requests
            multiplier: 2, // the amount to expand the timeout by if the response hasn't changed (up to maxTimeout)
            type: 'text', // response type - text, xml, json, etc. See $.ajax config options
            maxCalls: 0, // maximum number of calls. 0 = no limit.
            autoStop: 0 // automatically stop requests after this many returns of the same data. 0 = disabled.
        }, function(remoteData, success, xhr, handle) {
            $('#content').html(remoteData);
        });
    });
</script>

</body>
</html>