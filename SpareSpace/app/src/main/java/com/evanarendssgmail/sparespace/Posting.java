package com.evanarendssgmail.sparespace;

/**
 * Created by Evan on 7/22/2016.
 */
public class Posting {
    public String Title;
    public String Description;
    public String Location;
    public String Cost;
    public String Dimmension;
    public String Obo;
    public String Phone;
    public String Email;
    public Posting(String title, String description, String location, String cost, String dimmension, String obo, String phone, String email) {
        Title = title;
        Description = description;
        Location = location;
        Cost = cost;
        Dimmension = dimmension;
        Obo = obo;
        Phone = phone;
        Email = email;
    }
}
