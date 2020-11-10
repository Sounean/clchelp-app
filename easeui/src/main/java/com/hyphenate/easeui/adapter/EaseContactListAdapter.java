package com.hyphenate.easeui.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.modules.contact.EaseContactSetModel;
import com.hyphenate.easeui.widget.EaseImageView;

public class EaseContactListAdapter extends EaseBaseRecyclerViewAdapter<EaseUser> {
    private int emptyLayoutResource;
    private EaseContactSetModel contactSetModel;

    @Override
    public ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ease_widget_contact_item, parent, false));
    }

    @Override
    public int getEmptyLayoutId() {
        if(emptyLayoutResource != 0) {
            return emptyLayoutResource;
        }
        return super.getEmptyLayoutId();
    }

    public void setSettingModel(EaseContactSetModel settingModel) {
        this.contactSetModel = settingModel;
    }

    /**
     * 设置无数据时的布局
     * @param emptyLayoutResource
     */
    public void setEmptyLayoutResource(int emptyLayoutResource) {
        this.emptyLayoutResource = emptyLayoutResource;
    }

    private class ContactViewHolder extends ViewHolder<EaseUser> {
        private TextView mHeader;
        private EaseImageView mAvatar;
        private TextView mName;
        private TextView mSignature;
        private TextView mUnreadMsgNumber;
        private ConstraintLayout clUser;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void initView(View itemView) {
            mHeader = findViewById(R.id.header);
            mAvatar = findViewById(R.id.avatar);
            mName = findViewById(R.id.name);
            mSignature = findViewById(R.id.signature);
            mUnreadMsgNumber = findViewById(R.id.unread_msg_number);
            clUser = findViewById(R.id.cl_user);
            if(contactSetModel != null) {
                float headerTextSize = contactSetModel.getHeaderTextSize();
                if(headerTextSize != 0) {
                    mHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, headerTextSize);
                }
                int headerTextColor = contactSetModel.getHeaderTextColor();
                if(headerTextColor != 0) {
                    mHeader.setTextColor(headerTextColor);
                }
                Drawable headerBgDrawable = contactSetModel.getHeaderBgDrawable();
                if(headerBgDrawable != null) {
                    mHeader.setBackground(headerBgDrawable);
                }
                float titleTextSize = contactSetModel.getTitleTextSize();
                if(titleTextSize != 0) {
                    mName.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
                }
                int titleTextColor = contactSetModel.getTitleTextColor();
                if(titleTextColor != 0) {
                    mName.setTextColor(titleTextColor);
                }
                Drawable avatarDefaultSrc = contactSetModel.getAvatarDefaultSrc();
                if(avatarDefaultSrc != null) {
                    mAvatar.setImageDrawable(avatarDefaultSrc);
                }
                float avatarRadius = contactSetModel.getAvatarRadius();
                if(avatarRadius != 0) {
                    mAvatar.setRadius((int) avatarRadius);
                }
                float borderWidth = contactSetModel.getBorderWidth();
                if(borderWidth != 0) {
                    mAvatar.setBorderWidth((int) borderWidth);
                }
                int borderColor = contactSetModel.getBorderColor();
                if(borderColor != 0) {
                    mAvatar.setBorderColor(borderColor);
                }
                int shapeType = contactSetModel.getShapeType();
                if(shapeType != 0) {
                    mAvatar.setShapeType(shapeType);
                }
                float avatarSize = contactSetModel.getAvatarSize();
                if(avatarSize != 0) {
                    ViewGroup.LayoutParams mAvatarLayoutParams = mAvatar.getLayoutParams();
                    mAvatarLayoutParams.height = (int) avatarSize;
                    mAvatarLayoutParams.width = (int) avatarSize;
                }
                float itemHeight = contactSetModel.getItemHeight();
                if(itemHeight != 0) {
                    ViewGroup.LayoutParams userLayoutParams = clUser.getLayoutParams();
                    userLayoutParams.height = (int) itemHeight;
                }
                Drawable bgDrawable = contactSetModel.getBgDrawable();
                clUser.setBackground(bgDrawable);
            }
        }

        @Override
        public void setData(EaseUser item, int position) {
            String header = item.getInitialLetter();
            mHeader.setVisibility(View.GONE);
            if(position == 0 || (header != null && !header.equals(getItem(position -1).getInitialLetter()))) {
                if(!TextUtils.isEmpty(header)) {
                    mHeader.setVisibility(View.VISIBLE);
                    mHeader.setText(header);
                }
            }
            mName.setText(item.getUsername());
            Drawable defaultDrawable = mAvatar.getDrawable();
            Glide.with(mContext)
                    .load(item.getAvatar())
                    .error(defaultDrawable)
                    .into(mAvatar);
        }
    }
}
