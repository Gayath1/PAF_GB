package com.example.demo;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/retrive")
public class Research {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<model> retrive()
    {
        ResultSet rs;
        List<model> users = new ArrayList<model>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = "select * from research";

            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();



            while (rs.next())
            {
                String name = rs.getString("Name");
                String creator = rs.getString("Creator");
                String details = rs.getString("details");

                users.add(new model( name, creator, details));
            }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  users;


    }

}

