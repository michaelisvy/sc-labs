package com.petclinic.security.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/open/")
    public String getOpen() {
        return "Open access level";
    }
    @RequestMapping("/user/")
    public String getUser(Principal principal) {
        return "User access level for " +principal.getName();
    }

    @RequestMapping("/admin/")
    public String getAdmin(Principal principal) {
        return "Admin access level for " +principal.getName();
    }
}
