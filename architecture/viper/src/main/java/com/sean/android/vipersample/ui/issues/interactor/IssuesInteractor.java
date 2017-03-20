package com.sean.android.vipersample.ui.issues.interactor;

import com.sean.android.vipersample.data.issue.Issue;
import com.sean.android.vipersample.data.issue.Issues;
import com.sean.android.vipersample.data.issue.IssuesDataSource;
import com.sean.android.vipersample.data.issue.IssuesRepository;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seonil on 2017-03-06.
 */

public class IssuesInteractor {

    private List<IssueItemViewModel> mCachedData;

    public IssuesInteractor() {
        mCachedData = new ArrayList<>();
    }

    public void execute(final Action action) {
        IssuesRepository.getInstance().fetchIssues(new IssuesDataSource.LoadIssuesCallback() {
            @Override
            public void onIssuesLoaded(Issues issues) {
                List<IssueItemViewModel> itemVMList = new ArrayList<IssueItemViewModel>();
                for (Issue issue : issues.getModels()) {
                    itemVMList.add(new IssueItemViewModelImpl(issue));
                }
                if (action != null) {
                    action.onCompleted(itemVMList);
                }

                mCachedData.clear();
                mCachedData.addAll(itemVMList);
            }

            @Override
            public void onIssuesFailed(int code, String message) {
                if (action != null) {
                    action.onFailed(message);
                }
            }
        });
    }

    public List<IssueItemViewModel> getIssueItemViewModels() {
        return mCachedData;
    }

    public interface Action {
        void onCompleted(List<IssueItemViewModel> itemViewModelList);

        void onFailed(String message);
    }
}
