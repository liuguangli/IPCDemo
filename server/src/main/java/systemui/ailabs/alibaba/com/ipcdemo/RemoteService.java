package systemui.ailabs.alibaba.com.ipcdemo;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * @author liuguangli
 * @date 2018/11/26
 */
public class RemoteService extends Service {


    IBinder mBinder = new IRemoteService.Stub() {
        @Override
        public void getViewInfo() throws RemoteException {
            int n = mCallbackList.beginBroadcast();
            for (int i = 0; i < n; i++) {
                mCallbackList.getBroadcastItem(i).onBack(R.layout.layout_remote_view,"systemui.ailabs.alibaba.com.ipcdemo");
            }

            mCallbackList.finishBroadcast();
        }

        @Override
        public void registerCallback(IRemoteCallback callback) throws RemoteException {
            mCallbackList.register(callback);
        }

        @Override
        public void unregisterCallback(IRemoteCallback callback) throws RemoteException {
            mCallbackList.unregister(callback);
        }
    };

    RemoteCallbackList<IRemoteCallback> mCallbackList = new RemoteCallbackList();



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
