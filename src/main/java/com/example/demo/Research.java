package com.example.demo;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/retrive")
public class Research {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String retrive()
    {
        ResultSet rs;
        String name = null;
        String creator ="";
        String details = "";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = "select * from research";

            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();


            while (rs.next())
            {
                 name = rs.getString("Name");
                 creator = rs.getString("Creator");
                 details = rs.getString("details");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return name;


    }
}
