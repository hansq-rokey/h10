<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String headType = request.getParameter("headType");
%>
<div style="display: none;">
	<form id="articleDetail" name="articleDetail" action="" method="post">
	<input type="hidden" name="headType" id="headType" value="${headType }"/><!-- 这个标题头用来区分详细页上面显示的内容 -->
	<button type="submit" id="detailButton">提交</button>
</form>
<form id="publishArticle" name="publishArticle" action="" method="get">
	<input type="hidden" name="headType" id="headType" value="${headType }"/><!-- 这个标题头用来区分详细页上面显示的内容 -->
		<button type="submit" id="publishButton">提交</button>
	</form>
</div>

