package com.Dev.coketohome;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class BannerView extends AppCompatActivity implements View.OnClickListener {
private String TAG = "app";
    private WebView mButterflyWebView;
    private Button mButton;
    private Button mButton1;

    private void init() {
        mButterflyWebView = (WebView) findViewById(R.id.butterfly_webview);
        mButterflyWebView.getSettings().setBuiltInZoomControls(true);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        mButton1 = (Button)findViewById(R.id.goback);
        mButton = (Button)findViewById(R.id.download);
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        init();
        Intent intent = getIntent();
        String htmlstring = intent.getStringExtra("html_page");
        try {
            loadHtmlPage(htmlstring);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
                    Toast.makeText(this, "Permission Granted, You can now use local drive .", Toast.LENGTH_LONG).show();

                } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
                    Toast.makeText(this, "Permission Denied, You cannot use local drive .", Toast.LENGTH_LONG).show();

                }
            break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.download) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    WebView.enableSlowWholeDocumentDraw();
                }
                WebView mButterflyWebView1;
                mButterflyWebView1 = (WebView) findViewById(R.id.butterfly_webview);
                mButterflyWebView1.measure(View.MeasureSpec.makeMeasureSpec(
                        View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                mButterflyWebView1.layout(0, 0, mButterflyWebView1.getMeasuredWidth(),
                        mButterflyWebView1.getMeasuredHeight());
                mButterflyWebView1.setDrawingCacheEnabled(true);
                mButterflyWebView1.buildDrawingCache();
                Bitmap bm = Bitmap.createBitmap(mButterflyWebView1.getMeasuredWidth(),
                        mButterflyWebView1.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

                Canvas bigcanvas = new Canvas(bm);
                Paint paint = new Paint();
                int iHeight = bm.getHeight();
                bigcanvas.drawBitmap(bm, 0, iHeight, paint);
                mButterflyWebView1.draw(bigcanvas);

                if (bm != null) {
                    try {
                        String datetime = Calendar.getInstance().getTime().toString();
                        String shortenedDateTime = datetime.substring(0,19);
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), shortenedDateTime+"Kiraana Poster.png");
                        FileOutputStream fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.getFD().sync();
                        fOut.close();
                        bm.recycle();
                        MediaScannerConnection.scanFile(this, new String[] {file.getAbsolutePath()}, null, null);
                        Toast.makeText(this, "Image Saved!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
        if (view.getId() == R.id.goback) {
            Intent intent = new Intent(BannerView.this, ScrollingActivity.class);
            startActivity(intent);
        }
    }


    private void loadHtmlPage(String htmlString) throws InterruptedException {
        if (htmlString != null) {
            mButterflyWebView.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "UTF-8", null);
        }
        else {
            Toast.makeText(this, R.string.no_such_page, Toast.LENGTH_LONG).show();
        }
    }
}
