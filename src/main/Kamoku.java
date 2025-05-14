package main;

import java.io.Serializable;

public class Kamoku implements Serializable {
    private String cd;   // 科目コード
    private String name; // 科目名

    public Kamoku(String cd, String name) {
        this.cd = cd;
        this.name = name;
    }


    public String getCd() {
        return cd;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
