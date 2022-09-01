package com.ashucode.navigationversion20.ui.home;

public class HomeModel
{
    final String Name;
    final String date;
    final String RollNO;

    public HomeModel(String rollNO, String name, String date) {
        RollNO = rollNO;
        Name = name;
        this.date = date;
    }

    public String getRollNO() {
        return RollNO;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return date;
    }

}