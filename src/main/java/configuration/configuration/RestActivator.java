package configuration.configuration;

import rest.configuration.path.RestPath;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(RestPath.BASE_REST)
public class RestActivator extends Application {

}
