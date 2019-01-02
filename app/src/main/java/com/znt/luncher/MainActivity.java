package com.znt.luncher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.znt.timer.CheckFrontAppTimer;
import com.znt.utils.SystemData;
import com.znt.utils.WifiFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements OnItemClickListener
{
	private GridView mGrid = null;
	private List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
	
	private AppsAdapter adapter = null;
	private CheckFrontAppTimer checkFrontAppTimer = null;
	
	private final String pkgSpeaker = "com.znt.speaker";
	private final String pkgMySelf = "com.znt.luncher";
	private boolean isInit = true;
	private final int CHECK_FRONT_APP = 0;
	private View ivBg = null;
	private TextView tvTimer = null;
	private int timeCount = 30;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.what == CHECK_FRONT_APP)
			{
				if(timeCount == -1)
				{
					timeCount = 30;
					if(isRunningForeground(pkgSpeaker) != 0)
					{
						doStartApplicationWithPackageName(pkgSpeaker);
					}
					else
						stopTimer();
				}
				else
				{
					showSpeakerHint();
					timeCount --;
				}
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		ivBg = findViewById(R.id.launcher_bg);
		tvTimer = (TextView)findViewById(R.id.tv_timer);
		mGrid = (GridView) findViewById(R.id.apps_list);
        mGrid.setAdapter(new AppsAdapter());
        
        if(!WifiFactory.newInstance(MainActivity.this).getWifiAdmin().isWifiEnabled())
			WifiFactory.newInstance(MainActivity.this).getWifiAdmin().openWifi();
        
        checkFrontAppTimer = new CheckFrontAppTimer(getApplicationContext());
        checkFrontAppTimer.setHandler(handler, CHECK_FRONT_APP);
        checkFrontAppTimer.setTimeInterval(1000);
        
        mGrid.setOnItemClickListener(this);
        
        adapter = new AppsAdapter();
        mGrid.setAdapter(adapter);
        
        loadApps();

        handler.postDelayed(new Runnable() 
        {
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				if(isRunningForeground(pkgSpeaker) != 0)
				{
					doStartApplicationWithPackageName(pkgSpeaker);
				}
			}
		}, 3000);
	}

	public void startTimer()
	{
		timeCount = 30;
		checkFrontAppTimer.startTimer();
	}
	public void stopTimer()
	{
		checkFrontAppTimer.stopTimer();
	}
	
	private void showSpeakerHint()
	{
		Toast.makeText(this, timeCount + "秒后自动进入店音...", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) 
	{
		int keyCode = event.getKeyCode();
		int keyAction = event.getAction();
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_0:
				if (keyAction == KeyEvent.ACTION_UP)
				{
					return true;
				}
				break;

		}
		return super.dispatchKeyEvent(event);
	}
	
	private void gotoSetPage()
	{
		Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);  
        startActivity(intent);
	}
	

	/**
	*callbacks
	*/
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		startTimer();
		
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
	    filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
	    filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package");
	    registerReceiver(appReceiver, filter);
		
	}
	
	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		stopTimer();
	}
	
	@Override
	protected void onDestroy() 
	{
		if(!isInit)
			checkFrontAppTimer.stopTimer();

		unregisterReceiver(appReceiver);
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void loadApps() 
	{
        //progressDialog = ProgressDialog.show(MainActivity.this, "Loading...", "璇风◢绛�...", true, false);
		if(mApps != null)
			mApps.clear();
        new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		        List<ResolveInfo> tempList = getPackageManager().queryIntentActivities(mainIntent, 0);
				int size = tempList.size();
				for(int i=0;i<size;i++)
				{
					String pkg = tempList.get(i).activityInfo.packageName;
					if(!pkg.equals(pkgMySelf) && !pkg.equals("com.znt.install"))
						mApps.add(tempList.get(i));
				}

			}
		}).start();
    }
	
	private int isRunningForeground (String pkgName)
	{
		int result = 1;
	    ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
	    try
		{
	    	ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		    String currentPackageName = cn.getPackageName();
		    if(!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(pkgName))
		    {
		    	result = 0 ;
		    }
		}
	    catch (Exception e)
		{
			// TODO: handle exception
	    	Log.e("MainAcivity", "isRunningForeground-->" + e.getMessage());
	    	//Toast.makeText(getApplicationContext(), "isRunningForeground-->" + e.getMessage(), 0).show();
	    	result = 2;
		}
	 
	    return result ;
	}
	
	private void stopApp()
	{
		int speakerPid = SystemData.getSpeakerPid(this);
		if(speakerPid > 0)
			android.os.Process.killProcess(speakerPid);
		
	}
	
	private void doStartApplicationWithPackageName(String packagename) 
	{
		Intent intent = null;
		try 
		{
			intent = getPackageManager().getLaunchIntentForPackage(packagename);
		} 
		catch (Exception e) 
		{
			Log.e("", "start " + packagename + " error-->" + e.getMessage());
			e.printStackTrace();
		}
		if (intent == null) 
		{
			return;
		}
		else
			startActivity(intent);

	}

	private long mFirstPressTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			if (System.currentTimeMillis() - mFirstPressTime < 3000)
			{
				finish();
			}
			else 
			{
				mFirstPressTime = System.currentTimeMillis();
				Toast.makeText(this,"再按一次退出店音Launcher",
						Toast.LENGTH_SHORT).show();
			}
			return true;
		} 
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{

		return true;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position,long id)
	{
        ResolveInfo info = mApps.get(position);
         
        String pkg = info.activityInfo.packageName;
        String cls = info.activityInfo.name;
         
        ComponentName componet = new ComponentName(pkg, cls);
         
        Intent i = new Intent();
        i.setComponent(componet);
        startActivity(i);
        
    }
	
	public class AppsAdapter extends BaseAdapter
	{
		
		private Map<String, Drawable> bmList = new HashMap<String, Drawable>();
		
        public AppsAdapter() 
        {
        	
        }
 
        public View getView(int position, View convertView, ViewGroup parent)
        {
        	ViewHolder vh = null;
 
            if (convertView == null) 
            {
            	vh = new ViewHolder();
            	convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_app_item, null);
            	vh.imageView = (ImageView)convertView.findViewById(R.id.iv_app_icon);
            	vh.textView = (TextView)convertView.findViewById(R.id.tv_app_name);
            	
            	convertView.setTag(vh);
            } 
            else 
            {
                vh = (ViewHolder) convertView.getTag();
            }
 
            ResolveInfo info = mApps.get(position);
            
            if(bmList.size() > 0)
        	{
        		String key = info.activityInfo.packageName;
        		if(bmList.containsKey("key"))
        		{
        			Drawable drawable = bmList.get(key);
            		if(drawable != null)
            			vh.imageView.setImageDrawable(drawable);
            		else
            			showImage(info, vh.imageView);
        		}
        		else
        			showImage(info, vh.imageView);
        	}
        	else
        	{
        		showImage(info, vh.imageView);
        	}
            
            //vh.imageView.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            vh.textView.setText(info.activityInfo.loadLabel(getPackageManager()).toString());
            return convertView;
        }
        
        private void showImage(final ResolveInfo info, final ImageView imageView)
        {
        	
        	new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					final Drawable drawable = info.activityInfo.loadIcon(getPackageManager());
					bmList.put(info.activityInfo.packageName, drawable);
					
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							// TODO Auto-generated method stub
							imageView.setImageDrawable(drawable);
						}
					});
				}
			}).start();
        }
        
        private class ViewHolder
        {
        	ImageView imageView = null;
        	TextView textView = null;
        }
 
        public final int getCount()
        {
            return mApps.size();
        }
 
        public final Object getItem(int position)
        {
            return mApps.get(position);
        }
 
        public final long getItemId(int position) 
        {
            return position;
        }
	}
	
	private BroadcastReceiver appReceiver = new BroadcastReceiver()
	{       
    	@Override
    	public void onReceive(Context context, Intent intent) 
    	{
    		// TODO Auto-generated method stub
           
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) 
    		{
                String packageName = intent.getData().getSchemeSpecificPart();
                loadApps();
                RunApp(context, packageName);
                //Toast.makeText(context, "瀹夎鎴愬姛"+packageName, Toast.LENGTH_LONG).show();
            }
    		else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) 
            {
                //String packageName = intent.getData().getSchemeSpecificPart();
                loadApps();
                //Toast.makeText(context, "鍗歌浇鎴愬姛"+packageName, Toast.LENGTH_LONG).show();
            }
    		else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) 
            {
    			String pkg = intent.getData().getSchemeSpecificPart();
    			RunApp(context, pkg);
            }
    	}	
	};
	
	@RequiresApi(api = Build.VERSION_CODES.DONUT)
	private void RunApp(Context context, String packageName)
	{  
		if(!packageName.startsWith("com.znt"))
		{
			return;
		}
        PackageInfo pi;  
        try 
        {  
            pi = context.getPackageManager().getPackageInfo(packageName, 0);  
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
            resolveIntent.setPackage(pi.packageName);  
            PackageManager pManager = context.getPackageManager();  
            List<ResolveInfo> apps = pManager.queryIntentActivities(  
                    resolveIntent, 0);  
  
            ResolveInfo ri = apps.iterator().next();  
            if (ri != null) {  
                packageName = ri.activityInfo.packageName;  
                String className = ri.activityInfo.name;  
                Intent intent = new Intent(Intent.ACTION_MAIN);  
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName(packageName, className);  
                intent.setComponent(cn);  
                context.startActivity(intent);  
            }  
        } catch (NameNotFoundException e) {  
            e.printStackTrace();  
        }  
  
    }
}
