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
    <form method="post" id="edit_form"></form>
    <table class="table">
        <tr>
            <th>NAZWA UŻYTKOWINKA</th>
            <th>EMAIL</th>
            <th>HASŁO</th>
        </tr>
        <tr>
            <td>
                <input name="firstname" type="text" placeholder="Nazwa" form="edit_form">
            </td>
            <td>
                <input name="email" type="text" placeholder="Email" form="edit_form">
            </td>
            <td>
                <input name="password" type="text" form="edit_form" placeholder="Hasło">
            </td>
            <td>
                <input type="submit" value="Dodaj" form="edit_form">
            </td>
        </tr>
    </table>
<%@ include file="/footer.jsp" %>