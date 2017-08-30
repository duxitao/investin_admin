<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="pagebar" class="pagination"
	style="margin-left: auto; margin-right: auto">
	
		<input id="pageNum" name="pageNum" type="hidden" value="${pageNum}">
		<input id="totalPageNum" type="hidden" value="${totalPageNum}">
		<ul>
			<li>第<span id="lbl_currentPage">${pageNum}</span>页/共<span
				id="lbl_totalPage">${totalPageNum}</span>页
			</li>
			<li>
				<button type="button"
					<c:if test="${pageNum=='1'}">disabled="disabled" </c:if>
					class="button button-small" id="btn_first">首页</button>
			</li>
			<li>
				<button type="button"
					<c:if test="${pageNum=='1'}">disabled="disabled" </c:if>
					class="button button-small" id="btn_pre">上一页</button>
			</li>
			<li>
				<button type="button"
					<c:if test="${totalPageNum==pageNum}">disabled="disabled" </c:if>
					class="button button-small" id="btn_next">下一页</button>
			</li>
			<li>
				<button type="button"
					<c:if test="${totalPageNum==pageNum}">disabled="disabled" </c:if>
					class="button button-small" id="btn_end">尾页</button>
			</li>
			<li>到第【<input class="span1 span-width control-text"
				id="txt_pageNum" />】页
				<button type="button"
					<c:if test="${totalPageNum=='1'}">disabled="disabled" </c:if>
					class="button button-small" id="btn_go">跳转</button></li>
		</ul>

</div>

<script type="text/javascript">
	$("#btn_first").click(function() {
		$("#pageNum").val("1");
		$("#formID").submit();
	});
	$("#btn_pre").click(function() {
		var pageNum = $("#pageNum").val() - 1;
		$("#pageNum").val(pageNum);
		$("#formID").submit();
	});
	$("#btn_next").click(function() {
		var pageNum = $("#pageNum").val() - 1 + 2;
		$("#pageNum").val(pageNum);
		$("#formID").submit();
	});
	$("#btn_end").click(function() {
		var pageNum = $("#totalPageNum").val();
		$("#pageNum").val(pageNum);
		$("#formID").submit();
	});
	$("#btn_go").click(function() {
		var gotoNum = $("#txt_pageNum").val();
		var pageNum = $("#pageNum").val();
		var totalPageNum = $("#totalPageNum").val();
		if (gotoNum == pageNum || gotoNum<1||gotoNum>totalPageNum)
			return false;
		$("#pageNum").val(gotoNum);
		$("#formID").submit();
	});
</script>