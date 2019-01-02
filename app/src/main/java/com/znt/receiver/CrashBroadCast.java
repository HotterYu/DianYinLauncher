package com.znt.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CrashBroadCast extends BroadcastReceiver
{

	//private UpdateManager updateManager = null;
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// TODO Auto-generated method stub
		 /*if(intent.getAction().equals("com.znt.dianyin.APP_CRASH"))
		 {
			 if(updateManager == null)
				 updateManager = new UpdateManager(context);
			 Bundle bundle = intent.getExtras();  
			 int versionCode = bundle.getInt("VERSION_CODE");
			 String curSign = bundle.getString("VERSION_SIGN");
			 updateManager.doCheckUpdate(versionCode, curSign);
		 }*/
	}

}
