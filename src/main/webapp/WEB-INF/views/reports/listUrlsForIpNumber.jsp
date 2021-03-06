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
</head>
<body>
<div class="container">
    <h1>List URLs for IP Number ${ipNumber.ip}</h1>
    <table class="table table-striped table-condensed table-bordered">
        <tr><th>URL</th><th>nr</th></tr>
        <c:forEach items="${listPages.content}" var="ip">
            <tr><td>${ip.request}</td><td>${ip.nr}</td></tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    $(window).load(function(){
    });
</script>

</body>
</html>