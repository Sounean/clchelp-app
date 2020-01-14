package com.hyphenate.chatuidemo.section.group;

import android.content.Context;
import android.content.Intent;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.section.base.BaseInitActivity;

public class GroupDetailActivity extends BaseInitActivity {
    private String groupId;

    public static void actionStart(Context context, String groupId) {
        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra("groupId", groupId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.em_activity_chat_group_detail;
    }

    @Override
    protected void initIntent(Intent intent) {
        super.initIntent(intent);
        groupId = intent.getStringExtra("groupId");
    }
}
