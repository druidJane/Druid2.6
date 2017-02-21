package com.druid.mvc;

import com.druid.core.dto.JsonResp;
import com.druid.model.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by druid on 20/02/17.
 */
@Component
@Path("login")
public class LoginController {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public JsonResp login(){
        return JsonResp.fail();
    }
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public JsonResp login(User user){
        System.out.println("Login fail!");
        return JsonResp.fail();
    }
}
