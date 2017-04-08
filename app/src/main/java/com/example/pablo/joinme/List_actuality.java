package com.example.pablo.joinme;


public class List_actuality {

    String place;
    String time ;
    String topic ;
    String leveltxt;
    int level ;
    String comment ;

    // Constructor
    public List_actuality(String place,String time, String topic, int level, String comment)
    {
        this.place = place ;
        this.time = time ;
        this.topic = topic ;
        this.level = level ;
        this.leveltxt = String.valueOf(level) ;
        this.comment = comment ;

    }
    // Getter
    public String getPlace() {return this.place ;}
    public String getTime() {return this.time ;}
    public String getTopic() {return this.topic ;}
    public int getLevel() {return this.level ;}
    public String getLeveltxt() {return this.leveltxt ;}
    public String getComment() {return this.comment ;}

}
