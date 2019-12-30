package com.hyphenate.chatuidemo.section.friends.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hyphenate.chat.EMGroup;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.common.enums.Status;
import com.hyphenate.chatuidemo.section.base.BaseInitFragment;
import com.hyphenate.chatuidemo.section.friends.adapter.GroupContactAdapter;
import com.hyphenate.chatuidemo.section.friends.viewmodels.GroupContactViewModel;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseRecyclerView;
import com.hyphenate.easeui.widget.EaseSidebar;

import java.util.List;

public class ContactManageFragment extends BaseInitFragment implements EaseSidebar.OnTouchEventListener {
    private EaseRecyclerView mRvGroupList;
    private EaseSidebar mSideBarGroup;
    private TextView mFloatingHeader;
    public GroupContactAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.em_fragment_friends_group_manage;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mRvGroupList = findViewById(R.id.rv_group_list);
        mSideBarGroup = findViewById(R.id.side_bar_group);
        mFloatingHeader = findViewById(R.id.floating_header);

        mRvGroupList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new GroupContactAdapter();
        mRvGroupList.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSideBarGroup.setOnTouchEventListener(this);
    }

    @Override
    public void onActionDown(MotionEvent event, String pointer) {
        showFloatingHeader(pointer);
        moveToRecyclerItem(pointer);
    }

    @Override
    public void onActionMove(MotionEvent event, String pointer) {
        showFloatingHeader(pointer);
        moveToRecyclerItem(pointer);
    }

    @Override
    public void onActionUp(MotionEvent event) {
        hideFloatingHeader();
    }

    private void moveToRecyclerItem(String pointer) {
        List<EMGroup> data = mAdapter.getData();
        if(data == null || data.isEmpty()) {
            return;
        }
        for(int i = 0; i < data.size(); i++) {
            if(TextUtils.equals(EaseCommonUtils.getLetter(data.get(i).getGroupName()), pointer)) {
                LinearLayoutManager manager = (LinearLayoutManager) mRvGroupList.getLayoutManager();
                if(manager != null) {
                    manager.scrollToPositionWithOffset(i, 0);
                }
            }
        }
    }

    /**
     * 展示滑动的字符
     * @param pointer
     */
    private void showFloatingHeader(String pointer) {
        if(TextUtils.isEmpty(pointer)) {
            hideFloatingHeader();
            return;
        }
        mFloatingHeader.setText(pointer);
        mFloatingHeader.setVisibility(View.VISIBLE);
    }

    private void hideFloatingHeader() {
        mFloatingHeader.setVisibility(View.GONE);
    }
}