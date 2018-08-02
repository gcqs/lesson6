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
<lesson:page title="选课">

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

            <h3 class="header smaller lighter blue">选课
                <span class="hidden-sm hidden-xs btn-group pull-right">

                            </span>
            </h3>



            <div class="row">
                <h1>已选课程</h1>
                <c:forEach var="item" items="${subject}">
                    <span>
                        ${item.name}
                    </span>
                    <br/>
                </c:forEach>
                <hr/>

                <h1>未选课程</h1>
                <form action="/manage/student/save_addSubject.do" method="get">


                    <input type="hidden" value="${studentId}" name="studentId">
                <c:forEach var="item" items="${noSubject}">

                    <input type="checkbox" name="name" value="${item.name}">
                    <span>
                        ${item.name}:
                    </span><br/>

                </c:forEach>
                    <input type="submit">
                </form>
            </div><!-- /.row -->


        </div>





    </jsp:body>



</lesson:page>