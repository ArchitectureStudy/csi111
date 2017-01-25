package com.sean.android.github;

import com.sean.android.github.call.IssueCallService;

/**
 * Created by Seonil on 2017-01-25.
 */

public class IssueAPI extends GithubAPI<IssueCallService> {
    public IssueAPI() {
        super(IssueCallService.class);
    }
}
