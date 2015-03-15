package com.example.anshul.bat_app;

public class Criminal {
    private String name;
    private int age;
    private boolean male;
    private String crime;
    private String lastSeenLocation;
    private String imageURI;

    public Criminal(String name,int age,boolean male,String crime,String lastSeenLocation){
        this.name=name;
        this.age=age;
        this.male=male;
        this.crime=crime;
        this.lastSeenLocation=lastSeenLocation;
        this.imageURI="null";
    }public Criminal(String name,int age,boolean male,String crime,String lastSeenLocation,String imageURI){
        this.name=name;
        this.age=age;
        this.male=male;
        this.crime=crime;
        this.lastSeenLocation=lastSeenLocation;
        this.imageURI=imageURI;
    }
    public String getName(){
        return name;

    }
    public int getAge(){
        return age;

    }
    public boolean isMale(){
        return male;

    }
    public String getCrime(){
        return crime;

    }
    public String getLastSeenLocation(){
        return lastSeenLocation;

    }
    public String getImageURI(){
        return imageURI;

    }

}
