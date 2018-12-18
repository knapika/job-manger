package com.example.javaserver.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ParsersActivities")
public class ParserUsed {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer parserID;

    @Column(unique = true)
    private String parserName;

    @Column
    private Date used;

    public Integer getParserID() {
        return parserID;
    }

    public void setParserID(Integer parserID) {
        this.parserID = parserID;
    }

    public String getParserName() {
        return parserName;
    }

    public void setParserName(String parserName) {
        this.parserName = parserName;
    }

    public Date getUsed() {
        return used;
    }

    public void setUsed(Date used) {
        this.used = used;
    }
}
