<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<%@ page import ="java.util.ArrayList"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Administrador</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
        if(customerBean.getTipo().name().equals("Asistente")){
            mensaje="Esta pagina es exclusiva para usuarios administradores";
            paginaSiguiente="asistenteVista.jsp";
            %>
            <jsp:forward page="<%=paginaSiguiente%>">
            <jsp:param value="<%=mensaje%>" name="mensaje"/>
            </jsp:forward>
            <%
        }
        else{
    %>

    <body>
        <header>
        <h1>Página Administrador</h1>
        </header>

        <div>

            <ul>
                <%
                ArrayList<Integer> listaId=(ArrayList<Integer>)request.getAttribute("listaId");
                ArrayList<Integer> listaCompletas=(ArrayList<Integer>)request.getAttribute("listaCompletas");
                ArrayList<Integer> listaParciales=(ArrayList<Integer>)request.getAttribute("listaParciales");
                for(int i=0; i<listaId.size(); i++){
                %>
                    <li>Campamento <%=listaId.get(i)%>: <%=listaCompletas.get(i)%> inscripciones completas y 
                    <%=listaParciales.get(i)%> inscripciones parciales</li>
                <%
                }
                %>
            </ul>
        </div>

        <fieldset>
        <legend>Opciones del administrador</legend>
            <ul>
                <li>
                    <a href="/campamentos/mvc/vistas/nuevaActividadVista.jsp">
                        <button> Dar de alta una actividad </button>
                    </a>
                </li>
                <li>
                    <a href="/campamentos/mvc/vistas/nuevoMonitorVista.jsp">
                        <button> Dar de alta un monitor </button>
                    </a>
                </li>
                <li>
                    <a href="/campamentos/mvc/vistas/nuevoCampamentoVista.jsp">
                        <button> Dar de alta un campamento </button>
                    </a>
                </li>
                <li>
                    <a href="/campamentos/mvc/vistas/AsociarMonitor_Actividad.jsp">
                        <button> Vincular un monitor a una actividad </button>
                    </a>
                </li>
                <li>
                    <a href="/campamentos/mvc/vistas/AsociarMonitorVista.jsp">
                        <button> Vincular monitor campamento </button>
                    </a>
                </li>
                <li>
                    <a href="/campamentos/mvc/vistas/vinculaActividadVista.jsp">
                        <button> Vincular un actividad campamento </button>
                    </a>
                </li>
            </ul>
        </fieldset>

        <aside>
            <a href="/campamentos/mvc/controladores/modificarDatosControlador.jsp">
            <button> Modificar datos </button>
            </a>

            <a href="/campamentos/mvc/controladores/desconexionControlador.jsp">
            <button> Desconectar </button>
            </a>
        </aside>
        
    </body>

    <% }
    }
    else{
        mensaje="Necesita iniciar sesion para tener acceso";
        paginaSiguiente="../../index.jsp";
        %>
        <jsp:forward page="<%=paginaSiguiente%>">
        <jsp:param value="<%=mensaje%>" name="mensaje"/>
        </jsp:forward>
        <%
    }%>
</html>