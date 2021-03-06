package com.dashda.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
public class RestApiController {

    @Secured("ROLE_USER")
    @GetMapping(value = "/user/hello")
    public String welcomeAppUser(@AuthenticationPrincipal User user) {
        return "Welcome User "+ user.getUsername();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value = "/user/hello4")
    public String welcomeAppUser4(@AuthenticationPrincipal User user) {
        return "Welcome User ";
    }
    
    
    @Secured({"ROLE_USER_INFO"})
    @GetMapping(value = "/user/hello41")
    public String welcomeAppUser41(@AuthenticationPrincipal User user) {
        return "Welcome User " + user.getUsername() + " " + user.getAuthorities();
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/user/hello1")
    public String welcomeAppUser1(@AuthenticationPrincipal User user) {
        return "Welcome User ";
    }


    @PreAuthorize("hasRole('USER') AND hasRole('ADMIN')")
    @GetMapping(value = "/user/hello2")
    public String welcomeAppUser2(@AuthenticationPrincipal User user) {
        return "Welcome User " + user.getUsername();
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping(value = "/user/hello3")
    public String welcomeAppUser3(@AuthenticationPrincipal User user) {
        return "Welcome User " + user.getUsername();
    }

    /**
     * <p>
     * This method can be accessed by any user with ROLE_USER.
     * But the content will be returned if the user has the ROLE_ADMIN and
     * authenticated principal name is same as the username of the return object.
     * </p>
     *
     * @param user
     * @return
     */
//    @PreAuthorize("hasRole('USER')")
//    @PostAuthorize("(returnObject.username == principal.name) AND hasRole('ADMIN')")
//    @GetMapping(value = "/user/hello5")
//    public UserProfile welcomeAppUser5(@AuthenticationPrincipal User user) {
//        return new UserProfile("chathuranga", "Chathuranga Tennakoon");
//    }


    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/user/hello6")
    public String welcomeAppUser6(@AuthenticationPrincipal User user) {
        return "Welcome User " + user.getAuthorities();
    }


    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value = "/user/hello7")
    public String welcomeAppUser7(@AuthenticationPrincipal User user) {
        return "Welcome User " + user.getPassword();
    }

}