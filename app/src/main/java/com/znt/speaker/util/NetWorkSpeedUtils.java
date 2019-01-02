package com.znt.speaker.util;

import android.content.Context;
import android.net.TrafficStats;

public class NetWorkSpeedUtils {
	private Context context;  
    private long lastTotalRxBytes = 0;  
    private long lastTimeStamp = 0;  
  
    public NetWorkSpeedUtils(Context context){  
        this.context = context;  
    }  
  
    public void startShowNetSpeed(){  
        lastTotalRxBytes = getTotalRxBytes();  
        lastTimeStamp = System.currentTimeMillis();  
    }  
  
    private long getTotalRxBytes() {  
        return TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 :(TrafficStats.getTotalRxBytes()/1024);//תΪKB  
    }  
  
    public String getNetSpeed() 
    {  
        long nowTotalRxBytes = getTotalRxBytes();  
        long nowTimeStamp = System.currentTimeMillis();  
        long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));
        long speed2 = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 % (nowTimeStamp - lastTimeStamp));
  
        String speed2Str = String.valueOf(speed2);
        if(speed2Str.length() >= 2)
        	speed2Str = speed2Str.substring(0, 1);
        
        lastTimeStamp = nowTimeStamp;  
        lastTotalRxBytes = nowTotalRxBytes;  
        String speedStr = "";
        if(speed >= 1024)
        {
        	speedStr = speed / 1024  + " MB";
        }
        else 
        	speedStr = String.valueOf(speed) + "." + speed2Str + " KB";
        return speedStr;
    }  
}
