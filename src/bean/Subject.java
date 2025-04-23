package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String cd;
    private String name;
    private String shool;


    public Subject() {}

    public Subject(String cd, String name, String shool) {
        this.cd = cd;
        this.name = name;
        this.shool = shool;

    }


    public String getcd() {
        return cd;
    }

    public void setc(String cd) {
        this.cd = cd;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getshool() {
        return shool;
    }

    public void setshool(String shool) {
        this.shool = shool;
    }

    @Override
    public String toString() {
        return "SubjectBean{" +
                "cd='" + cd + '\'' +
                ", name='" + name + '\'' +
                ", shool="+ shool + '\'' +

                '}';
    }
}
