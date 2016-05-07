<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <script src="/resources/jquery-2.2.3.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/npm.js"></script>
    <script src="/resources/moment.js"></script>
    <script src="/resources/combodate.js"></script>

    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300' rel='stylesheet' type='text/css'>

    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/resources/css/custom.css" rel="stylesheet">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>

    $(function(){
        jQuery("#eventStart").combodate({
            maxYear: 2016
        });
    });
    $(function(){
        jQuery("#eventEnd").combodate({
            maxYear: 2016
        });
    });

    function sureOrNot(x) {
        if (confirm("Datensatz wirklich löschen?") == true) {
            window.location.replace(x);
        } else {
            x = "Löschvorgang abgebrochen";
        }

    }
</script>

<div class="container">
    <div class="row">
        <div class="jumbotron">
            <h1>Time Tracking</h1>
            <p>Project Failure 133%</p>
        </div>
    </div>
</div>




