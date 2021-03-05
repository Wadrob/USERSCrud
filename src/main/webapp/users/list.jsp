<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/header.jsp" %>
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    <a href='<c:url value="/user/add"/>' class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
                </div>
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>NAZWA UŻYTKOWNIKA</th>
                        <th>EMAIL</th>
                        <th>AKCJA</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>
                            <a href='<c:url value="/user/delete?id=${user.id}"/>'>USUŃ</a>
                            <a href='<c:url value="/user/edit?id=${user.id}"/>'>EDYTUJ</a>
                            <a href='<c:url value="/user/show?id=${user.id}"/>'>POKAŻ</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
<%@ include file="/footer.jsp" %>