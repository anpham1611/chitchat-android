package com.apmv.chitchat.data;

import com.apmv.chitchat.data.model.Account;
import com.apmv.chitchat.net.api.ApiError;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.List;
import java.util.concurrent.Callable;

import bolts.Task;

@Database(name = LocalStoreUtil.NAME, version = LocalStoreUtil.VERSION)
public class LocalStoreUtil {
    public static final String NAME = "batso_db";
    public static final int VERSION = 2;

    public static Task<Void> clearAllInBackground() {
        return Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                LocalStoreUtil.clearAll();
                return null;
            }
        });
    }

    public static void clearAll() {
        new Delete().from(Account.class).query();
    }

    public static <T extends Model> Task<List<T>> queryInBackgroud(final BaseModelQueriable<T> queriable, final Class<T> model) throws ApiError {
        return Task.callInBackground(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return LocalStoreUtil.query(queriable, model);
            }
        });
    }

    public static <T extends Model> List<T> query(BaseModelQueriable<T> queriable, Class<T> model) throws ApiError {
        return queriable.queryList();
    }
}