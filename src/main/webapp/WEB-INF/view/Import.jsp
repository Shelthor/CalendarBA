<%@ include file="/WEB-INF/view/Header.jsp" %>


<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">

        <h1>
            Import Calendar XML
        </h1>

        <c:url var="importAction" value="/import/do" />
        <form:form action="${importAction}" commandName="Import">
                <div class="col-sm-12">
                        <input type="submit" value="<spring:message text='do it'/>" style="width: 100%" />
                </div>
        </form:form>
       </div>

        <div class="panel panel-default">
            <c:if test="${!empty listEvents}">
                <!-- Default panel contents -->
        <div class="panel-heading">Events List</div>

        <!-- Table -->
        <table class="table col-md-12">
            <tr>
                <th class="col-md-2">Event ID</th>
                <th class="col-md-2">Event Name</th>
                <th class="col-md-2">Start Date</th>
                <th class="col-md-2">End Date</th>
                <th class="col-md-2">Edit</th>
                <th class="col-md-2">Delete</th>
            </tr>
            <c:forEach items="${eventList}" var="Event">
                <tr>
                    <td>${Event.eventId}</td>
                    <td>${Event.eventName}</td>
                    <td>${Event.eventStart}</td>
                    <td>${Event.eventEnd}</td>
                    <td><a href="<c:url value='/edit/${Event.eventId}' />" >Edit</a></td>
                    <td><a href="<c:url value='/remove/${Event.eventId}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        </div>
    </div>
    </div>
</div>
</body>
</html>