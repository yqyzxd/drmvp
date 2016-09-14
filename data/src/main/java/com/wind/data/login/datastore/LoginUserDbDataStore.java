package com.wind.data.login.datastore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.wind.data.login.model.LoginUser;
import com.wind.data.login.model.LoginUserModel;
import com.wind.data.login.response.LoginResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wind on 16/5/19.
 */
public class LoginUserDbDataStore {
    public static final String TAG="LoginUserDbDataStore";
    private final BriteDatabase mBriteDb;
    private Gson mGson;
    @Inject
    public LoginUserDbDataStore(BriteDatabase briteDb,Gson gson) {

        this.mBriteDb = briteDb;
        this.mGson=gson;
    }

    public void putLoginUser(final LoginResponse loginResponse) {

        String jsonUser=mGson.toJson(loginResponse);
        final BriteDatabase.Transaction transaction = mBriteDb.newTransaction();
        try {
            final LoginUser.LoginUserMarshal marshal = new LoginUser.LoginUserMarshal();

                mBriteDb.insert(LoginUser.TABLE_NAME, marshal.json_user(jsonUser).asContentValues(),
                        SQLiteDatabase.CONFLICT_REPLACE);
            //Log.e(TAG,"putLoginUser success");
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    public Observable<LoginResponse> getLoginUser(){

        return mBriteDb.createQuery(LoginUser.TABLE_NAME,LoginUser.SELECT_LOGIN_USER)
                .map(new Func1<SqlBrite.Query, LoginResponse>() {
                    @Override
                    public LoginResponse call(SqlBrite.Query query) {
                        try {
                            Cursor cursor=query.run();
                            if (cursor.moveToLast()){
                                LoginUserModel model = LoginUser.MAPPER.map(cursor);
                                String json = model.json_user();
                                //Log.e(TAG, "getLoginUser" + json);
                                Gson gson = new Gson();
                                return gson.fromJson(json, LoginResponse.class);
                            }else {
                                return null;
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
    }
}
