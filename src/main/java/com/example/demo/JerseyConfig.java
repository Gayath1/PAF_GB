package com.example.demo;

import com.CustomerService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(Home.class);
        register(Admin.class);
        register(Research.class);
        register(addResearch.class);
        register(updateResearch.class);
        register(DeleteResearch.class);
        register(GetResearcherFiles.class);
        register(uploadFile.class);
        register(Profile.class);
        register(PaymentServiceManagement.class);
        register(CustomerService.class);


    }

}
