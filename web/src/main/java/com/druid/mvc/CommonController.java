package com.druid.mvc;

import com.druid.core.dto.JsonResp;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by druid on 21/02/17.
 */
@Path(value = "common")
@Component
public class CommonController {
    @GET
    @Path(value = "login")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonResp login(){
        return JsonResp.fail();
    }
}
