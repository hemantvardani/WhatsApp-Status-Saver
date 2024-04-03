package com.example.whatsapp2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class class_for_loading_while_user_returns extends AsyncTask<Void, Void, Void> {

    loadingDialog loadingDialog_object;
    BlankFragment_main blankFragment_main;
    private WeakReference<Context> context;


    class_for_loading_while_user_returns(Context context, BlankFragment_main blankFragment_main , loadingDialog loadingDialog_object){
        this.loadingDialog_object=loadingDialog_object;
        this.blankFragment_main=blankFragment_main;
        this.context= new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Your background function here

        try {
            blankFragment_main.ad.updateOnResume();
        } catch (Exception e) {
            // Restart the app
            Context contextRef = context.get();
            if (contextRef != null) {
                Intent intent = contextRef.getPackageManager().getLaunchIntentForPackage(contextRef.getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                contextRef.startActivity(intent);
            }
        }





        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // Function executed, stop the animation here
        blankFragment_main.ad.otherNotifyFun();
        loadingDialog_object.cancel();
    }

}


