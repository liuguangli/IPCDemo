// IRemoteCallback.aidl
package systemui.ailabs.alibaba.com.ipcdemo;

// Declare any non-default types here with import statements

interface IRemoteCallback {

  void onBack(int layoutid, String packageName);
}
