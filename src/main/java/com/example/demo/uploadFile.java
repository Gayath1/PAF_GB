package com.example.demo;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Path("/file")
public class uploadFile {
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA})
    public Response insert1(@FormParam("name") String name, @FormParam ("details") String details, InputStream data )
    {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = "insert into research"+"(Name,Creator,details,data) VALUES"+"(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,name);
            st.setString(2,email);
            st.setString(3,details);
            st.setBlob(4,data);
            st.executeUpdate();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return Response.status(200)
                .entity("AddData  name : " + name + ", details : " + details + ", file : "+ data)
                .build();
    }
}

