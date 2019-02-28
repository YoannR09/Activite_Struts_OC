<%--
  Created by IntelliJ IDEA.
  User: El-ra
  Date: 05/02/2019
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/_include/head.jsp"%>
    <style type="text/css">
        #card{
            margin-top: 30px;
            width: 60%;
        }
        #cardDroite{
            margin-top: 30px;
            width: 25%;
            height: 5%;
        }
        ul{
            list-style:none;
            display: flex;
        }
        #page {
            display: flex;
            justify-content: space-around;
        }
        #gauche{
            display: flex;
            justify-content:space-around;
        }
        #droite{
            width: 100%;
            display: flex;
            justify-content: space-around;
        }
        #form{
            display:flex;
        }
        #message{
            width: 100%;
        }
        #btnR{
            margin: 10px;
        }
        #btn{
            margin: 5px;
        }

        #env
        {
            width: 100%;
        }
        #mid
        {
            height: 300px;
        }
    </style>
</head>
<body>

<header>
    <%@ include file="/_include/header.jsp"%>
</header>

<div id="page">

    <div class="card text-white bg-dark mb-3"
         id="card">

        <div class="card-header">
            <div style="width: 100%; display: flex; justify-content: space-around">
                <h2><s:property value="name" /></h2>
            </div>
        </div>
        <div class="card-body" id="mid" style="text-align: left">
            <s:actionerror/>
            <s:actionmessage/>
            <s:iterator value="listMessage">
                <div id="cadreMessage" style="margin: 20px;">
                    <span class="badge badge-info" style=" padding: 10px;" id="message"><em></em></span>
                </div>
            </s:iterator>
        </div>

        <div class="card-footer bg-transparent" id="bot">
            <div id="addMessage"style="width: 50%;" >
                    <s:textfield name="contenuMessage" class="form-control" placeholder="Ecrivez votre message..."/>
                    <button id="btn" onclick="addMessage()" class="btn btn-info">Envoyer</button>
            </div>
        </div>
        <button type="button" class="btn btn-info" id="slide" data-dismiss="modal"></button>
    </div>
    <div class="card text-white bg-dark mb-3"
         id="cardDroite">
        <div class="card-header">
            <div style="width: 100%; display: flex; justify-content: space-around">
                <div style="margin:18px;">
                    <input type="checkbox" class="form-check-input" id="checkRefresh">
                    <label class="form-check-label" for="checkRefresh">Auto-refresh</label>
                </div>
                <button id="btnR" onclick="reloadListMessage()" class="btn btn-info">Refresh</button>
            </div>
        </div>
        <div class="card-body" id="midBody">
            <button type="button" class="btn btn-info" id="env">Ecrivez votre message</button>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('#bot').hide();
        $('#slide').hide();
        $("#env").click(function() {
            $('#bot').slideDown(1500);
            $('#midBody').slideUp(1000);
            $('#slide').show();
        });
        $("#btn").click(function() {
            $('#bot').slideUp(1000);
            $('#midBody').slideDown(1500);
            $('#slide').hide();


        });
        $("#slide").click(function() {
            $('#bot').slideUp(1000);
            $('#midBody').slideDown(1500);
            $('#slide').hide();
        });

    });
    function reloadListMessage() {
        // URL de l'action AJAX
        var url = "<s:url action="ajax_getListMessage"/>";

        // Action AJAX en POST
        jQuery.post(
            url,
            function (data) {
                var $listMessage = jQuery("#listMessage");
                $listMessage.empty();
                jQuery.each(data, function (key, val) {
                    $listMessage.append(
                        jQuery($("<em>"))
                            .append(val.message)
                    );
                });
            })
            .fail(function () {
                alert("Erreur");
            });
    }
    function addMessage() {
        // URL de l'action AJAX
        var url = "<s:url action="ajax_newMessage"/>";

        // Action AJAX en POST
        jQuery.post(
            url,
            function (data) {
                var $listMessage = jQuery("#listMessage");
                $listMessage.empty();
                jQuery.each(data, function (key, val) {
                    $listMessage.append(
                        jQuery($("<em>"))
                            .append(val.message)
                    );
                });
            })
            .fail(function () {
                alert("Erreur");
            });
    }

</script>
</body>
</html>
