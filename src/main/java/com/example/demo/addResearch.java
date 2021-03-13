package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Path("/research")
public class addResearch {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String insert()
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = "insert into research(Name,Creator,details) VALUES(?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(2,email);
            st.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return email;
    }
}
