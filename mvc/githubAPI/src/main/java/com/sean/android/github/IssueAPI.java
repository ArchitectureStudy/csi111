package com.sean.android.github;

import com.sean.android.github.call.IssueCallService;
import com.sean.android.github.dto.IssueDTO;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public class IssueAPI extends GithubPaginationAPI<IssueCallService, IssueDTO> {

    IssueCallService callService;

    public IssueAPI() {
        super(IssueCallService.class);
        callService = createCallService();
    }

    @Override
    public void asyncRequestNextPage(Callback callBack) {
    }

    @Override
    public List<IssueDTO> requestNextPage() {
        return null;
    }

    @Override
    public void asyncRequestItems(Callback callBack) {

    }

    @Override
    public void asyncRequestItem(Callback callBack) {

    }

    @Override
    public List requestItems() {
        return null;
    }

    @Override
    public Object requestItem() {
        return null;
    }
}
