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
    <script src="<c:url value='/js/highcharts.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/jquery.highchartTable.js'/>" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <h1>Dashboard</h1>

    <table class="highchart" data-graph-container-before="1" data-graph-type="line" style="display:none">
        <caption>Page Impressions per Day</caption>
        <thead>
        <tr>
            <th>day</th>
            <th>page impressions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDays.content}" var="ip">
            <tr>
                <td>${ip.day}</td>
                <td>${ip.nr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="highchart" data-graph-container-before="1" data-graph-type="column" style="display:none">
        <caption>HTTP Codes</caption>
        <thead>
        <tr>
            <th>HTTP Code</th>
            <th>Number of HTTP Requests</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listHttpCodes.content}" var="ip">
            <tr>
                <td>${ip.httpCode}</td>
                <td>${ip.nr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="highchart"
           data-graph-container-before="1"
           data-graph-type="column"
           data-graph-inverted="1"
           data-graph-xaxis-labels-enabled="0"
           style="display:none">
        <caption>Pages</caption>
        <thead>
        <tr>
            <th>Page</th>
            <th>Number of Page Impressions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPages.content}" var="ip">
            <tr>
                <td>${ip.request}</td>
                <td>${ip.nr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="highchart"
           data-graph-container-before="1"
           data-graph-type="pie"
           data-graph-xaxis-labels-enabled="1"
           style="display:none">
        <caption>Browser</caption>
        <thead>
        <tr>
            <th>Browser</th>
            <th>Number of Requests</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listBrowser.content}" var="ip">
            <tr>
                <td>${ip.browser}</td>
                <td>${ip.nr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('table.highchart').highchartTable();
    });
</script>

</body>
</html>