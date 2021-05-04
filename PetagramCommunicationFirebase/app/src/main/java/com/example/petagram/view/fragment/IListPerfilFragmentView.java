package com.example.petagram.view.fragment;

import com.example.petagram.adapter.RecentUserInfoAdapter;
import com.example.petagram.restApi.model.RecentUserInfo;

import java.util.ArrayList;

public interface IListPerfilFragmentView {

      void generateGridLayout();

      RecentUserInfoAdapter createAdapter(ArrayList<RecentUserInfo> recentUserInfos);

      void initAdapterRecentUserInfo(RecentUserInfoAdapter adapter);

}