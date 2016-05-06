<%@ include file="/WEB-INF/view/Header.jsp" %>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">Category List</div>

        <!-- Table -->
        <table class="table">
            <tr>
                <th class="col-md-3">Category ID</th>
                <th class="col-md-3">Category Name</th>
                <th class="col-md-3">Privat</th>
                <th class="col-md-3">Action</th>
            </tr>
            <c:forEach items="${categorysList}" var="Category">
                <tr>
                    <td>${Category.categoryId}</td>
                    <td>${Category.categoryName}</td>
                    <td>${Category.categoryPrivat}</td>
                    <td><a href="<c:url value='/report/sum/${Category.categoryId}' />" >Sum up</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Events List</div>

        <!-- Table -->
        <table class="table">
            <tr>
                <th class="col-md-3">Event Name</th>
                <th class="col-md-3">Days</th>
                <th class="col-md-3">Hours</th>
                <th class="col-md-3">Min</th>
            </tr>
            <c:forEach items="${resultList}" var="resultObject">
                <tr>
                    <td>${resultObject[0]}</td>
                    <td>${resultObject[1]}</td>
                    <td>${resultObject[2]}</td>
                    <td>${resultObject[3]}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
