package rest;



import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.jbosslog.JBossLog;
import rest.configuration.path.RestPath;


@Path(RestPath.REST_LOGIN)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@JBossLog
public class LoginRest implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
    @Path(RestPath.VALIDATE)
    public Response restValidateLogin(@QueryParam("username") String login,
                                      @QueryParam("password") String password) {
        try {
                log.info("restValidateLogin: "+login+","+password);
           // Optional<LoginEntity> userEntity = loginDao.validateUser(login, password);
            //   log.info("userEntity.isPresent() : " + userEntity.isPresent());
            return Response.ok(login+","+password).build();
        } catch (Exception e) {
//           log.error(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
