package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Path("/delete")
public class DeleteResearch {
    @POST
    @Consumes({MediaType.APPLICATION_JSON,  MediaType.APPLICATION_FORM_URLENCODED})
    public Response delete(@FormParam("id") String id)
    {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = ("Delete FROM research WHERE  id = ? AND  Creator = ?;");
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
                .entity("DeleteData  ID : " + id + ", User : " + email)
                .build();
    }
}
