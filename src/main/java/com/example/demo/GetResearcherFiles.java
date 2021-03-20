package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Path("/user/research")
public class GetResearcherFiles {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<model> retrive() {
        ResultSet rs;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        List<model> users = new ArrayList<model>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb", "root", "Gayya");

            String query = "select * from research WHERE Creator = ?";

            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,email);
            rs = st.executeQuery();


            while (rs.next()) {
                String name = rs.getString("Name");
                String creator = rs.getString("Creator");
                String details = rs.getString("details");

                users.add(new model(name, creator, details));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
