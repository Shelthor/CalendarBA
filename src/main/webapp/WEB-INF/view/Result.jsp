<%@ include file="/WEB-INF/view/Header.jsp" %>

<c:url var="Url" value="/reports/show" />
<div class="panel-heading">Catergory Sum</div>

<!-- Table -->
<table class="table">
    <tr>
        <th class="col-md-2">Event ID</th>
        <th class="col-md-3">Event Name</th>
        <th class="col-md-2">Event Start</th>
        <th class="col-md-3">Event End</th>
    </tr>
    <c:forEach items="${eventList}" var="Category">
        <tr>
            <td>${Event.eventId}</td>
            <td>${Event.eventName}</td>
            <td>${Event.eventStart}</td>
            <td>${Event.eventEnd}</td>
        </tr>
    </c:forEach>
    <tr>
        <td>Hours ${sumHours}</td>
        <td>Min ${sumMinutes}</td>
        <td>Sec ${sumSeconds}</td>
    </tr>
    <td><a href="<c:url value='/reports/' />" >Back</a></td>
</table>