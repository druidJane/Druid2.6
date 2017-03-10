package com.druid.mvc;

import com.druid.UserService;
import com.druid.core.dto.JsonResp;
import com.druid.model.User;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public JsonResp login(){
        return JsonResp.fail();
    }
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public JsonResp login(User user){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
            subject.login(token);
            User login;
            login = userService.userLogin(user);
            if(login==null){
                return JsonResp.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResp.fail();
        }

        return JsonResp.success();
    }
}
