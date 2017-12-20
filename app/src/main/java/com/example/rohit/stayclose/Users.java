package com.example.rohit.stayclose;

/**
 * Created by Rohit on 12/17/2017.
 */

class Users {

    public Users(){


    }

    public String name;
    public String image;
    public String status;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Users(String rate) {

        this.rate = rate;
    }

    public String rate;

    public String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(String name, String image, String status, float rate) {
        this.name = name;
        this.image = image;
        this.status = status;
    }
}
