package rest.configuration.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request,
                       ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");

        response.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");

        response.getHeaders().add("Access-Control-Allow-Credentials", "true");

        response.getHeaders().add("Access-Control-Expose-Headers", "Authorization");

        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        request.getHeaders().add("Access-Control-Allow-Origin", "*");

        request.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");

        request.getHeaders().add("Access-Control-Allow-Credentials", "true");

        request.getHeaders().add("Access-Control-Expose-Headers", "Authorization");

        request.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}
