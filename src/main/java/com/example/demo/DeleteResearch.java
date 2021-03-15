package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Path("/delete")
public class DeleteResearch {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") String id)
    {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = ("Delete FROM research WHERE   Creator = ?;");
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,id);
            st.setString(2,email);
            st.executeUpdate();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return Response.status(200)
                .entity("DeleteData  name : " + id + ", details : " + email)
                .build();
    }
}
