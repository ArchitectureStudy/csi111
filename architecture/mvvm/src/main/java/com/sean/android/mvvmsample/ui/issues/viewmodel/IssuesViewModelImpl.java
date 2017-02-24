package com.sean.android.mvvmsample.ui.issues.viewmodel;

import android.content.Context;
import android.util.Log;

import com.sean.android.mvvmsample.base.util.ToastMaker;
import com.sean.android.mvvmsample.data.issue.Issue;
import com.sean.android.mvvmsample.data.issue.Issues;
import com.sean.android.mvvmsample.data.issue.IssuesDataSource;
import com.sean.android.mvvmsample.data.issue.IssuesRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by Seonil on 2017-02-23.
 */

public class IssuesViewModelImpl implements IssuesViewModel {

    private Context mContext;


    private PublishSubject<Issues> mPublishIssuesSubject;

    private Observable<List<IssueItemViewModel>> mIssuesObservable;

    private Issues mIssues;

    public IssuesViewModelImpl(Context mContext) {
        this.mContext = mContext;
        mPublishIssuesSubject = PublishSubject.create();
        mIssuesObservable = mPublishIssuesSubject.onBackpressureLatest().map(new Func1<Issues, List<IssueItemViewModel>>() {
            @Override
            public List<IssueItemViewModel> call(Issues issues) {
                List<IssueItemViewModel> itemVMList = new ArrayList<IssueItemViewModel>();
                for (Issue issue : issues.getModels()) {
                    itemVMList.add(new IssueItemViewModelImpl(issue));
                }
                return itemVMList;
            }
        });

    }

    @Override
    public void refreshIssues() {
    }

    @Override
    public void fetchIssues() {
        IssuesRepository.getInstance().fetchIssues(new IssuesDataSource.LoadIssuesCallback() {
            @Override
            public void onIssuesLoaded(Issues issues) {
                mPublishIssuesSubject.onNext(issues);

            }

            @Override
            public void onIssuesFailed(int code, String message) {
                ToastMaker.makeShortToast(mContext, message);
            }
        });

    }

    @Override
    public boolean showIndicator() {
        return false;
    }

    @Override
    public Observable observIssues() {
        return mIssuesObservable;
    }
}
