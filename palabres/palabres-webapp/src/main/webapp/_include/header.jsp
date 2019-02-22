<%--
  Created by IntelliJ IDEA.
  User: El-ra
  Date: 05/02/2019
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <s:a action="home" class="navbar-brand">Palabres</s:a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">  <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <s:a action="channel_list" class="nav-link">Liste des channels</s:a>
            </li>
            <li class="nav-item">
             <s:a action="channel_new" class="nav-link">Nouveau channel</s:a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" >
            <s:if test="#session.user">
               <span id="loginText"> Utilisateur :
                <s:property value="#session.user.pseudo" /></span>
                <s:a action="logout" class="nav-link">Deconnexion</s:a>
            </s:if>
            <s:else>
                <s:a action="login" class="nav-link">Connexion</s:a>
            </s:else>
        </form>
    </div>
</nav>