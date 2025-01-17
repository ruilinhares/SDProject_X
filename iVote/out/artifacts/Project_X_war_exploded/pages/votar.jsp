<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20/12/2017
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>
    form {
        text-align:center;
        border: 3px solid #f1f1f1;
        width:50%;
        margin:0 auto;
    }

    input[type=text], input[type=password] {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    button {
        background-color: #03ad80;
        color: white;
        font-family:helvetica;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
    }


    button:hover {
        opacity: 0.8;
    }


    label{
        font-family:helvetica;
    }

    .title{
        text-align:center;
        font-size: 40px;
    }
	.cancelbtn {
        width: auto;
        padding: 10px 18px;
        background-color: #f44336;
    }
    
    .imgcontainer {
        text-align: center;
        margin: 24px 0 12px 0;
    }

    img.avatar {
        width: 20%;
    }

    .container {
        padding: 16px;
    }
</style>
<body>
<div class="imgcontainer">
    <img src="../assets/image.png" alt="Avatar" class="avatar">
</div>
<div class="container">

    <form action="listareleicoes.action" method="post">
        <label class="title"><b>Votar</b></label>
        <br/>
        <label><b><br/>Lista de Eleicões disponiveis:<br/></b></label>
        <s:textarea value="%{listaeleicoes}" cols="50" rows="30" disabled="true"/>
        <br/>
        <button type="submit">Ver Eleições</button>
    </form>
    <form action="listarcandidatos.action" method="post">
        <label><b>Insira o título da eleição:</b></label>
        <s:textfield name="eleicao" label="Nome da Eleição"/>
        <br>
        <label><b>Lista de Candidatos</b></label>
        <br>
        <s:textarea value="%{listacandidatos}" cols="50" rows="30" disabled="true"/>
        <br>
        <button type="submit">Ver Listas Candidatas</button>

    </form>
    <form action="votar.action">
        <label><b>Insira o título da eleição:</b></label>
        <s:textfield name="eleicao" required="true"/>
        <label><b>Insira o nome da lista:</b></label>
        <s:textfield name="lista" required="true"/>
        <button type="submit">Vota</button>
    </form>
    <form action="logout.action">
        <button>Logout</button>
    </form>
</div>
</body>
</html>


