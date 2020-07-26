package com.Dev.coketohome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener,AsyncResponse {

    private Button mButton;
    private String TAG = "tag";
    RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
    private String cokeRatesString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(isStoragePermissionGranted()) {
            retrieveFeedTask.delegate = this;
            retrieveFeedTask.execute();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scrolling);
            mButton = (Button) findViewById(R.id.button_send);
            mButton.setOnClickListener(this);
        }

    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.BLUETOOTH)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {
                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 2);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
                return isStoragePermissionGranted();
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onClick(View view) {
            if (view.getId() == R.id.button_send) {

                System.out.println(this.cokeRatesString);

//                {"rates":[10,12,14,15,16,17,18,19,20]}
                String rawString = this.cokeRatesString;
                String finalString = rawString.substring(10, rawString.length()-3);
                System.out.println(finalString);
                String[] priceArray = finalString.split(",");


                TextInputLayout storeNameObject = (TextInputLayout) findViewById(R.id.storename);
                TextInputLayout gstinObject = (TextInputLayout) findViewById(R.id.gstin);
                TextInputLayout phonenoObject = (TextInputLayout) findViewById(R.id.phoneno);
                TextInputLayout storeOwnerObject = (TextInputLayout) findViewById(R.id.storeownername);

                String storeName = storeNameObject.getEditText().getText().toString();
                String gstin = gstinObject.getEditText().getText().toString();
                String phoneno = phonenoObject.getEditText().getText().toString();
                String ownername = storeOwnerObject.getEditText().getText().toString();

                List<String> itemList = new ArrayList<>();
                List<String> unitlist = new ArrayList<>();
                List<String> priceList = new ArrayList<>();
                List<String> offerPriceList = new ArrayList<>();

                itemList.add(((TextView) findViewById(R.id.i1)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u1)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p1)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op1)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i2)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u2)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p2)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op2)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i3)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u3)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p3)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op3)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i4)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u4)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p4)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op4)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i5)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u5)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p5)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op5)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i6)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u6)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p6)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op6)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i7)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u7)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p7)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op7)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i8)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u8)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p8)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op8)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i9)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u9)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p9)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op9)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i10)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u10)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p10)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op10)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i11)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u11)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p11)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op11)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i12)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u12)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p12)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op12)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i13)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u13)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p13)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op13)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i14)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u14)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p14)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op14)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i15)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u15)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p15)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op15)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i16)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u16)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p16)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op16)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i17)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u17)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p17)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op17)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i18)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u18)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p18)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op18)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i19)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u19)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p19)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op19)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i20)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u20)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p20)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op20)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i21)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u21)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p21)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op21)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i22)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u22)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p22)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op22)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i23)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u23)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p23)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op23)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i24)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u24)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p24)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op24)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i25)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u25)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p25)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op25)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i26)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u26)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p26)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op26)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i27)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u27)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p27)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op27)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i28)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u28)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p28)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op28)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i29)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u29)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p29)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op29)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i30)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u30)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p30)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op30)).getEditText().getText().toString());

                itemList.add(((TextView) findViewById(R.id.i31)).getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u31)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p31)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op31)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i32)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u32)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p32)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op32)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i33)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u33)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p33)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op33)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i34)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u34)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p34)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op34)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i35)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u35)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p35)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op35)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i36)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u36)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p36)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op36)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i37)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u37)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p37)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op37)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i38)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u38)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p38)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op38)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i39)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u39)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p39)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op39)).getEditText().getText().toString());

                itemList.add("-" + ((TextInputLayout) findViewById(R.id.i40)).getEditText().getText().toString());
                unitlist.add(((TextInputLayout) findViewById(R.id.u40)).getEditText().getText().toString());
                priceList.add(((TextInputLayout) findViewById(R.id.p40)).getEditText().getText().toString());
                offerPriceList.add(((TextInputLayout) findViewById(R.id.op40)).getEditText().getText().toString());


                // Do something with lists here
                // Try to create a html table

                StringBuilder buf = new StringBuilder();
                buf.append("<html>" +

                        "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "<title>Banner</title>\n" +
                        "    <style>\n" +
                        ".container {\n" +
                        "  position: relative;\n" +
                        "  text-align: center;\n" +
                        "  color: white;\n" +
                        "}\n" +
                        ".storename {\n" +
                        "  position: absolute;\n" +
                        "  top:45px;\n" +
                        "  left: 50%;\n" +
                        "  font-size:12px;\n" +
                        "  transform: translate(-50%, -50%);\n" +
                        "}\n" +
                        ".name {\n" +
                        "  position: absolute;\n" +
                        "  top: 23px;\n" +
                        "  left: 33%;\n" +
                        "  font-size:5px;\n" +
                        "\n" +
                        "}\n" +
                        ".phno {\n" +
                        "  position: absolute;\n" +
                        "  top: 23px;\n" +
                        "  left:53%;\n" +
                        "  font-size:5px;\n" +
                        "}\n" +
                        ".gstin {\n" +
                        "  position: absolute;\n" +
                        "  top:60px;\n" +
                        "  left: 50%;\n" +
                        "  font-size:5px;\n" +
                        "  transform: translate(-50%, -50%);\n" +
                        "}\n" +
                        ".centered {\n" +
                        "  position: absolute;\n" +
                        "  bottom: 50%;\n" +
                        "  right: 10;\n" +
                        "}\n" +
                        "\n" +
                        "#table1 {\n" +
                        "        position:absolute;\n" +
                        "        left: 2%;\n" +
                        "        top: 78px;\n" +
                        "        text-align:center;\n" +
                        "        border-spacing: 0px 1px 0px 1px;\n" +
                        "        border: 1px solid black;font-size:5px;\n" +
                        "}\n" +
                        "#table2 {\n" +
                        "        position:absolute;\n" +
                        "        left:52%;\n" +
                        "        top: 78px;\n" +
                        "        text-align:center;\n" +
                        "        border-spacing: 0px 1px 0px 1px;\n" +
                        "        border: 1px solid black;font-size:5px;\n" +
                        "}\n" +
                        "#table3 {\n" +
                        "        position:absolute;\n" +
                        "        left:82%;\n" +
                        "        bottom: 28px;\n" +
                        "        text-align:center;\n" + "  font-size:9px;\n" +

                        "}\n" +
                        "th {\n" +
                        "        border: 0.5px solid black;\n" +
                        "        width: 10%;\n" +
                        "        height: 7px;\n" +
                        "}\n" +
                        "td {\n" +
                        "        border: 0.5px solid black;\n" +
                        "        width: 10%;\n" +
                        "        height: 7px;\n" +
                        "}\n" +
                        "#table3 td {\n" +
                        "        height: 17px;\n" +
                        "        padding: 1px 4px 8px 4px;\n" +
                        "        border: 0.5px solid black;\n" +
                        "        font-weight: bold;\n" +
                        "}\n" +
                        ".col1 {\n" +
                        "        width: 30%;\n" +
                        "}\n" +
                        "\n" +
                        ".col0 {\n" +
                        "        width: 5%;\n" +
                        "}\n" +
                        "\n" +
                        ".col5 {\n" +
                        "        width: 15%;\n" +
                        "}\n" +
                        "\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"container\">\n" +
                        "    <img src=\"background.png\" alt=\"Snow\" style=\"width:100%;\">\n" +
                        "    <div class=\"name\">" + ownername + "</div>\n" +
                        "    <div class=\"phno\">" + phoneno + "</div>\n" +
                        "    <div class=\"gstin\">GSTIN: " + gstin + "</div>\n" +
                        "    <div class=\"storename\">" + storeName + "</div>\n" +
                        "<table id=\"table3\" width=\"17.5%\">");
                        for(int i=0;i<5;i++) {
                            buf.append("<tr><td>"+priceArray[i*2]+"</td>"+
                            "<td>"+priceArray[(i*2)+1]+"</td></tr>");
                        }
                        buf.append("</table>" +
                        "\n" +
                        "    <div class=\"tables\">\n" +
                        "        <table id=\"table1\" width=\"46%\">\n" +
                        "            <tr>\n" +
                        "<th class=\"col0\">" + " " + "</th>" +
                        "<th class=\"col1\">" + getString(R.string.itemname) + "</th>" +
                        "<th>" + getString(R.string.unitheading) + "</th>" +
                        "<th>" + getString(R.string.price_heading) + "</th>" +
                        "<th class=\"col5\">" + getString(R.string.offer_price_heading) + "</th>" +
                        "            </tr>\n");

                for (int i = 0; i <= itemList.size() / 2 - 1; i++) {
                    buf.append("<tr><td class=\"col0\">")
                            .append(i + 1 + ". ")
                            .append("</td><td class=\"col1\">")
                            .append(itemList.get(i))
                            .append("</td><td>")
                            .append(unitlist.get(i))
                            .append("</td><td>")
                            .append(priceList.get(i))
                            .append("</td><td class=\"col5\">")
                            .append(offerPriceList.get(i))
                            .append("</td></tr>");
                }


                buf.append("        </table>\n" +
                        "        <table id=\"table2\" width=\"46%\">\n" +
                        "            <tr>\n" +
                        "<th class=\"col0\">" + " " + "</th>" +
                        "<th class=\"col1\">" + getString(R.string.itemname) + "</th>" +
                        "<th>" + getString(R.string.unitheading) + "</th>" +
                        "<th>" + getString(R.string.price_heading) + "</th>" +
                        "<th class=\"col5\">" + getString(R.string.offer_price_heading) + "</th>" +
                        "            </tr>\n");

                for (int i = itemList.size() / 2; i <= itemList.size() - 1; i++) {
                    buf.append("<tr><td class=\"col0\">")
                            .append(i + 1 + ". ")
                            .append("</td><td class=\"col1\">")
                            .append(itemList.get(i))
                            .append("</td><td>")
                            .append(unitlist.get(i))
                            .append("</td><td>")
                            .append(priceList.get(i))
                            .append("</td><td class=\"col5\">")
                            .append(offerPriceList.get(i))
                            .append("</td></tr>");
                }


                buf.append("        </table>\n" +
                        "    </div>\n" +
                        "\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>");

                String htmlAsString = buf.toString();

                Intent intent = new Intent(ScrollingActivity.this, BannerView.class);
                intent.putExtra("html_page", htmlAsString);
                startActivity(intent);

            }
    }

    @Override
    public void processFinish(String output) {
        this.cokeRatesString = output;
    }
}

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
    public AsyncResponse delegate = null;
    protected String doInBackground(Void... urls) {

        try {
            URL url = new URL("https://api.npoint.io/68cf2489ae5bc6b85da6");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        delegate.processFinish(response);
        Log.i("INFO", response);
    }
}