<%@ include file="/WEB-INF/view/Header.jsp" %>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
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
                                <spring:message text="Privat"/>
                            </form:label>
                        </td>
                        <td>
                            <form:radiobutton path="categoryPrivat" value="true" label="privat"/>
                            <form:radiobutton path="categoryPrivat" value="false" label="business"/>
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
            <c:if test="${!empty listCategorys}">


                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Category List</div>

                    <!-- Table -->
                    <table class="table">
                        <tr>
                            <th class="col-md-2">Category ID</th>
                            <th class="col-md-3">Category Name</th>
                            <th class="col-md-2">Privat</th>
                            <th class="col-md-3">Edit</th>
                            <th class="col-md-2">Delete</th>
                        </tr>
                        <c:forEach items="${listCategorys}" var="Category">
                            <tr>
                                <td>${Category.categoryId}</td>
                                <td>${Category.categoryName}</td>
                                <td>${Category.categoryPrivat}</td>
                                <td><a href="<c:url value='/category/edit/${Category.categoryId}' />" >Edit</a></td>
                                <td><a href="<c:url value='/category/remove/${Category.categoryId}' />" >Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>


</body>
</html>