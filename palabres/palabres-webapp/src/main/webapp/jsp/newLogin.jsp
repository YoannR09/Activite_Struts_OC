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
        .blocInfo{
            margin: 20px;
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

        <div class="card-header"><h3>Nouveau utilisateur</h3></div>
        <div class="card-body" id="mid">
            <s:form action="newUser">
                <s:textfield name="pseudo" label="Identifiant " class="blocInfo" />
                <s:textfield name="password" type="password" label="Mot de passe " class="blocInfo" />
                <s:submit id="btn" value="Ok" class="btn btn-info"/>
            </s:form>
        </div>
    </div>
</div>
</body>
</html>
