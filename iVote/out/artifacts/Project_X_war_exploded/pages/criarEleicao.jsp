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
        width: 10%;
        border-radius: 50%;
    }

    .title{
        font-family:"Arial";
        font-size:30px;
    }

    .container {
        padding: 16px;
    }

    #face{
        background-color:#3b5998;

    }

    span.psw {
        float: right;
        padding-top: 16px;
    }

    /* Change styles for span and cancel button on extra small screens */
    @media screen and (max-width: 300px) {
        span.psw {
            display: block;
            float: none;
        }
        .cancelbtn {
            width: 100%;
        }
    }
</style>
<body>
<div class="imgcontainer">
    <img src="../assets/image.png" alt="Avatar" class="avatar">
</div>
<div class="container">
    <form action="criarEle.action" method="post">
        <label class="title"><b>Criar Eleição</b></label>
        <br/>
        <label><b><br/>Tipo de Eleição (Núcleo ou DG):</b></label>
        <s:textfield name="tipo" required="true"/>
        <label><b><br/>Titulo da Eleicao<br/></b></label>
        <s:textfield name="titulo" required="true" />
        <label><b><br/>Descricao:<br/></b></label>
        <s:textfield name="descricao" required="true" />
        <label><b><br/>Data de Inicio no formato "dd-mm-aaaa hh:mm" :</b></label>
        <s:textfield name="datai" required="true"/>
        <label><b><br/>Data de Final no formato "dd-mm-aaaa hh:mm" :</b></label>
        <s:textfield name="dataf" required="true"/>
        <label><b><br/>Departamento(Núcleo)<br/></b></label>
        <s:textfield name="dep"/>
        <button type="submit">Confirmar</button>
    </form>
    <form action="cancelar">
        <button type="submit">Cancelar</button>
    </form>
</div>
</body>
</html>

