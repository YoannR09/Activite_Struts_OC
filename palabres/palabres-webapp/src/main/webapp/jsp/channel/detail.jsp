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
            overflow-y: scroll;
        }
    </style>
</head>
<body>

<header>
    <%@ include file="/_include/header.jsp"%>
</header>

<div id="page">

    <div class="card text-white bg-dark mb-3" id="card">

        <div class="card-header">
            <div style="width: 100%; display: flex; justify-content: space-around">
                <h2><s:property value="channel.name" /></h2>
            </div>
        </div>
        <div class="card-body" id="mid" style="text-align: left">
                <div id="cadreMessage" style="margin: 20px;">
                    <div id="listMessage">

                    </div>
                </div>
        </div>

        <div class="card-footer bg-transparent" id="bot">
            <div id="addMessage" style="width: 50%;" >
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

        setInterval(refresh, 5000);

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

    function refresh() {
        var checkBox = document.getElementById("checkRefresh");

        if (checkBox.checked == true) {
            reloadListMessage();
        }
    }

    function reloadListMessage() {
        // URL de l'action AJAX
        var url = "<s:url action="ajax_getListMessage"/>";

        // Action AJAX en POST
        jQuery.post(
            url,
            function (data) {
                var $listMessage = jQuery("#listMessage");
                $('#messageAffichage').empty();
                $listMessage.empty();
                jQuery.each(data, function (key, val) {
                    $listMessage.append(
                        jQuery("<br /><span class='badge badge-info' style='padding :10px;margin-bottom: 15px;'>").append(val.author.pseudo)
                    );
                    $listMessage.append(
                        jQuery("<span class='badge badge-light' style='padding :10px;margin-bottom: 15px;'>")
                            .append(val.message)
                    );
                });
            })
            .fail(function () {
                alert("Erreur !!");
            });
    }

    function addMessage() {

        // récupère le message entré par l'utilisateur
        var contenuMessage = $("input[name=contenuMessage]").val();

        // URL de l'action AJAX
        var url = "<s:url action="ajax_newMessage"/>";

        // Paramètres de la requête AJAX
        var params = {
            contenuMessage: contenuMessage
        };

        // Action AJAX en POST
        jQuery.post(
            url,

            params,
            function (data) { // La méthode qui lit le résultat retourné à la suite de l'envoi de la requêt POST
                var $listMessage = jQuery("#listMessage"); // OFA : il faut qu'une balise html existe avec cet id="listMessage" pour savoir ou mettre la liste des mesages.

                $listMessage.empty(); // Oups pourquoi vide t'on le résultat obtenu ?

                $('#messageAffichage').empty();

                jQuery.each(data, function (key, val) {
                    $listMessage.append(
                        jQuery("<br /><span class='badge badge-info' style='padding :10px;margin-bottom: 15px;'>").append(val.author.pseudo)
                    );
                    $listMessage.append(
                        jQuery("<span class='badge badge-light' style='padding :10px;margin-bottom: 15px;'>").append(val.message)
                    );
                    console.log(val.message);
                });
            })
            .fail(function () {
                alert("Erreur");
            });

        $("input[name=contenuMessage]").val(""); //-- On vide le champ de saisie du nouveau message à chaque tour.
    }


</script>
</body>
</html>
