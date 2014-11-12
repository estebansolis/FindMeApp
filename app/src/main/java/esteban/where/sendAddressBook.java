package esteban.where;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.List;


public class sendAddressBook extends ListActivity {

    public String[] phoneNumbers = {"8325745102","9793190517","9362400198","9795334913","9364652449","9366763737","9362083406","9362406810","9728246613","9364049451","9364046914","7135059472","8325668499","8173198593"};
    protected String[] mAndroidNames = {"Adrien: "+phoneNumbers[0],"Brittany: "+phoneNumbers[1],"Cassidy: "+phoneNumbers[2],"Dakota: "+phoneNumbers[3],"Emilio: "+phoneNumbers[4],
                                                "Father: "+phoneNumbers[5],"Gili: "+phoneNumbers[6],"Hannah: "+phoneNumbers[7],"Katie: "+phoneNumbers[8],"Laci: "+phoneNumbers[9],"Muhammed: "+phoneNumbers[11],"Tristan: "+phoneNumbers[12],"Ricky: "+phoneNumbers[13]};
    //"http://goo.gl/maps/oHJ67";

    private TextView latitudeField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    public static final String TAG = "location error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_address_book);
//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mAndroidNames);
        setListAdapter(adapter);
        ListView lv = getListView();
        try
        {
            int x = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
            if (x == ConnectionResult.SUCCESS){
                Log.i(TAG,
                        "isGooglePlayServicesAvailable SUCCESS");
            }else{
                Log.i(TAG, "isGooglePlayServicesAvailable FAIL " + GooglePlayServicesUtil.getErrorString(x));
            }
            super.onCreate(savedInstanceState);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }catch(Exception i){
            Log.i(TAG, "Exception Caught!", i);
        }
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            Log.i(TAG,"Provider " + provider + " has been selected.");
        } else {
            latitudeField.setText("Location not available");
            longitudeField.setText("Location not available");
        }



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                // String product = ((TextView) view).getText().toString();
                // Launching new Activity on selecting single List Item
                Intent intent = new Intent(getApplicationContext(), confirmationPage.class);
                startActivity(intent);
                sendSMSMessage("I'm here!","maps.google.com/maps?q="+ getLoc().getLatitude()+","+getLoc().getLongitude());

                //intent.putExtra("product", product);
            }


        });
   }
    public Location getLoc()
    {
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        //System.out.println(location.toString());
        return location;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
      ///  Array contacts =
    }



    protected void sendSMSMessage(String NUMBER, String MESSAGESS ) {

        //String phoneNo = "7135059472";//txtphoneNo.getText().toString();
        //String message = MESSAGESS;  //txtMessage.getText().toString();
        String phoneNo1 = "9362083406";//txtphoneNo.getText().toString();
        String message1 = MESSAGESS;  //txtMessage.getText().toString();
        // will send same message but an array of phone numbers through a for loop
        try {
            SmsManager smsManager = SmsManager.getDefault();
        //    smsManager.sendTextMessage(phoneNo, null, message, null, null);
           smsManager.sendTextMessage(phoneNo1, null, message1, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!!!.",
            Toast.LENGTH_LONG).show();
        } catch (Exception e) {
                 Toast.makeText(getApplicationContext(),
                 "SMS failed, please try again.",
                 Toast.LENGTH_LONG).show();
                 e.printStackTrace();
        }
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
