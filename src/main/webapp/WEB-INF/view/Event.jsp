<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Event Page</title>
    <script src="<c:url value="/resources/jquery-2.2.3.min.js" />"></script>
    <script src="<c:url value="/resources/moment.js" />"></script>
    <script src="<c:url value="/resources/combodate.js" />"></script>
    <script>
        $(function(){
            jQuery("#eventStart").combodate();
        });
        $(function(){
            jQuery("#eventEnd").combodate();
        });
    </script>
</head>
<body>



<h1>
    Add a Event
</h1>

<c:url var="addAction" value="/event/add" ></c:url>

<form:form action="${addAction}" commandName="Event">
    <table>
        <c:if test="${!empty Event.eventName}">
            <tr>
                <td>
                    <form:label path="eventId">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="eventId" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="eventId" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="eventName">
                    <spring:message text="eventName"/>
                </form:label>
            </td>
            <td>
                <form:input path="eventName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="eventStart">
                    <spring:message text="Start Date"/>
                </form:label>
            </td>
            <td>
                    <%--  <form:input type="text" path="eventStart"/>
                      --%>
                      <form:input type="text" path="eventStart" id="eventStart" data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH : mm" value=""/>

            </td>
        </tr>
        <tr>
            <td>
                <form:label path="eventEnd">
                    <spring:message text="End Date"/>
                </form:label>
            </td>
            <td>
                    <%--
                    <form:input type="text" path="eventEnd"/>
                     --%>
                  <form:input type="text" path="eventEnd" id="eventEnd" data-format="DD-MM-YYYY HH:mm" data-template="DD / MM / YYYY HH : mm" value=""/>

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty Event.eventName}">
                    <input type="submit"
                           value="<spring:message text="Edit Event"/>" />
                </c:if>
                <c:if test="${empty Event.eventName}">
                    <input type="submit"
                           value="<spring:message text="Add Event"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Events List</h3>
<c:if test="${!empty listEvents}">
    <table class="tg">
        <tr>
            <th width="80">Event ID</th>
            <th width="120">Event Name</th>
            <th width="120">Start Date</th>
            <th width="120">End Date</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listEvents}" var="Event">
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
</body>
</html>