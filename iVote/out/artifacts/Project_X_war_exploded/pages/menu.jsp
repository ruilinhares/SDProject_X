<%--
  Created by IntelliJ IDEA.
  User: tiago
  Date: 19-12-2017
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>   
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
        padding: 14px 20px;
        margin: 6px 0;
        border: none;
        cursor: pointer;
        width: 100%;
    }

    button:hover {
        opacity: 0.8;
    }


    label{
        font-family:"helvetica";
    }

    .cancelbtn {
        width: auto;
        padding: 10px 18px;
        background-color: #f44336;
    }

    .imgcontainer {
        text-align: center;
    }

    img.avatar {
        width: 20%;
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
<form>
    <div class="imgcontainer">
        <img src="../assets/image.png" alt="Avatar" class="avatar">
    </div>

    <h2>Consola de Administrador</h2>
    <div class="container">
        <form></form>
        <form action="button1.action">
            <button>1.Registar Pessoa</button>
        </form>
        <form action="button2.action">
            <button>2.Gerir Departamentos</button>
        </form>
        <form action="button3.action">
            <button>3.Criar Eleicao</button>
        </form>
        <form action="button4.action">
            <button>4.Consultar Eleicao</button>
        </form>
        <form action="button5.action">
            <button>5.Gerir Candidatos</button>
        </form>
        <form action="button6.action">
            <button>6.Gerir Mesas de Voto</button>
        </form>
        <form action="button7.action">
            <button>7.Alterar Eleicao</button>
        </form>
        <form action="button8.action">
            <button>8.Local de voto</button>
        </form>
        <form action="button9.action">
            <button>9.Estado das mesas de voto</button>
        </form>
        <form action="button10.action">
            <button>10.Consultar Eleicao Passada</button>
        </form>
        <form action="button11.action">
            <button>11.Associar Mesa</button>
        </form>
        
        <br><br>
        <form action="logout.action">
            <button>Logout</button>
        </form>

    </div>
</form>
</body>
</html>

</body>
</html>

