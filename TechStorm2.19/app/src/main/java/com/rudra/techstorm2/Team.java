package com.rudra.techstorm2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {

    private String team_name;
    private String leader_name;
    private String leader_dept;
    private String leader_email;
    private int team_size;
    private List<String> memberName=new ArrayList<String>();
    private List<String> memberDept=new ArrayList<String>();

    private String college_Name;
    private String contact_number;
    private String alt_contact_number;


    public Team()
    {

    }

    public Team(String team_name, String leader_email, String leader_name, String leader_dept, int team_size, String[] memberName, String[] memberDept, String college_Name, String contact_number, String alt_contact_number) {
        this.team_name = team_name;
        this.leader_email = leader_email;
        this.leader_name = leader_name;
        this.leader_dept = leader_dept;
        this.team_size = team_size;
        this.memberName = Arrays.asList(memberName);
        this.memberDept = Arrays.asList(memberDept);
        this.college_Name = college_Name;
        this.contact_number = contact_number;
        this.alt_contact_number = alt_contact_number;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getLeader_email() {
        return leader_email;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public String getLeader_dept() {
        return leader_dept;
    }

    public int getTeam_size() {
        return team_size;
    }

    public List<String> getMemberName() {
        return memberName;
    }

    public List<String> getMemberDept() {
        return memberDept;
    }

    public String getCollege_Name() {
        return college_Name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getAlt_contact_number() {
        return alt_contact_number;
    }
}

