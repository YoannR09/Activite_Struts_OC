<%--
  Created by IntelliJ IDEA.
  User: El-ra
  Date: 05/02/2019
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/_include/head.jsp"%>
    <style type="text/css">
        #bloc
        {
            margin: 20px;
            text-align: center;
        }

        #page {
            display: flex;
            justify-content: space-around;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="/_include/header.jsp"%>
</header>
<div id="page">


    <ul>
        <s:iterator value="listChannel">
            <li>
                <div class="card text-white bg-dark mb-3" style="max-width: 18rem;"  id="bloc">
                    <div class="card-header"><s:property value="name"/></div>
                    <div class="card-body">
                        <s:a action="channel_detail" class="btn btn-info"><s:param name="channel.name" value="name" /> Voir </s:a>
                    </div>
                </div>

            </li>
        </s:iterator>
    </ul>
</div>
</body>
</html>
