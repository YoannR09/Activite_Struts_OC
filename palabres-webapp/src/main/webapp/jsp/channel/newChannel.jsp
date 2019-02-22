<%--
  Created by IntelliJ IDEA.
  User: El-ra
  Date: 12/02/2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
  --%>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/_include/head.jsp" %>
    <style type="text/css">


        #btn
        {
            margin: 10px;
        }
        #card
        {
            margin-top: 30px;
        }

        #mid {
            display: flex;
            justify-content: space-around;
            text-align: center;
        }
        #page {
            display: flex;
            justify-content: space-around;
        }
    </style>
</head>

<body>

<%@ include file="/_include/header.jsp" %>
<s:actionerror/>
<s:actionmessage/>
<div id="page">
    <div class="card text-white bg-dark mb-3" style="max-width: 25rem;"
         id="card">

        <div class="card-header"><h3>Nouveau channel</h3></div>
        <div class="card-body" id="mid">
            <s:form action="channel_new">
                <s:textfield name="channel.name" label="Nom du channel " />
                <s:submit id="btn" value="OK" class="btn btn-info"/>
            </s:form>
        </div>
    </div>
</div>
</body>
</html>
