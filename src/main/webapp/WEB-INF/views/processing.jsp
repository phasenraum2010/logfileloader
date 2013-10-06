<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<p>
Total Source Lines: <c:out value="${processingStatus.allSourceLines}" /> <br />
Waiting for Queue-Input: <c:out value="${processingStatus.sourceLinesToBeProcessed}" /><br />
Lines in Queue: <c:out value="${processingStatus.linesInQueue}" /><br />
Queue-Finished: <c:out value="${processingStatus.allTargetLineItems}" /> von  <c:out value="${processingStatus.allSourceLines}" /><br />
</p>
