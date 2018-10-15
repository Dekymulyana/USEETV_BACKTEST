package id.co.telkom.ippd.useetv_backtest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;
import java.net.MalformedURLException;
import java.net.URL;
import net.sunniwell.app.ott.huawei.service.IPTV;
import java.util.Map;
import id.co.telkom.ippd.ippdapp.R;
import id.co.telkom.ippd.stbinterface.TelkomSTB;
import ztestb.iptv.aidl.ServiceIPTVAidl;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ServiceIPTVAidl zteIptvService = null;
    private IPTV huaweiIptvService = null;
    private TelkomSTB telkomSTB = null;
    Map<String, String> iptvIdentity = new ArrayMap<>();
    private boolean useZTE = false;
    private boolean useHuawei = false;
    private boolean useSTBTelkom = false;
    private boolean useFiberhome = false;
    public String indihomeIdText;
    String afterEnter;
    //TextView indihomeIdText;
    //public String indihomeIdText;
    //String appPackageName= "id.co.telkom.useeindonesia";
    //TextView indihomeIdText;
    //TextView urlText;
    //WebView mWebView;
    /*
    //final String LOAD_BASE_URL = "http://moss.ip-dynamic.com/?";          //test id ih
    //final String LOAD_BASE_URL = "http://moss.ip-dynamic.com/prepaid/?";   //test url
    //final String LOAD_BASE_URL = "http://10.0.8.56/moss/?";     //link production

    final String ID_IH = "indihome_id";
    Uri builtUri;
    */

    public MainActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //FUNGSI UTAMA
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tvStorageButton = (Button) findViewById(R.id.tvstorageIdButton);
        Button wifiIDButton = (Button) findViewById(R.id.wifiIDButton);
        Button stbtambahanButton = (Button) findViewById(R.id.STBTambahanButton);


        //Function 1 Get Indihome ID On Toast
        tvStorageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("id.co.telkom.ippd.indihome.addon.tvstorage");
                intent.setPackage("id.co.telkom.ippd.indihome.addon");
                startActivity(intent);
            }
        });

        wifiIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("aptoidetv://cm.aptoidetv.pt.useeapps/appview?package=id.co.telkom.ippd.indihome.addon"));
                Intent intent = new Intent("id.co.telkom.ippd.indihome.addon.wifiid");
                intent.setPackage("id.co.telkom.ippd.indihome.addon");
                startActivity(intent);

                //Intent i = getPackageManager().getLaunchIntentForPackage("id.co.telkom.ippd.indihome.addon");
                //startActivity(i);
            }
        });

        stbtambahanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("id.co.telkom.ippd.indihome.addon.stbtambahan");
                intent.setPackage("id.co.telkom.ippd.indihome.addon");
                startActivity(intent);

            }
        });
    }

}

