package io.codingbros.talk.restresource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/game/info/")
public interface GameMetaInfoResource {

	@GET
	@Path("difficulty")
	Response loadCurrentDifficulty();

	@GET
	@Path("credits")
	Response loadGameCredits();

	@GET
	@Path("about")
	Response loadAboutInformation();
}
