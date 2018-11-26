// IRemoteService.aidl
package systemui.ailabs.alibaba.com.ipcdemo;
import systemui.ailabs.alibaba.com.ipcdemo.IRemoteCallback;
// Declare any non-default types here with import statements

interface IRemoteService {

    void getViewInfo();
    void registerCallback(IRemoteCallback callback);
    void unregisterCallback(IRemoteCallback callback);
}
