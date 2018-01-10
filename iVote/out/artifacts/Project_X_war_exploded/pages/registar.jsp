<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20/12/2017
  Time: 17:03
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
    <form action="registar">
        <label><b>Registar Utilizador</b></label>
        <br/>
        <label><b><br/>Numero da UC:</b></label>
        <s:textfield name="uc" />
        <label><b>Nome:</b></label>
        <s:textfield name="nome" />
        <label><b>Numero do Cartao de Cidadao:</b></label>
        <s:textfield name="cc" />
        <label><b>Validade do CC:</b></label>
        <s:textfield name="validade" />
        <label><b>Password:</b></label>
        <s:textfield name="pass" type="password"/>
        <label><b>Telemovel:</b></label>
        <s:textfield name="telemovel" />
        <label><b>Morada:</b></label>
        <s:textfield name="morada" />
        <label><b>Departamento:</b></label>
        <s:textfield name="dep" />
        <label><b>Tipo (Estudante,Docente ou Funcionario):</b></label>
        <s:textfield name="tipo" />
        <button type="submit">Registar</button>
    </form>
    <form action="cancelar">
        <button>Cancelar</button>
    </form>
</div>
</body>
</html>

