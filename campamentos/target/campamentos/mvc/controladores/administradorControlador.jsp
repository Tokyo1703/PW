<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="src.Negocio.DTO.CampamentoDTO, src.Datos.DAO.InscripcionDAO, src.Negocio.gestorCampamentos" %>
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
            paginaSiguiente="../vistas/administradorVista";
            Properties sql=new Properties();
            Properties config=new Properties();
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));

            gestorCampamentos gestorcampamentos = new gestorCampamentos(sql, config);
            InscripcionDAO inscripcionDAO = new InscripcionDAO(sql, config);
            ArrayList<CampamentoDTO> listaCampamentos = new ArrayList<CampamentoDTO>();
            ArrayList<Integer> listaId = new ArrayList<Integer>();
            ArrayList<Integer> listaCompletas = new ArrayList<Integer>();
            ArrayList<Integer> listaParciales = new ArrayList<Integer>();
            ArrayList<Integer> Aux = new ArrayList<Integer>();
            ArrayList<CampamentoDTO> listaCampamentos = new ArrayList<CampamentoDTO>();

            listaCampamentos=gestor.listaCampamentos();
            for(CampamentoDTO campamento : listaCampamentos){
                Aux=inscripcionDAO.contarTiposAsistentes(campamento.getId());
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
