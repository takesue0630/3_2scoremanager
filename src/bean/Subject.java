package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String subjectCode;
    private String subjectName;


    public Subject() {}

    public Subject(String subjectCode, String subjectName) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }


    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "SubjectBean{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
