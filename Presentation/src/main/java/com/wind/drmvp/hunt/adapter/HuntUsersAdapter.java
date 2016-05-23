package com.wind.drmvp.hunt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wind.base.adapter.BaseAdapterHelper;
import com.wind.base.adapter.QuickAdapter;
import com.wind.data.base.bean.UserInfo;
import com.wind.drmvp.R;
import com.wind.drmvp.utils.ImageLoaderWrapper;

import java.util.HashMap;



/**
 * Created by shi on 2015/9/21.
 */
public class HuntUsersAdapter extends QuickAdapter<UserInfo> {

    public HuntUsersAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
        ellipsizeMap=new HashMap();
    }
    private HashMap<String,Boolean> ellipsizeMap;
    @Override
    protected void convert(BaseAdapterHelper helper, final UserInfo item) {
        final ImageView avatar=(ImageView) helper.getView(R.id.iv_avatar);
        setAvatar(avatar,item);


        ImageView cover=helper.getView(R.id.iv_cover);
        String coverUrl=item.getBaseUserInfo().getCover().getImg().getUrl();
        //cover.setTag(coverUrl);


        //使用Gilde
        ImageLoaderWrapper.displayImage(context,coverUrl,cover);

        final TextView tv_expand_or_fold=helper.getView(R.id.tv_expand_or_fold);
        final String uid=item.getBaseUserInfo().getUid();
        final TextView ellipsizeTextView=helper.getView(R.id.tv_about_me);
        String aboutMe=item.getBaseUserInfo().getAboutMe().trim();
        ellipsizeTextView.setText(aboutMe);
        ellipsizeTextView.post(new Runnable() {
            @Override
            public void run() {
                int lineCount=ellipsizeTextView.getLineCount();
                tv_expand_or_fold.setVisibility(lineCount<= 3 ? View.GONE : View.VISIBLE);
                ellipsize(ellipsizeTextView,tv_expand_or_fold,uid);
            }
        });





        tv_expand_or_fold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ellipsizeMap.get(uid)==null){
                    ellipsizeMap.put(uid,true);
                    notifyDataSetChanged();
                    return;
                }
                if (!ellipsizeMap.get(uid)){
                    ellipsizeMap.put(uid,true);
                }else {
                    ellipsizeMap.put(uid,false);
                }
                notifyDataSetChanged();
            }
        });


        helper.setText(R.id.tv_high, item.getBaseUserInfo().getHigh()+"cm");
        helper.setText(R.id.tv_name, item.getBaseUserInfo().getName());
        helper.setText(R.id.tv_age,item.getBaseUserInfo().getAge()+"岁");
     //   helper.setText(R.id.tv_age,SystemUtil.getAgeByBirthday(item.getBaseUserInfo().getBirthday())+"岁");
        helper.setText(R.id.tv_annual_income,item.getBaseUserInfo().getAnnualIncome());
        StringBuilder auth=new StringBuilder();
        String authStr="认证用户";
        boolean isAllAuth=true;


       TextView tv_auth= helper.getView(R.id.tv_auth);
        //tv_auth.setTextColor(context.getResources().getColor(R.color.gold));
        if (!isAllAuth){
           // tv_auth.setCompoundDrawables(null,null,null,null);
            if (auth.length()==0){
                tv_auth.setTextColor(Color.parseColor("#858585"));
                authStr="未通过认证";
            }else{
                auth=auth.deleteCharAt(auth.length()-1);
                authStr="已认证"+auth.toString();

            }
        }else{
           /* Drawable d=context.getResources().getDrawable(R.drawable.my_certification);
            d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
            tv_auth.setCompoundDrawables(d,null,null,null);*/
        }
        tv_auth.setText(authStr);
        helper.setText(R.id.tv_plan_marry_time, item.getBaseUserInfo().getPlanMarryTime());


        helper.setText(R.id.tv_constellation, item.getBaseUserInfo().getConstellation());
        //我是否已关注TA
        if (item.getStatus().getLiked()==0){
            helper.setImageResource(R.id.iv_like, R.drawable.my_unlike);
        }else{
            helper.setImageResource(R.id.iv_like, R.drawable.my_like);
        }
        helper.setOnClickListener(R.id.iv_like, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLikeUnlikeClickListener.onLikeUnlike(item);
            }
        });

        if (item.getStatus().getMember_fees_status()==1){
            helper.getView(R.id.iv_vip).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.iv_vip).setVisibility(View.GONE);
        }


        String abodeFull=item.getBaseUserInfo().getAbode();
        if (abodeFull.contains("-")){
            String []abodeArr=abodeFull.split("-");
            if (abodeArr.length>1)
                helper.setText(R.id.tv_abode,  abodeArr[1]);
            else {
                helper.setText(R.id.tv_abode,  abodeArr[0]);
            }
        }else {
            helper.setText(R.id.tv_abode,  item.getBaseUserInfo().getAbode());
        }

        View ll_recommend=helper.getView(R.id.tv_recommend);
        View line=helper.getView(R.id.line);
        if(item.getStatus().getRecommended()==0){
            ll_recommend.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else {
            ll_recommend.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            //0男1女
           // String gender=item.getBaseUserInfo().getGender()==0?"男":"女";
           // helper.setText(R.id.tv_high_quality, context.getResources().getString(R.string.high_quality_person,gender ));
            //helper.setText(R.id.tv_recommend,"推荐理由:"+item.getStatus().getRecommendedReason());
        }

        View iv_online=helper.getView(R.id.iv_online);
        if ("1".equals(canViewCode)){
            //是否在线 还需要判断用户是否是加v会员
            if (item.getStatus().getOnline()==0){
                iv_online.setVisibility(View.GONE);
            }else {
                iv_online.setVisibility(View.VISIBLE);
            }
        }else {
            iv_online.setVisibility(View.GONE);
        }




        helper.setOnClickListener(R.id.tv_chat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EventBus.getDefault().post(new ChatEvent(item));
            }
        });

    }

    private void setAvatar(ImageView avatar,UserInfo item) {
        avatar.setTag(item.getBaseUserInfo().getAvatar().getImg().getUrl());
       /* if (item.getBaseUserInfo().getGender()==0){
            ImageLoader.getInstance().displayImage(item.getBaseUserInfo().getAvatar().getImg().getUrl(), avatar, manAvatarImageOptions, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (view.getTag()!=null){
                        if (view.getTag().equals(imageUri)) {
                            ((ImageView)view).setImageBitmap(loadedImage);
                        }
                    }
                }
            });
        }else {
            ImageLoader.getInstance().displayImage(item.getBaseUserInfo().getAvatar().getImg().getUrl(), avatar, womenAvatarImageOptions, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (view.getTag()!=null){
                        if (view.getTag().equals(imageUri)) {
                            ((ImageView)view).setImageBitmap(loadedImage);
                        }
                    }

                }
            });
        }*/

    }


    private void ellipsize(TextView ellipsizeTextView,TextView tv_expand_or_fold,final String uid) {
        if (ellipsizeMap.get(uid)==null){
            ellipsizeTextView.setMaxLines(3);
            tv_expand_or_fold.setText("全文");
            return;
        }
        if (!ellipsizeMap.get(uid)){
            ellipsizeTextView.setMaxLines(3);
            tv_expand_or_fold.setText("全文");
        }else {
            ellipsizeTextView.setMaxLines(50);
            tv_expand_or_fold.setText("收起");
        }

    }

    public void setOnLikeUnlikeClickListener(OnLikeUnlikeClickListener onLikeUnlikeClickListener){
        this.onLikeUnlikeClickListener=onLikeUnlikeClickListener;
    }
    private OnLikeUnlikeClickListener onLikeUnlikeClickListener;
    /**
     * Finds a mail by his id if displayed in this adapter
     */
    public UserInfo findById(String uid) {

            if (data == null) {
                return null;
            }

            for (UserInfo m : data) {
                if (uid.equals(m.getBaseUserInfo().getUid())) {
                    return m;
                }
            }
            return null;
    }

    private String canViewCode;
    public void setCanViewOnLine(String canViewCode) {
        this.canViewCode=canViewCode;
    }

    public interface OnLikeUnlikeClickListener{
        void onLikeUnlike(UserInfo item);

    }
}
