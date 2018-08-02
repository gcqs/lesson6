<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2018/7/26
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="学生信息">

    <jsp:body>
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
            </ul><!-- /.breadcrumb -->
        </div>



        <div class="page-content">
            <input type="hidden" id="id-of-user">

            <h3 class="header smaller lighter blue">学生管理
                <span class="hidden-sm hidden-xs btn-group pull-right">

                            </span>
            </h3>
            <div>
                <form action="/manage/student/find.do" method="get">
                    <div style="float: left">
                    <span>学号：</span>
                    <input type="text" name="num">
                    <span>姓名</span>
                    <input type="text" name="name">
                    <span>出身日期</span>
                    </div>
                        <div class="row"style="width: 30%;float: left">
                            <div class="col-xs-8 col-sm-11">
                                <div class="input-daterange input-group">
                                    <input type="text" class="input-sm form-control" name="start">
                                    <span class="input-group-addon">
																		<i class="fa fa-exchange"></i>
																	</span>

                                    <input type="text" class="input-sm form-control" name="end">
                                </div>
                            </div>
                        </div>





                    <input type="submit" value="查找">



                </form>


            </div>
            <div><br/></div>


            <!--<div class="row">
                <div class="col-xs-8 col-sm-11">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>

                        <input class="form-control" type="text" name="date-range-picker" id="id-date-range-picker-1">
                    </div>
                </div>
            </div>-->


            <div class="row">
                <div class="col-xs-12">
                    <table id="simple-table" class="table  table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" />
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="detail-col">详细</th>

                            <th >序号</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th class="hidden-480">性别</th>

                            <th>
                                <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
                                出生日期
                            </th>

                            <th>
                                <a style="cursor:pointer;" class="ui-sghref" href="/manage/student/add.do">
                                    <div class="ui-pg-div" style="text-align: center"><span >➕新建</span></div>
                                </a>
                            </th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="item" items="${studentAll.content}">

                        <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" />
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td class="center">
                                <div class="action-buttons">
                                    <a href="#" class="green bigger-140 show-details-btn" title="Show Details">
                                        <i class="ace-icon fa fa-angle-double-down"></i>
                                        <span class="sr-only">Details</span>
                                    </a>
                                </div>
                            </td>

                            <td>
                                <a href="#">${item.studentId}</a>
                            </td>
                            <td>${item.num}</td>
                            <td class="hidden-480">${item.name}</td>
                            <td>${item.sex}</td>

                            <td class="hidden-480">
                                <span class="label label-sm label-warning">${item.date}</span>
                            </td>

                            <td width="20%">
                                <div class="hidden-sm hidden-xs btn-group" style="text-align: center">
                                    <button class="btn btn-xs btn-success" onclick="javascript:window.location.href='manage/student/addAvg.do?studentId=${item.studentId}'">
                                        <i class="ace-icon fa fa-check bigger-120">录入</i>
                                    </button>

                                    <button class="btn btn-xs btn-warning" onclick="javascript:window.location.href='manage/student/addSubject.do?studentId=${item.studentId}'">
                                        <i class="ace-icon fa fa-flag bigger-120">选课</i>
                                    </button>

                                    <button class="btn btn-xs btn-info" onclick="javascript:window.location.href='manage/student/update.do?studentId=${item.studentId}'">
                                        <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                    </button>

                                    <button class="btn btn-xs btn-danger" onclick="javascript:window.location.href='manage/student/delete.do?studentId=${item.studentId}&gradeId=${item.gradeId}'">
                                        <i class="ace-icon fa fa-trash-o bigger-120">删除</i>
                                    </button>


                                </div>

                            </td>
                        </tr>

                        <tr class="detail-row">
                            <td colspan="8">
                                <div class="table-detail">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-2">
                                            <div class="text-center">
                                                <img height="150" class="thumbnail inline no-margin-bottom" alt="Domain Owner's Avatar" src="${item.img}" />
                                                <br />
                                                <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                                    <div class="inline position-relative">
                                                        <a class="user-title-label" href="#">
                                                            <i class="ace-icon fa fa-circle light-green"></i>
                                                            &nbsp;
                                                            <span class="white">${itme.name}</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xs-12 col-sm-7">
                                            <div class="space visible-xs"></div>

                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 姓名 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.name}</span>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 平均分 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.avgNum}</span>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 性别 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.sex}</span>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 出生日期 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.date}</span>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 所在班级 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.gradeId}</span>
                                                    </div>
                                                </div>

                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> 已选科目数 </div>

                                                    <div class="profile-info-value">
                                                        <span>${item.subjectNum}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </td>
                        </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div><!-- /.row -->

            <div class="row">
                <div class="col-xs-6">
                    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite"></div>
                </div>
                <div class="col-xs-6">
                    <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
                        <ul class="pagination">

                            <c:if test="${studentAll.number!=0}">
                            <li class="paginate_button previous disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
                                <a href="/manage/student/${pagename}.do?page=${studentAll.number}">Previous</a>
                            </li>


                            <li class="paginate_button active" aria-controls="dynamic-table" tabindex="0">
                                <a href="#">${studentAll.number}</a>
                            </li>
                            </c:if>
                            <li class="paginate_button " aria-controls="dynamic-table" tabindex="0">
                                <a href="">${studentAll.number+1}</a>
                            </li>
                            <c:if test="${studentAll.number+1!=studentAll.totalPages}">
                            <li class="paginate_button " aria-controls="dynamic-table" tabindex="0">
                                <a href="/manage/student/${pagename}.do?page=${studentAll.number+2}">${studentAll.number+2}</a>
                            </li>
                            <li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next">
                                <a href="/manage/student/${pagename}.do?page=${studentAll.number+2}">Next</a>
                            </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>


        </div>






    </jsp:body>



</lesson:page>