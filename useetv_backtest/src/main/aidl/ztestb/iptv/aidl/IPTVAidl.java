package ztestb.iptv.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class IPTVAidl 
{
	private String TAG = "IPTVAidl";
	private Context mContext = null;
	private ServiceIPTVAidl mServiceIPTV = null;
	private static IPTVAidl m_Instance=null;
	
	
	public static enum PLATFORM_TYPE
	{
		PT_IPTV,
		PT_OTT
	}
	
	public static IPTVAidl getInstance()
	{
		if(null==m_Instance)
		{
			m_Instance = new IPTVAidl();
		}
		return m_Instance;
	}
	
	public boolean init(Context context)
	{
		mContext = context;
		Intent intent = new Intent(ServiceIPTVAidl.class.getName());
	    boolean bBindService = mContext.bindService(intent,mServiceIPTVConnection,Context.BIND_AUTO_CREATE);
	    Log.i(TAG, "bBindService="+bBindService);
	    return bBindService;
	}

	public void unBindService()
	{
		try 
		{
			mContext.unbindService(mServiceIPTVConnection);
		} catch (Exception e) 
		{
		}
	}
	
//	public boolean BindService()
//	{
//	    Intent intent = new Intent(ServiceIPTVAidl.class.getName());
//	    boolean bBindService = mContext.bindService(intent,mServiceIPTVConnection,Context.BIND_AUTO_CREATE);
//	    Log.i(TAG, "bBindService="+bBindService);
//	    return bBindService;
//	}
	
	public String getUserName(PLATFORM_TYPE PlatformType)
	{
		Log.i(TAG, "getUserName: iPlatformType="+PlatformType);
		String l_strUserName="";
		if(null==mServiceIPTV)
		{
			Log.i(TAG, "getUserName: mServiceIPTV is null");
			return l_strUserName;
		}
		try
		{
			l_strUserName = (PLATFORM_TYPE.PT_IPTV==PlatformType)? mServiceIPTV.getIPTVPlatFormUser():mServiceIPTV.getOTTPlatFormUser();
		}
		catch (Exception e) 
		{
			Log.i(TAG, "getUserName Exception");
		}
		return l_strUserName;
	}
	
	public String getPassword(PLATFORM_TYPE PlatformType)
	{
		Log.i(TAG, "getPassword: iPlatformType="+PlatformType);
		String l_strPassword="";
		if(null==mServiceIPTV)
		{
			Log.i(TAG, "getPassword: mServiceIPTV is null");
			return l_strPassword;
		}
		try
		{
			l_strPassword = (PLATFORM_TYPE.PT_IPTV==PlatformType)? mServiceIPTV.getIPTVPlatFormPwd():mServiceIPTV.getOTTPlatFormPwd();
		}
		catch (Exception e) 
		{
			Log.i(TAG, "getPassword Exception");
		}
		return l_strPassword;
	}
	
	private ServiceConnection mServiceIPTVConnection = new ServiceConnection()
	{
		public void onServiceConnected(ComponentName className, IBinder service) 
		{
			Log.i(TAG,  "onServiceConnected");
			mServiceIPTV = ServiceIPTVAidl.Stub.asInterface(service);
		}
		
		public void onServiceDisconnected(ComponentName className) 
		{
			Log.i(TAG,  "onServiceDisconnected");
			mServiceIPTV = null;
		}
	};
}

