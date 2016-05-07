<%@ include file="/WEB-INF/view/Header.jsp" %>
<body>

<div class="container">

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="home">Home</a></li>
                    <li role="presentation"><a href="events">Event</a></li>
                    <li role="presentation" class="active"><a href="#">Category</a></li>
                    <li role="presentation"><a href="reports">Report</a></li>
                    <li role="presentation"><a href="#">Import</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-body">
            <h1>
                Add a Category
            </h1>

            <c:url var="addAction" value="/category/add" ></c:url>

            <form:form action="${addAction}" commandName="Category">
                    <c:if test="${!empty Category.categoryName}">
                        <form:label path="categoryId">
                            <spring:message text="ID"/>
                        </form:label>

                        <form:input path="categoryId" readonly="true" size="8"  disabled="true" />
                        <form:hidden path="categoryId" />
                    </c:if>
                    <br/>
                    <div class="col-md-12">
                        <form:label path="categoryName">
                            <spring:message text="Name"/>
                        </form:label>
                    </div>

                    <form:input path="categoryName" style="width: 100%"/>
                    <br/><br/>

                    <span class="input-group-addon">
                        <form:radiobutton path="categoryPrivat" value="true" checked="checked"/>
                        <p>privat</p>
                    </span>
                    <span class="input-group-addon">
                        <form:radiobutton path="categoryPrivat" value="false"/>
                        <p>business</p>
                    </span>
                    <br/>
                    <c:if test="${!empty Category.categoryName}">
                        <input type="submit" value="<spring:message text="Edit Category"/>" style="width: 100%"/>
                    </c:if>
                    <c:if test="${empty Category.categoryName}">
                        <input type="submit" value="<spring:message text="Add Category"/>" style="width:100%" />
                    </c:if>
            </form:form>

            <c:if test="${!empty listCategorys}">
                <div>
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">Category List</div>

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th class="col-md-2">Category ID</th>
                                <th class="col-md-3">Category Name</th>
                                <th class="col-md-2">Privat</th>
                                <th class="col-md-3"></th>
                                <th class="col-md-2"></th>
                            </tr>
                            <c:forEach items="${listCategorys}" var="Category">
                                <tr>
                                    <td>${Category.categoryId}</td>
                                    <td>${Category.categoryName}</td>
                                    <td>${Category.categoryPrivat}</td>
                                    <td><a href="<c:url value='/category/edit/${Category.categoryId}' />" >Edit</a></td>

                                    <td><a onclick="sureOrNot('<c:url value='/category/remove/${Category.categoryId}' />');">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>

        </div>

        </div>
    </div>
</div>


</body>
</html>