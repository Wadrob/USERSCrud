<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <a href='<c:url value="/user/list"/>' class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Lista użytkowników</a>
    </div>
    <table class="table">
        <tr>
            <td><b>ID</b></td>
            <td>${userShow.id}</td>
        </tr>
        <tr>
            <td><b>NAZWA UŻYTKOWNIKA</b></td>
            <td>${userShow.username}</td>
        </tr>
        <tr>
            <td><b>EMAIL</b></td>
            <td>${userShow.email}</td>
        </tr>
    </table>
<%@ include file="/footer.jsp" %>