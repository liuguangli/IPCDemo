package systemui.ailabs.alibaba.com.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import systemui.ailabs.alibaba.com.ipcdemo.IRemoteCallback;
import systemui.ailabs.alibaba.com.ipcdemo.IRemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("systemui.ailabs.alibaba.com.ipcdemo.SERVICE");
        intent.setPackage("systemui.ailabs.alibaba.com.ipcdemo");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IRemoteService remoteService =  IRemoteService.Stub.asInterface(service);
                try {
                    remoteService.registerCallback(new IRemoteCallback.Stub() {
                        @Override
                        public void onBack(int layoutid, String packageName) throws RemoteException {
                            Log.d("MainActivity", "layoutid=" + layoutid + ", pkg=" + packageName);
                            loadRemoteView(packageName, layoutid);
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    remoteService.getViewInfo();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    /**
     * 加载远程视图
     * @param pkg
     * @param layoutId
     */
    private void loadRemoteView(String pkg, int layoutId) {
        try {
            Context c = createPackageContext(pkg, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);

            View view = LayoutInflater.from(c).inflate(layoutId, null);

            ViewGroup container =  findViewById(R.id.container);

            container.addView(view);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
