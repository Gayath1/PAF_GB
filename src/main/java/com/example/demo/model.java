package com.example.demo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
public class model {
    public String name;
    public String creator;
    public String details;
    public model() {} // JAXB needs this

    public model(String name, String creator, String details) {
        this.name = name;
        this.creator = creator;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
