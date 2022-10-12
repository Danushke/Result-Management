package com.desirecode.rms;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME="mysharedpfre12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_INDEX="index";
    private static final String KEY_DEP="department";
    private static final String KEY_SHORTNAME="short_name";
    private static final String KEY_TABLENAME="table_name";

    private SharedPrefManager(Context context) {
        ctx = context;
    }


        public static synchronized SharedPrefManager getInstance (Context context){
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }
        //remenber to add "dep" here
        public boolean userLogin(int index_no, String shortName,String tableName,String dep){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPrefManager.edit();

            editor.putInt(KEY_INDEX,index_no);
            //editor.putString(KEY_USERNAME,username);
            editor.putString(KEY_SHORTNAME,shortName);
            editor.putString(KEY_TABLENAME,tableName);
            editor.putString(KEY_DEP,dep);

            editor.apply();
            return true;
        }
        public boolean isLoggedIn(){
            SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            //if the user name is not available in the "sharedPreferences.getString(KEY_USERNAME,null)" it return null
            //if (sharedPreferences.getString(KEY_USERNAME,null) != null){
            if (sharedPreferences.getString(KEY_SHORTNAME,null) != null){
                return true;
            }
            return false;
        }
        public boolean logout(){
            SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.apply();
            return true;
        }
        //to get the current user details to the profile activity
        public String getUsername(){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPrefManager.getString(KEY_USERNAME,null);
        }
        public String getShortname(){
        SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPrefManager.getString(KEY_SHORTNAME,null);
        }
        public int getIndex(){
        SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPrefManager.getInt(KEY_INDEX,-1);
        }
        public String getDep(){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPrefManager.getString(KEY_DEP,null);
        }
        public String getTableName(){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPrefManager.getString(KEY_TABLENAME,null);
        }
        /*public int getUserIndex(){
            SharedPreferences sharedPrefManager = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPrefManager.getInt(KEY_INDEX,null);
        }*/

}
