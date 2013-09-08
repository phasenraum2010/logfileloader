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
    <h1>List Browser for URL ${request.request}</h1>
    <table class="table table-striped table-condensed table-bordered">
        <tr><th>browser</th><th>nr</th></tr>
        <c:forEach items="${listBrowser.content}" var="ip">
            <tr><td>${ip.browser}</td><td>${ip.nr}</td></tr>
        </c:forEach>
    </table>
    <div class="pagination">
        <ul>
            <c:if test="${listBrowser.firstPage}">
                <li class="disabled"><a href="/reports/listPages/${request.id}/browser?page.page=${listBrowser.number}&page.size=${listBrowser.size}">&laquo;</a></li>
            </c:if>
            <c:if test="${not listBrowser.firstPage}">
                <li><a href="/reports/listPages/${request.id}/browser?page.page=${listBrowser.number}&page.size=${listBrowser.size}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${listBrowser.totalPages}" var="i">
                <c:if test="${listBrowser.number+1 eq i}">
                    <li class="active"><a href="/reports/listPages/${request.id}/browser?page.page=${i}&page.size=${listBrowser.size}">${i}</a></li>
                </c:if>
                <c:if test="${listBrowser.number+1 ne i}">
                    <li><a href="/reports/listPages/${request.id}/browser?page.page=${i}&page.size=${listBrowser.size}">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${listBrowser.lastPage}">
                <li class="disabled"><a href="/reports/listPages/${request.id}/browser?page.page=${listBrowser.number +2}&page.size=${listBrowser.size}">&raquo;</a></li>
            </c:if>
            <c:if test="${not listBrowser.lastPage}">
                <li><a href="/reports/listPages/${request.id}/browser?page.page=${listBrowser.number +2}&page.size=${listBrowser.size}">&raquo;</a></li>
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