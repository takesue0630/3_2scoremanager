package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String cd;
    private String name;
    private School school;


    public Subject() {}

    public Subject(String cd, String name, School school) {
        this.cd = cd;
        this.name = name;
        this.school = school;

    }


    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Subject{cd='" + cd + "', name='" + name + "', school=" + (school != null ? school.getCd() : "null") + "}";
    }
}

