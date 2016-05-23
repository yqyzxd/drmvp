package com.wind.data.login.model;

import com.google.auto.value.AutoValue;

/**
 * Created by wind on 16/5/19.
 */
@AutoValue
public abstract class LoginUser implements LoginUserModel{
    public static final Mapper<LoginUser> MAPPER = new Mapper<LoginUser>(new Mapper.Creator() {

        @Override
        public LoginUserModel create(long _id, String json_user) {
            return new AutoValue_LoginUser(_id, json_user);
        }
    });

    public static final class Marshal extends LoginUserMarshal<Marshal> { }
}
