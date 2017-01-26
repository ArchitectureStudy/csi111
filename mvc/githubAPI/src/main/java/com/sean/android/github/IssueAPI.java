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
    public <E extends List<IssueDTO>> void asyncRequestNextPage(Callback<E> callBack) {
        callService.issues(getHeaderMap(), getNextPageUrl()).enqueue(callBack);
    }

    @Override
    public List<IssueDTO> requestNextPage() {
        return null;
    }

    @Override
    public <E extends List<IssueDTO>> void asyncRequestItems(Callback<E> callBack) {

    }

    @Override
    public <T> void asyncRequestItem(Callback<T> callBack) {

    }

    @Override
    public List<IssueDTO> requestItems() {
        return null;
    }

    @Override
    public IssueDTO requestItem() {
        return null;
    }
}
