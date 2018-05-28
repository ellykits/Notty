package com.nerdsoft.coder.notty;

import android.app.Application;

import com.nerdsoft.coder.notty.model.DaoMaster;
import com.nerdsoft.coder.notty.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class NottyApplication extends Application {
    //Flag to switch from standard Sqlite to encrypted SqlCipher database

    public static final boolean IS_ENCRYPTED = true;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        String dbPassword = "4358b5009c67d0e31d7fbf1663fcd3bf";
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(
                NottyApplication.this, IS_ENCRYPTED ? "encrypted-notes-db" : "notes-db");
        Database db = IS_ENCRYPTED ? helper.getEncryptedReadableDb(dbPassword) : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
