<%-- 
    Document   : list
    Created on : Aug 21, 2022, 4:48:03 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
        <title>Courses</title>
        <link href="<c:url value="/css/site.css"/>" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="<c:url value="/css/stylecategory.css"/>" rel="stylesheet">
        <style>
            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border-radius: 5px;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
            td {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">

                <!--            <div class="col-md-12 text-center">
                                <br/>
                                <form action="MainController">
                                    Search Value:
                                    <input type="text" name="lastSearchValue" value=""/>
                                    <input type="submit" value="Search" name="btAction"/><br>
                                    <a href="bookStore.html">Shopping Book</a>
                                </form>
                                <hr/>
                            </div>-->
                <c:if test="${list != null}">
                    <table class="table table-hover table-condensed table-bordered">
                        <thead class="active">
                            <tr>
                                <th scope="col" style=" color: white" class="text-center">No.</th>
                                <!--<th scope="col">Id</th>-->
                                <th scope="col" style="color: white" class="text-center">Course</th>
                                <th scope="col" style="color: white" class="text-center">Image</th>
                                <th scope="col" style="color: white" class="text-center">Fee</th>
                                <th scope="col" style="color: white" class="text-center">Type</th>
                                <th scope="col" style="color: white" class="text-center">Begin date</th>
                                <th scope="col" style="color: white" class="text-center">End date</th>
                                    <c:if test="${isAdmin}">
                                    <th scope="col" style="color: white" class="text-center">Last Update date</th>
                                    <th scope="col" style="color: white" class="text-center">Last Update user</th>
                                    </c:if>
                                <th scope="col" style="color: white" class="text-center">Register</th>
                                <!--<th scope="col">Modify</th>-->
                            </tr>
                        </thead>
                        <tbody>
                            <%--<c:forEach var="course" items="${list}" varStatus="loop">--%>
                            <c:set var="count" value="${20*(page-1)+1}"/>
                            <c:set var="paging" value="0"/>
                            <c:forEach var="course" items="${list}">
                                <c:set var="paging" value="${paging+1}"/>
                            </c:forEach>
                            <c:forEach var="course" items="${list}"  begin="${20*(page-1)}" end="${20*page - 1}">
                                <tr>
                                    <th scope="row">${count}</th>
                                        <c:set var="count" value="${count+1}"/>
                                        <c:choose>
                                            <c:when test="${isAdmin}">
                                    <form action="<c:url value="/course/update.do"/>" method="">
                                        <!--<td>Mark</td>-->
                                        <td class="text-center" text-align=" center"><input type="text" name="newName" value="${course.couName}" /></td>
                                        <input type="hidden" name="couId" value="${course.couId}"/>
                                        <input type="hidden" name="category" value="${category}"/>
                                        <input type="hidden" name="page" value="${page}"/>
                                        <td><img width="256px" height="128px" src="<c:url value="/images/${course.image}"/>" alt="${course.couName}" /></td>
                                        <td>$ <input type="number" name="fee" value="${course.fee}" /></td>
                                        <td><select name="newCaId">
                                                <option<c:if test="${course.caId==1}"> selected="selected" </c:if> value="1">Piano</option>
                                                <option<c:if test="${course.caId==2}"> selected="selected" </c:if> value="2">Guitar</option>
                                                <option<c:if test="${course.caId==3}"> selected="selected" </c:if> value="3">Drawing</option>

                                            </td>
                                            <td style="text-align: center;" ><input type="date" name="newStartDate" value="${course.startDate}"/></td>
                                        <td style="text-align: center;" ><input type="date" name="newEndDate" value="${course.endDate}" /></td>
                                        <td style="text-align: center;" ><fmt:formatDate value="${course.lastUpdateDate}" pattern="dd-MM-yyyy" /></td>
                                        <td class="text-center" text-align=" center">${course.lastUpdateUser}</td>
                                        <td>
                                            <input type="submit" value="Update"><br/><br/>
                                    </form>
                                    <a href="<c:url value="/course/inactive.do?couId=${course.couId}}&category=${category}&page=${page}"/>" style="color: black"><button>Deactive</button></a>
                                    </td>

                                    <!--                                        <td><button>Register</button></td>-->
                                </c:when>
                                <c:otherwise>

                                    <td class="text-center" text-align=" center">${course.couName}</td>
                                    <td><img width="256px" height="128px" src="<c:url value="/images/${course.image}"/>" alt="${course.couName}" /></td>
                                    <td style="text-align: center;"><fmt:formatNumber value="${course.fee}" pattern="$ #,##0.00" /></td>
                                    <c:if test="${course.caId==1}">
                                        <td>Piano</td>
                                    </c:if>
                                    <c:if test="${course.caId==2}">
                                        <td>Guitar</td>
                                    </c:if>
                                    <c:if test="${course.caId==3}">
                                        <td>Drawing</td>
                                    </c:if>
                                    <td style="text-align: center;" ><fmt:formatDate value="${course.startDate}" pattern="dd-MM-yyyy" /></td>
                                    <td style="text-align: center;" ><fmt:formatDate value="${course.endDate}" pattern="dd-MM-yyyy" /></td>

                                    <td><button>Register</button></td>
                                </c:otherwise>
                            </c:choose>

                            <!--                    <td>
                                                    <button>Update</button><br/><br/>
                                                    <button>Delete</button>
                                                </td>-->
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <c:set var="next" value="1"/>
                        <c:if test="${page>1}" >
                            <a href="<c:url value="/course/${category}.do?page=${page-1}"/>">&laquo;</a>
                        </c:if>
                        <a <c:if test="${page==1}" >class="active"</c:if> href="<c:url value="/course/${category}.do?page=1"/>">1</a>
                        <c:if test="${paging>20}">
                            <c:set var="next" value="2"/>
                            <a <c:if test="${page==2}" >class="active"</c:if> href="<c:url value="/course/${category}.do?page=2"/>">2</a>
                        </c:if>
                        <c:if test="${paging>40}">
                            <c:set var="next" value="3"/>
                            <a <c:if test="${page==3}" >class="active"</c:if> href="<c:url value="/course/${category}.do?page=3"/>">3</a>
                        </c:if>
                        <%--<c:if test="${paging>20}">--%>
            <!--            <a <c:if test="${page==4}" >class="active"</c:if> href="#">4</a>
                        <a <c:if test="${page==5}" >class="active"</c:if> href="#">5</a>-->
                            <!--                <a href="#">6</a>-->
                        <c:if test="${next!=page}">
                            <a href="<c:url value="/course/${category}.do?page=${page+1}"/>">&raquo;</a>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${list == null}">
                    <br/><br/><br/> <h3 style="color: red" >Not Founnd !!!</h3>
                </c:if>
            </div>
        </div>

    </body>
</html>
