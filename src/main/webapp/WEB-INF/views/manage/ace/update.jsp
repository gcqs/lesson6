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
						<div class="row">
							<div class="col-xs-12">

								<form id="form" class="form-horizontal" role="form" action="manage/student/save_update.do" enctype="multipart/form-data" method="post">
									<!--头像-->
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 上传头像 </label>

										<div class="col-sm-9">
											<input type="file" name="filename">
										</div>
									</div>
									<!--学号-->
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 学号 </label>

										<div class="col-sm-9">
											<input type="text" name="number" id="form-field-1" placeholder="请输入不重复的学号！" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<!--姓名-->
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right"  for="form-field-1"> 姓名 </label>

										<div class="col-sm-9">
											<input type="text" name="name" placeholder="请输入学生姓名！" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<!--班级-->
									<div class="form-group" >
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 班级 </label>
										<div class="col-sm-9">
											<select class="col-xs-10 col-sm-5" name="grade" style="width: 388px">
												<c:forEach var="item" items="${grade}">

													<option value="${item.gradeId}">${item.name}</option>

												</c:forEach>
											</select>
										</div>
									</div>
									<!--性别-->
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别 </label>

										<div class="radio">
											<label>
												<input name="sex" type="radio" class="ace" value="男"/>
												<span class="lbl">男</span>
											</label>
											<label>
												<input name="sex" type="radio" class="ace" value="女"/>
												<span class="lbl">女</span>
											</label>
										</div>
									</div>
									<!--出生日期-->
									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 出生日期 </label>

										<div class="col-sm-9" style="width: 500px">
											<input class="form-control date-picker" name="birthday" id="id-date-picker-1" type="text" data-date-format="yyyy-mm-dd" />
										</div>
									</div>
									<!--提交-->
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">

											<button class="btn btn-info" type="button" onclick="javascript:document.getElementById('form').submit()">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Submit
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset" onclick="javascript:window.location.href='manage/student/all.do'">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Reset
											</button>
										</div>
									</div>
									<div class="space-24"></div>
								</form>
							</div>
						</div>


					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		</div>
</jsp:body>


</lesson:page>
