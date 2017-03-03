package com.wind.data.login.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import static android.R.attr.id;

/**
 * Created by wind on 16/5/19.
 */
@AutoValue
public abstract class LoginUser implements LoginUserModel{
    /*public static final Mapper<LoginUser> MAPPER = new Mapper<LoginUser>(new Mapper.Creator() {

        @Override
        public LoginUserModel create(long _id, String json_user) {
            return new AutoValue_LoginUser(_id, json_user);
        }
    });*/

    public static final Factory<LoginUser> FACTORY=new Factory<>(new Creator<LoginUser>() {
        @Override
        public LoginUser create(long _id, @NonNull String json_user) {
            //return null;
            return new AutoValue_LoginUser(id,json_user);
        }
    });
    public static final RowMapper<LoginUser> SELECT_LOGIN_USER_ROWMARPER = FACTORY.select_login_userMapper();

}
