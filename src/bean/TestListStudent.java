package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {



    private String subjectName;


    private String subjectcd;


    private int num;


    private int point;

    public TestListStudent() {}

    public TestListStudent(String subjectName, String subjectcd, int num, Integer point) {
        this.subjectName = subjectName;
        this.subjectcd = subjectcd;
        this.num = num;
        this.point = point;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectcd() {
        return subjectcd;
    }

    public void setSubjectcd(String subjectcd) {
        this.subjectcd = subjectcd;
    }

    public int getnum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }

    public Integer getpoint() {
        return point;
    }

    public void setpoint(Integer point) {
        this.point = point;
    }


}
