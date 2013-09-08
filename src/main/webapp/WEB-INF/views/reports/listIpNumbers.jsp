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
    <h1>List IP-Numbers</h1>
    <table class="table table-striped table-condensed table-bordered">
        <tr><th>ip</th><th>nr</th><th>&nbsp;</th></tr>
        <c:forEach items="${ipNumbersReport.content}" var="ip">
            <tr><td>${ip.ip}</td><td>${ip.nr}</td><td><a href="/reports/listIpNumbers/${ip.id}/url">URL</a></td></tr>
        </c:forEach>
    </table>
    <div class="pagination">
        <ul>
            <c:if test="${ipNumbersReport.firstPage}">
                <li class="disabled"><a href="/reports/listIpNumbers?page.page=${ipNumbersReport.number}&page.size=${ipNumbersReport.size}">&laquo;</a></li>
            </c:if>
            <c:if test="${not ipNumbersReport.firstPage}">
                <li><a href="/reports/listIpNumbers?page.page=${ipNumbersReport.number}&page.size=${ipNumbersReport.size}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${ipNumbersReport.totalPages}" var="i">
                <c:if test="${ipNumbersReport.number+1 eq i}">
                    <li class="active"><a href="/reports/listIpNumbers?page.page=${i}&page.size=${ipNumbersReport.size}">${i}</a></li>
                </c:if>
                <c:if test="${ipNumbersReport.number+1 ne i}">
                    <li><a href="/reports/listIpNumbers?page.page=${i}&page.size=${ipNumbersReport.size}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${ipNumbersReport.lastPage}">
                <li class="disabled"><a href="/reports/listIpNumbers?page.page=${ipNumbersReport.number +2}&page.size=${ipNumbersReport.size}">&raquo;</a></li>
            </c:if>
            <c:if test="${not ipNumbersReport.lastPage}">
                <li><a href="/reports/listIpNumbers?page.page=${ipNumbersReport.number +2}&page.size=${ipNumbersReport.size}">&raquo;</a></li>
            </c:if>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(window).load(function(){
    });
</script>

</body>
</html>