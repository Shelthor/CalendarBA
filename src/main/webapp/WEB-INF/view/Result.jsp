<%@ include file="/WEB-INF/view/Header.jsp" %>


<div class="container">

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="/home">Home</a></li>
                    <li role="presentation"><a href="/events">Event</a></li>
                    <li role="presentation"><a href="/categorys">Category</a></li>
                    <li role="presentation" class="active"><a href="/reports">Report</a></li>
                    <li role="presentation"><a href="#">Import</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Included Events</div>

        <!-- Table -->
        <table class="table">
            <tr>
                <th class="col-md-3">Event ID</th>
                <th class="col-md-3">Event Name</th>
                <th class="col-md-3">Event Start</th>
                <th class="col-md-3">Event End</th>
            </tr>
            <c:forEach items="${eventList}" var="Event">
                <tr>
                    <td>${Event.eventId}</td>
                    <td>${Event.eventName}</td>
                    <td>${Event.eventStart}</td>
                    <td>${Event.eventEnd}</td>
                </tr>
            </c:forEach>
        </table>
        </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-4">
                Days: <b>${sumDays}</b>
            </div>
            <div class="col-md-4">
                Hours: <b>${sumHours}</b>
            </div>
            <div class="col-md-4">
                Min: <b>${sumMin}</b>
            </div>
        </div>
    </div>
    <td><a href="<c:url value='/reports/' />" >Back</a></td>

    </div>