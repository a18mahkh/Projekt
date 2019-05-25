package com.a18mahkh.projekt;

public class Movie_itemlist{

    private String movie_title;
    private String company;
    private int running_time;
    private int running_time_min;
    private String movie_description;
    private String category;
    private String imgUrl;

    public Movie_itemlist(String movie_title, String company, int running_time, int running_time_min, String movie_description, String category, String imgUrl) {
        this.movie_title = movie_title;
        this.company = company;
        this.running_time = running_time;
        this.running_time_min=running_time_min;
        this.movie_description = movie_description;
        this.category = category;
        this.imgUrl = imgUrl;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getCompany() {
        return company;
    }

    public int getRunning_time() {
        return running_time;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public String getCategory() {
        return category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String info(){
        String str= "Running time: " + running_time + "h " + running_time_min + "min"+ "\r\n" + "Description: " + movie_description
                + "\r\n" + "Catergory: " + category + "\r\n"+ "Production company: "+ company;

        return str;
    }
}