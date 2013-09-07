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
    <h1>List Pages</h1>
    <table class="table table-striped table-condensed table-bordered">
        <tr><th>url</th><th>nr</th><th>&nbsp;</th></tr>
        <c:forEach items="${listPages.content}" var="ip">
            <tr><td>${ip.request}</td><td>${ip.nr}</td><td><a href="/reports/listPages/${ip.id}/browser">Browser</a></td></tr>
        </c:forEach>
    </table>
    <div class="pagination">
        <ul>
            <c:if test="${listPages.firstPage}">
                <li class="disabled"><a href="/reports/listPages?page.page=${listPages.number}&page.size=${listPages.size}">&laquo;</a></li>
            </c:if>
            <c:if test="${not listPages.firstPage}">
                <li><a href="/reports/listPages?page.page=${listPages.number}&page.size=${listPages.size}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${listPages.totalPages}" var="i">
                <c:if test="${listPages.number+1 eq i}">
                    <li class="active"><a href="/reports/listPages?page.page=${i}&page.size=${listPages.size}">${i}</a></li>
                </c:if>
                <c:if test="${listPages.number+1 ne i}">
                    <li><a href="/reports/listPages?page.page=${i}&page.size=${listPages.size}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${listPages.lastPage}">
                <li class="disabled"><a href="/reports/listPages?page.page=${listPages.number +2}&page.size=${listPages.size}">&raquo;</a></li>
            </c:if>
            <c:if test="${not listPages.lastPage}">
                <li><a href="/reports/listPages?page.page=${listPages.number +2}&page.size=${listPages.size}">&raquo;</a></li>
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