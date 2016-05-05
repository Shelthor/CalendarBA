<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Category Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a Category
</h1>

<c:url var="addAction" value="/category/add" ></c:url>

<form:form action="${addAction}" commandName="Category">
    <table>
        <c:if test="${!empty Category.categoryName}">
            <tr>
                <td>
                    <form:label path="categoryId">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="categoryId" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="categoryId" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="categoryName">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="categoryName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="categoryPrivat">
                    <spring:message text="Start Date"/>
                </form:label>
            </td>
            <td>
                <form:radiobutton path="categoryPrivat"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty Category.categoryName}">
                    <input type="submit"
                           value="<spring:message text="Edit Category"/>" />
                </c:if>
                <c:if test="${empty Category.categoryName}">
                    <input type="submit"
                           value="<spring:message text="Add Category"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Category List</h3>
<c:if test="${!empty listCategorys}">
    <table class="tg">
        <tr>
            <th width="80">Category ID</th>
            <th width="120">Category Name</th>
            <<th width="120">Privat</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listCategorys}" var="Category">
            <tr>
                <td>${Category.categoryId}</td>
                <td>${Category.categoryName}</td>
                <td>${Category.categoryPrivat}</td>
                <td><a href="<c:url value='/edit/${Category.categoryId}' />" >Edit</a></td>
                <td><a href="<c:url value='/remove/${Category.categoryId}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>