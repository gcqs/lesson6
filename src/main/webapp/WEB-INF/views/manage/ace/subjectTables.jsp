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
<lesson:page title="学科信息">

    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="aceSubject"/>
                    </a>
                </li>

                <li>
                    <a href="manage/users.do">
                        <spring:message code="tablesSubject"/>
                    </a>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>



        <div class="page-content">
            <input type="hidden" id="id-of-user">

            <h3 class="header smaller lighter blue">学科管理
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
                            <th>学科名</th>
                            <th>选修人数</th>
                            <th class="hidden-480">平均分</th>
                            <th>
                                <form action="/manage/subject/add.do" id="form" method="get">
                                    <span>学科名</span>
                                    <input type="text" name="name">

                                    <a style="cursor:pointer;" class="ui-sghref" href="javascript:document.getElementById('form').submit()">
                                        <span >添加</span>
                                    </a>

                                </form>
                            </th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="item" items="${subjectAll}">

                        <tr>

                            <td>
                                <a >${item.subjectId}</a>
                            </td>
                            <td>${item.name}</td>
                            <td class="hidden-480">${item.count}</td>
                            <td>${item.avg}</td>

                            <td width="20%">
                                <div class="hidden-sm hidden-xs btn-group" style="text-align: center">

                                    <button class="btn btn-xs btn-danger" onclick="javascript:window.location.href='manage/subject/delete.do?${item.subjectId}'">
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



        </div>





    </jsp:body>



</lesson:page>