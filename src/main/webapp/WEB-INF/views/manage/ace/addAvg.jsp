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
<lesson:page title="录入">

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

            <h3 class="header smaller lighter blue">录入
                <span class="hidden-sm hidden-xs btn-group pull-right">

                            </span>
            </h3>



            <div class="row">
                <form action="/manage/student/save_addAvg.do" method="get">
                    <c:if test="${subject.equals('')||subject==null}">
                        你还没有录入成绩
                    </c:if>
                <c:forEach var="item" items="${subject}">


                    <span>
                        ${item.name}:
                    </span>
                    <input type="text" name="avgNum">
                    <input type="hidden" name="name" value="${item.name}">

                </c:forEach>
                    <input type="hidden" value="${subject.get(0).studentId}" name="studentId">
                    <input type="submit">
                </form>
            </div><!-- /.row -->


        </div>





    </jsp:body>



</lesson:page>