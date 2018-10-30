package com.znt.luncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRvTest;
    private List<ResolveInfo> mlsResolveInfo;
    private RvTestAdapter mAdapter;

    private Button btnInstall = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mRvTest = findViewById(R.id.rv_test);
        btnInstall = findViewById(R.id.btn_start_plugin);
        mlsResolveInfo = new ArrayList<>();

        mRvTest.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new RvTestAdapter(MainActivity.this, mlsResolveInfo, new RvTestAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ResolveInfo resolveInfo = mlsResolveInfo.get(position);
                startAppByResolveInfo(resolveInfo);
                //MainActivity.this.finish();
            }
        });

        btnInstall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openPluginApp();
            }
        });

        mRvTest.setAdapter(mAdapter);

        List<ResolveInfo> resolveInfos = queryMainActivitiesInfo();
        mlsResolveInfo.addAll(resolveInfos);
        mAdapter.notifyDataSetChanged();

    }

    private void openPluginApp()
    {

        File file = new File(Environment.getExternalStorageDirectory() + "/app-debug.apk");
        if(file.exists())
        {
            PluginInfo pluginInfo = RePlugin.install(file.getAbsolutePath());
            RePlugin.preload(pluginInfo);//耗时
        }

        String pluginName = "com.znt.speaker";

        // 若没有安装，则直接提示“错误”
        // TODO 将来把回调串联上
        if (RePlugin.isPluginInstalled(pluginName))
        {
            Log.e("","*********************startActivity");
            RePlugin.startActivity(MainActivity.this, RePlugin.createIntent(pluginName, "com.znt.speaker.MainActivity"));
        }
        else
        {
            Toast.makeText(MainActivity.this, "You must install com.znt.speaker first!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询所有包含启动 intent 的 Activity 信息（去掉本应用）
     *
     * @return
     */
    private List<ResolveInfo> queryMainActivitiesInfo() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(mainIntent, 0);
        // 去掉本应用
        Iterator<ResolveInfo> iterator = resolveInfos.iterator();
        while (iterator.hasNext()) {
            ResolveInfo resolveInfo = iterator.next();
            String packageName = resolveInfo.activityInfo.packageName;
            if (packageName.equals(getApplication().getPackageName())) {
                iterator.remove();
            }
        }
        return resolveInfos;
    }


    private void startAppByResolveInfo(ResolveInfo resolveInfo) {
        String pkg = resolveInfo.activityInfo.packageName;
        String cls = resolveInfo.activityInfo.name;
        ComponentName componet = new ComponentName(pkg, cls);
        //打开该应用的主activity
        Intent intent = new Intent();
        intent.setComponent(componet);
        startActivity(intent);
    }
}