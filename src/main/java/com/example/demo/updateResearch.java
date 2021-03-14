package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Path("/update")
public class updateResearch {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("details") String details )
    {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = ("UPDATE  research SET Name = ?,details = ?  WHERE  id = ? AND Creator = ?;");
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,name);
            st.setString(2,details);
            st.setString(3,id);
            st.setString(4,email);
            st.executeUpdate();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return Response.status(200)
                .entity("UpdateData  name : " + name + ", details : " + details)
                .build();
    }
}
