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
        <tr><th>ip</th><th>nr</th></tr>
        <c:forEach items="${timelineDays.content}" var="ip">
            <tr><td>${ip.day}</td><td>${ip.nr}</td></tr>
        </c:forEach>
    </table>
    <div class="pagination">
        <ul>
            <c:if test="${timelineDays.firstPage}">
                <li class="disabled"><a href="/reports/timelineDays?page.page=${timelineDays.number}&page.size=${timelineDays.size}">&laquo;</a></li>
            </c:if>
            <c:if test="${not timelineDays.firstPage}">
                <li><a href="/reports/timelineDays?page.page=${timelineDays.number}&page.size=${timelineDays.size}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${timelineDays.totalPages}" var="i">
                <c:if test="${timelineDays.number+1 eq i}">
                    <li class="active"><a href="/reports/timelineDays?page.page=${i}&page.size=${timelineDays.size}">${i}</a></li>
                </c:if>
                <c:if test="${timelineDays.number+1 ne i}">
                    <li><a href="/reports/timelineDays?page.page=${i}&page.size=${timelineDays.size}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${timelineDays.lastPage}">
                <li class="disabled"><a href="/reports/timelineDays?page.page=${timelineDays.number +2}&page.size=${timelineDays.size}">&raquo;</a></li>
            </c:if>
            <c:if test="${not timelineDays.lastPage}">
                <li><a href="/reports/timelineDays?page.page=${timelineDays.number +2}&page.size=${timelineDays.size}">&raquo;</a></li>
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