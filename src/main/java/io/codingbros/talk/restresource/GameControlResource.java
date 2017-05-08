package io.codingbros.talk.restresource;

import io.codingbros.talk.types.Castle;
import io.codingbros.talk.types.MainCharacter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game/control/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GameControlResource {

	@PUT
	@Path("startup")
	Response initGame(String difficulty);

	@POST
	@Path("move/character")
	Response moveToCastle(MainCharacter mainCharacter, Castle castle);

	@POST
	@Path("rescue/{princess}")
	Response rescuePrincessByName(String princess);



}
