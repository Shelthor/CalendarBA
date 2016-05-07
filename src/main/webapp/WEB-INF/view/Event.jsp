<%@ include file="/WEB-INF/view/Header.jsp" %>
    <script>

        $(function(){
            jQuery("#eventStart").combodate();
        });
        $(function(){
            jQuery("#eventEnd").combodate();
        });

        function sureOrNot(x) {
            if (confirm("Datensatz wirklich löschen?") == true) {
                window.location.replace(x);
            } else {
                x = "Löschvorgang abgebrochen";
            }

        }

    </script>

<body>

<div class="container">

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="home">Home</a></li>
                    <li role="presentation" class="active"><a href="#">Event</a></li>
                    <li role="presentation"><a href="categorys">Category</a></li>
                    <li role="presentation"><a href="reports">Report</a></li>
                    <li role="presentation"><a href="#">Import</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-body">

        <h1>
            Add an Event
        </h1>


        <c:url var="addAction" value="/event/add" />
<c:if test="${!empty addError}">
    <spring:message text="${addError}" />
</c:if>
<c:url var="addAction" value="/event/add" />




        <form:form action="${addAction}" commandName="Event">
                    <div class="col-md-4 col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Event Name</h3>
                            </div>
                            <div class="panel-body">
                                <form:input path="eventName" cssStyle="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Event Start</h3>
                            </div>
                            <div class="panel-body">
                                <form:input path="eventStart" id="eventStart" data-format="DD.MM.YYYY HH:mm" data-template="DD / MM / YYYY HH : mm"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Event End</h3>
                            </div>
                            <div class="panel-body">
                                <form:input path="eventEnd" id="eventEnd" data-format="DD.MM.YYYY HH:mm" data-template="DD / MM / YYYY HH : mm"/>
                            </div>
                        </div>
                    </div>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Category Name (ID)</h3>
                        </div>
                        <div class="panel-body">
                            <form:input path="categoryId"/>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12">
                    <c:if test="${!empty Event.eventName}">
                        <input type="submit" value="<spring:message text='Edit Event'/>" style="width: 100%" />
                    </c:if>
                    <c:if test="${empty Event.eventName}">
                        <input type="submit" value="<spring:message text='Add Event'/>" style="width: 100%" />
                    </c:if>
                </div>

        </form:form>
        <br/>

            <p style="color: #ffffff">.</p>
            <div class="panel panel-default">
                <c:if test="${!empty listEvents}">
                <!-- Default panel contents -->
                <div class="panel-heading">Events List</div>

                <!-- Table -->
                <table class="table col-md-12">
                    <tr>
                        <th class="col-md-2">Event Name</th>
                        <th class="col-md-2">Start Date</th>
                        <th class="col-md-2">End Date</th>
                        <th class="col-md-2">Category ID</th>
                        <th class="col-md-2"></th>
                        <th class="col-md-2"></th>
                    </tr>
                    <c:forEach items="${listEvents}" var="Event">
                        <tr>
                            <td>${Event.eventName}</td>
                            <td>${Event.eventStart}</td>
                            <td>${Event.eventEnd}</td>
                            <td>${Event.categoryId}</td>
                            <td><a onclick="sureOrNot('<c:url value='/remove/${Event.eventId}' />');">Delete</a></td>
                            <td><a href="<c:url value='/edit/${Event.eventId}' />" >Edit</a></td>
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