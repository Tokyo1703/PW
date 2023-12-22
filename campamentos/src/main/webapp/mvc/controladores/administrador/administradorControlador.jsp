<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="src.Negocio.DTO.CampamentoDTO, src.Datos.DAO.InscripcionDAO, src.Negocio.gestorCampamentos, java.util.ArrayList, java.util.Properties" %>
<%@ page errorPage="../vistas/error.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
        if(customerBean.getTipo().name().equals("Asistente")){
            mensaje="No tiene acceso a esta pagina";
            paginaSiguiente="asistenteVista.jsp";
            %>
            <jsp:forward page="<%=paginaSiguiente%>">
            <jsp:param value="<%=mensaje%>" name="mensaje"/>
            </jsp:forward>
            <%
        }
        else{
            paginaSiguiente="../vistas/administradorVista.jsp";

            String fileSQL = application.getInitParameter("sql");
            String fileCONFIG = application.getInitParameter("config");
            java.io.InputStream myIOsql = application.getResourceAsStream(fileSQL);
            java.io.InputStream myIOconfig = application.getResourceAsStream(fileCONFIG);
            Properties sqlProperties = new java.util.Properties();
            Properties configProperties = new java.util.Properties();
            sqlProperties.load(myIOsql);
            configProperties.load(myIOconfig);

            gestorCampamentos gestor = new gestorCampamentos(sqlProperties, configProperties);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(sqlProperties, configProperties);
            ArrayList<CampamentoDTO> listaCampamentos = new ArrayList<CampamentoDTO>();
            ArrayList<Integer> listaId = new ArrayList<Integer>();
            ArrayList<Integer> listaCompletas = new ArrayList<Integer>();
            ArrayList<Integer> listaParciales = new ArrayList<Integer>();
            ArrayList<Integer> aux = new ArrayList<Integer>();

            listaCampamentos=gestor.listaCampamentos();
            for(CampamentoDTO campamento : listaCampamentos){
                aux=inscripcionDAO.contarTiposAsistentes(campamento.getId());
                listaId.add(campamento.getId());
                listaCompletas.add(aux.get(0));
                listaParciales.add(aux.get(1));
            }
            request.setAttribute("listaId", listaId);
            request.setAttribute("listaCompletas", listaCompletas);
            request.setAttribute("listaParciales", listaParciales);
    %>

            <jsp:forward page="<%=paginaSiguiente%>">
                <jsp:param value="<%=mensaje%>" name="mensaje"/>
            </jsp:forward>

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
