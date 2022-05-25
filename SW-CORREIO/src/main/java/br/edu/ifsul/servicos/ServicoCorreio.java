
package br.edu.ifsul.servicos;
import br.edu.ifsul.cliente.CResultado;
import br.edu.ifsul.cliente.CServico;
import br.edu.ifsul.cliente.CalcPrecoPrazoWS;
import br.edu.ifsul.cliente.CalcPrecoPrazoWSSoap;
import br.edu.ifsul.dao.DAOCompra;
import br.edu.ifsul.modelo.Compra;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Stateless
@Path("compra")
public class ServicoCorreio implements Serializable {

    @EJB
    private DAOCompra dao;
    String error;
    String erroMsg;
    public ServicoCorreio() {
    
    }
    
    @GET
    @Produces("application/json")
    @Path("/listar")
    public Response listar(){
        try {
            return Response.ok().entity(dao.getOrders()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error: "+ e.getMessage()).build();
        }
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response adicionar(Compra o){
               
        try {
                if(o.getServico().equals("04014") || o.getServico().equals("SEDEX") ||
                o.getServico().equals("04510") || o.getServico().equals("PAC") ||
                o.getServico().equals("04782") || o.getServico().equals("SEDEX 12") ||
                o.getServico().equals("04790") || o.getServico().equals("SEDEX 10") ||
                o.getServico().equals("04804") || o.getServico().equals("SEDEX Hoje")){
            
        CResultado result = calcPrecoPrazo("","",o.getServico(),o.getCepOrigem(),o.getCepDestino(),o.getPeso().toString(),
                o.getFormato(),o.getComprimento(),o.getAltura(),o.getDiametro(),o.getLargura(),"N",BigDecimal.ZERO,"N");
            
            List<CServico> servicos = result.getServicos().getCServico();
            
            error = "";
            for (CServico c : servicos) {
                if(c.getErro().equals("0")){
                    o.setFrete(c.getValor());
                    o.setPrazo(c.getPrazoEntrega()+" dias");
                } else {
                    error = c.getMsgErro();
                }
            }
            if(error.equals("-2")){
                    return Response.status(452).entity("-2 : CEP inválido").build();
                }
                else if(error.equals("-888")){
                    int i = erroMsg.indexOf("ERP");
                    erroMsg = erroMsg.substring(i, i+7);
                    
                    if(erroMsg.equals("ERP-008")){
                       return Response.status(453).entity("Dimensões inválidas").build(); 
                    }
                    else if(erroMsg.equals("ERP-037")){
                        return Response.status(454).entity("Peso acima do permitido(Maior que 50kg)").build();
                    }else{
                        return Response.status(455).entity(erroMsg).build();
                    }                    
                }else if(error.equals("99")){
                    return Response.status(460).entity("99 : Servico não existe").build();
                }
            }
            
            if(!error.equals("")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
            } else {
                return Response.status(Response.Status.CREATED).entity(dao.persist(o) ).build();
            }
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: "+e.getMessage()).build();
        }
    }

    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response editar(Compra o) {
        try {
            return Response.status(Response.Status.ACCEPTED).entity(dao.merge(o)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Path("remove/{id}")
    public Response remover(@PathParam("id") Integer id) {
        System.out.println("Delete Order with id: " + id.toString());
        try {
            dao.remove(id);
            return Response.status(Response.Status.ACCEPTED).entity("Compra com id  " + id.toString() + " removida!").build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error: " + e.getMessage()).build();
        }
    }
    
    private static CResultado calcPrecoPrazo(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVIPeso, int nCdFormato, BigDecimal nVlComprimento, BigDecimal nVlAltura, BigDecimal nVlLargura, BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento ) {
        CalcPrecoPrazoWS service = new CalcPrecoPrazoWS();
        CalcPrecoPrazoWSSoap port = service.getCalcPrecoPrazoWSSoap();
        return port.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVIPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
    }
}
