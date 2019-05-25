package com.a18mahkh.projekt;

public class Movie_itemlist{

    private String movie_title;
    private String movie_description;
    private String imgUrl;


    public Movie_itemlist(String movie_title, String imgUrl) {
        this.movie_title = movie_title;
       // this.movie_description = movie_description;
        this.imgUrl= imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_description() {
        return movie_description;
    }

}