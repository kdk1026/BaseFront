<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

<c:if test="${page.firstPage > 1}">
	<li class="page-item">
		<a href="javascript:void(0)" onclick="fnGo('${formId}', '1')">
			<i class="bi bi-skip-backward-fill"></i>
		</a>
	</li>
	<li class="page-item">
		<a class="page-link" href="javascript:void(0)" onclick="fnGo('${formId}', '${page.prevBlockPage}')">
			<i class="bi bi-caret-left-fill"></i>
		</a>
	</li>
</c:if>

<c:forEach var="i" begin="${page.firstPage}" end="${page.lastPage}" step="1">
	<c:choose>
		<c:when test="${i == page.currentPage}">
			<li class="page-item active">
				<a class="page-link" href="javascript:void(0)">${i}</a>
			</li>
		</c:when>
		<c:otherwise>
			<li>
				<a class="page-link" href="javascript:void(0)" onclick="fnGo('${formId}', '${i}')">${i}</a>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${page.lastPage != page.totalPage}">
	<li class="page-item">
		<a class="page-link" href="javascript:void(0)" onclick="fnGo('${formId}', '${page.nextBlockPage}')">
			<i class="bi bi-caret-right-fill"></i>
		</a>
	</li>
	<li class="page-item">
		<a class="page-link" href="javascript:void(0)" onclick="fnGo('${formId}', '${page.totalPage}')">
			<i class="bi bi-skip-forward-fill"></i>
		</a>
	</li>
</c:if>