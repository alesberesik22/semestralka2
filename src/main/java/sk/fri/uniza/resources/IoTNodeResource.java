package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("/IotNode") // Swagger
@Path("/IotNode") // JAX-RS
@Produces(MediaType.APPLICATION_JSON)//Výstupné dáta sú vo forme JSON //JAX-RS
@Consumes(MediaType.APPLICATION_JSON) //Vstupné dáta sú vo forme JSON //JAX-RS
public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Pridanie noveho IoTNodu")
    public IotNode createIotNode(IotNode iotNode) {
        return iotNodeDAO.create(iotNode);
    }

    @PUT /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Úprava existujúceho IoTNodu")
    public IotNode updateIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.update(iotNode);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{id}")
    @ApiOperation(value = "Zobrazí IoTNode podla ID")
    public IotNode findIotNode(@PathParam("id") Long id) {
        return iotNodeDAO.findById(id);
    }
    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zoznam všetkých iotNodes")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

}
