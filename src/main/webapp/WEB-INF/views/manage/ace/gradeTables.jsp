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
<lesson:page title="班级信息">

    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="aceGrade"/>
                    </a>
                </li>

                <li>
                    <a href="manage/users.do">
                        <spring:message code="tablesGrade"/>
                    </a>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>



        <div class="page-content">
            <input type="hidden" id="id-of-user">

            <h3 class="header smaller lighter blue">班级管理
                <span class="hidden-sm hidden-xs btn-group pull-right">

                            </span>
            </h3>




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


                            <th >序号</th>
                            <th>班级名</th>
                            <th>人数</th>
                            <th class="hidden-480">平均分</th>
                            <th>
                                <form action="/manage/grade/add.do" id="form" method="get">
                                <span>班级名</span>
                                <input type="text" name="name">

                                <a style="cursor:pointer;" class="ui-sghref" href="javascript:document.getElementById('form').submit()">
                                    <span >添加</span>
                                </a>

                                </form>
                            </th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="item" items="${gradeAll.content}">

                            <tr>

                                <td>
                                    <a >${item.gradeId}</a>
                                </td>
                                <td>${item.name}</td>
                                <td class="hidden-480">${item.num}</td>
                                <td>${item.avgNum}</td>

                                <td width="20%">
                                    <div class="hidden-sm hidden-xs btn-group" style="text-align: center">

                                        <button class="btn btn-xs btn-danger" onclick="javascript:window.location.href='manage/grade/delete.do?${item.gradeId}'">
                                            <i class="ace-icon fa fa-trash-o bigger-120">删除</i>
                                        </button>


                                        <button class="btn btn-xs btn-info" onclick="javascript:window.location.href=''">
                                            <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                        </button>




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
                            <li class="paginate_button previous disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
                                <a href="#">Previous</a>
                            </li>
                            <li class="paginate_button active" aria-controls="dynamic-table" tabindex="0">
                                <a href="#">1</a>
                            </li>
                            <li class="paginate_button " aria-controls="dynamic-table" tabindex="0">
                                <a href="#">2</a>
                            </li>
                            <li class="paginate_button " aria-controls="dynamic-table" tabindex="0">
                                <a href="#">3</a>
                            </li>
                            <li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next">
                                <a href="#">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>


        </div>





    </jsp:body>



</lesson:page>