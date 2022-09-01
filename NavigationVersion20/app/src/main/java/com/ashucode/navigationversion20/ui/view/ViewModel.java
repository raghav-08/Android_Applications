package com.ashucode.navigationversion20.ui.view;

public class ViewModel {
    final String stuName;
    final String Marks;

    public ViewModel(String stuName, String marks) {
        this.stuName = stuName;
        Marks = marks;
    }

    public String getStuName() {
        return stuName;
    }

    public String getMarks() {
        return Marks;
    }


}
