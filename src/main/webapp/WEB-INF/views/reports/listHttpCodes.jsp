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
    <h1>List HTTP-Codes</h1>

    <table class="highchart" data-graph-container-before="1" data-graph-type="column" style="display:none">
        <thead>
        <tr><th>List HTTP Codes</th><th>nr</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${listHttpCodes.content}" var="ip">
            <tr><td>${ip.httpCode}</td><td>${ip.nr}</td></tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-striped table-condensed table-bordered">
        <tr><th>List URLs for HTTP Code</th><th>nr</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr>
        <c:forEach items="${listHttpCodes.content}" var="ip">
            <tr><td>${ip.httpCode}</td><td>${ip.nr}</td>
                <td><a href="/reports/listHttpCodes/${ip.id}/url">URL</a></td>
                <td><a href="/reports/listHttpCodes/${ip.id}/browser">Browser</a></td>
                <td><a href="/reports/listHttpCodes/${ip.id}/ip">IP</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('table.highchart').highchartTable();
    });
</script>

</body>
</html>