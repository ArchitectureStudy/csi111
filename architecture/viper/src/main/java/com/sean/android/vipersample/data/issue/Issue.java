package com.sean.android.vipersample.data.issue;

import com.sean.android.github.dto.IssueDTO;
import com.sean.android.vipersample.base.model.Model;

/**
 * Created by sean on 2017. 1. 7..
 */

public class Issue extends Model {
    private int id;
    private int number;
    private String title;
    private String body;
    private String state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static Issue convertModel(IssueDTO issueDTO) {
        Issue issue = new Issue();
        issue.setId(issueDTO.getId());
        issue.setNumber(issueDTO.getNumber());
        issue.setTitle(issueDTO.getTitle());
        issue.setBody(issueDTO.getBody());
        issue.setState(issueDTO.getState());
        return issue;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
