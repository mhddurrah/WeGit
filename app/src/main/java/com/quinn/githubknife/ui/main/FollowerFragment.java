package com.quinn.githubknife.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quinn.githubknife.utils.L;
import com.quinn.httpknife.github.Github;
import com.quinn.httpknife.github.GithubImpl;
import com.quinn.httpknife.github.User;

import java.util.List;

/**
 * Created by Quinn on 7/15/15.
 */
public class FollowerFragment extends BaseFragment {

    Github github;



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("onResume FollowerFragment");
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("onCreate FollowerFragment");
        github = new GithubImpl(this.getActivity());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    List<User> follwers = github.myFollwers(gitHubAccount.getAuthToken());
                    L.i("followers = " + follwers);
                }catch (IllegalStateException e){
                    L.i("internet error");
                }
            }
        }).start();;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        UsersAdapter adapter = new UsersAdapter(null);
        recyclerView.setAdapter(adapter);
        L.i("onCreateView FollowerFragment");

        return view;
    }
}