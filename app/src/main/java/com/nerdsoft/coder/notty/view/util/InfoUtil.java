package com.nerdsoft.coder.notty.view.util;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class InfoUtil {
    public static void toastMessage(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void snackbarMessage(View view, String msg,String actionName, View.OnClickListener callback){
        Snackbar.make(view,msg,Snackbar.LENGTH_LONG).setAction(actionName,callback).show();
    }
}
