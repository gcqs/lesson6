<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="修改信息">
	<jsp:body>
		<div class="no-skin">
			<div class="main-container ace-save-state" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="welcome.do">
									<spring:message code="ace"/>
								</a>
							</li>

							<li>
								<a href="manage/users.do">
									<spring:message code="tables"/>
								</a>
							</li>
							<li>
								修改
							</li>
						</ul><!-- /.breadcrumb -->
					</div>

					<div class="page-content">
						<form action="/manage/grade/save_update.do" method="get">
						<span>班级名</span>
						<input type="text" name="name">
							<span>平均分</span>
							<input type="text" name="avgNum">
							<span>人数</span>
							<input type="text" name="num">
							<input type="hidden" name="gradeId" value="${id}">

							<br/>
							<input type="submit">

						</form>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		</div>
</jsp:body>


</lesson:page>
