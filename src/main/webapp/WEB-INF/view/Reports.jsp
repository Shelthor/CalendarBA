<%@ include file="/WEB-INF/view/Header.jsp" %>

    <div class="panel-heading">Category List</div>

    <!-- Table -->
    <table class="table">
        <tr>
            <th class="col-md-2">Category ID</th>
            <th class="col-md-3">Category Name</th>
            <th class="col-md-2">Privat</th>
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