package com.sean.android.mvcsample.data.issue;

import com.sean.android.mvcsample.base.model.Model;
import com.sean.android.mvcsample.data.dto.IssueDTO;

/**
 * Created by sean on 2017. 1. 7..
 */

public class Issue extends Model {
    private int id;
    private String title;

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

    public static Issue convertModel(IssueDTO issueDTO) {
        Issue issue = new Issue();
        issue.setId(issueDTO.getId());
        issue.setTitle(issueDTO.getTitle());

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
