package proyect.travelassistant.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.geocoder.AddressComponents;
import proyect.travelassistant.beans.geocoder.ResponseGeocode;
import proyect.travelassistant.beans.geocoder.Result;
import proyect.travelassistant.beans.worldweather.Response;
import proyect.travelassistant.utils.RestClient;

public class NewQueryActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private SupportMapFragment mFragment;
    private GoogleMap mapa;

    private Button searchButton;
    private EditText searchText;

    private Geocoder geoCoder;
    private Marker posicion;
    private LatLng coordenadas;

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;

    private Activity activity;

    private String critAnt1;
    private String critAnt2;
    private String critAnt3;
    private String critAnt4;
    private String destAnt;
    private Response response;
    private int calls;

    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_new_query);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item2));
        activity = this;

        critAnt1 = "";
        critAnt2 = "";
        critAnt3 = "";
        critAnt4 = "";
        destAnt = "";


        searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(R.id.searchText);

        geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                performSearch();
            }
        });

        mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mFragment.getMapAsync(this);


        spinner1 = (Spinner) findViewById(R.id.spinner_duration);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.days_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.spinner_reason);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.reason_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) findViewById(R.id.spinner_home);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.house_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = (Spinner) findViewById(R.id.spinner_transport);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.transport_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Button buttonObtenerRecomendaciones = (Button) findViewById(R.id.buttonObtainRecoms);
        buttonObtenerRecomendaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarRed()){
                    if(coordenadas!=null){
                        if(!critAnt1.equals(String.valueOf(spinner1.getSelectedItem())) ||
                                !critAnt2.equals(String.valueOf(spinner2.getSelectedItem())) ||
                                !critAnt3.equals(String.valueOf(spinner3.getSelectedItem())) ||
                                !critAnt4.equals(String.valueOf(spinner4.getSelectedItem())) ||
                                !destAnt.equals(searchText.getText().toString())){
                            calls = 0;
                            new CargarTiempoTask().execute();
                        }else{
                            Intent i = new Intent(activity, ResultActivity.class);
                            i.putExtra("Response",response);
                            i.putExtra("Duracion", critAnt1);
                            i.putExtra("Motivo",critAnt2);
                            i.putExtra("Alojamiento",critAnt3);
                            i.putExtra("Transporte",critAnt4);
                            i.putExtra("Destino",destAnt);
                            i.putExtra("Lat",coordenadas.latitude);
                            i.putExtra("Lon",coordenadas.longitude);
                            activity.startActivity(i);
                        }
                    }else{
                        //Se debe indicar un destino.
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error))
                                .setMessage(getString(R.string.text_error))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .setIcon(R.drawable.ic_warning)
                                .show();
                    }
                }
            }
        });
    }

    protected void onStart() {
        super.onStart();
        comprobarRed();
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.setOnMapClickListener(this);
    }

    private void performSearch(){
        if(comprobarRed()){
            String searchFor = searchText.getText().toString();
            if(searchFor.length()>0){

                if(1==2){
                //if(geoCoder.isPresent()){
                    try {
                        List<Address> addresses =
                                geoCoder.getFromLocationName(searchFor, 5);
                        if (addresses.size() > 0) {

                            hideKeyboard(this);
                            if(posicion!=null) {
                                posicion.remove();
                            }
                            posicion = mapa.addMarker(new MarkerOptions()
                                    .position(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()))
                                    .title(searchFor)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                            coordenadas = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12));

                            String direccion = addresses.get(0).getLocality();
                            if(direccion!= null && direccion.length()>0){
                                searchText.setText(direccion);
                            }else{
                                direccion = addresses.get(0).getSubAdminArea();
                                if(direccion!= null && direccion.length()>0){
                                    searchText.setText(direccion);
                                }else{
                                    //searchText.setTexto("");
                                }
                            }
                        }
                    } catch (IOException e) {
                        Log.e("MAP ERROR",""+e);
                    }
                }else{
                    //PLAN B
                    new LaunchGeocode().execute(searchFor);
                }


            }

        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }


    public static ProgressDialog getCustomProgressDialog(Context mContext, String texto) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(texto);
        return progressDialog;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(posicion!=null) {
            posicion.remove();
        }

        if(comprobarRed()){
            if(1==2){
            //if(geoCoder.isPresent()){
                try {
                    List<Address> addresses = geoCoder.getFromLocation(latLng.latitude,latLng.longitude,1);

                    if(addresses.size()>0){
                        posicion = mapa.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(addresses.get(0).getLocality())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                        coordenadas = latLng;
                        String direccion = addresses.get(0).getLocality();
                        if(direccion!= null && direccion.length()>0){
                            searchText.setText(direccion);
                        }else{
                            direccion = addresses.get(0).getSubAdminArea();
                            if(direccion!= null && direccion.length()>0){
                                searchText.setText(direccion);
                            }else{
                                //searchText.setTexto("");
                            }
                        }
                        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12));
                    }
                } catch (IOException e) {
                    Log.e("MAP ERROR",""+e);
                }
            }else{
                //PLAN B
                lat = latLng.latitude;
                lon =latLng.longitude;
                new LaunchGeocodeInverse().execute();
            }
        }
    }

    private class CargarTiempoTask extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog;
        private int diasIndex;
        @Override
        protected void onPreExecute() {
            diasIndex = spinner1.getSelectedItemPosition();
            progressDialog = getCustomProgressDialog(activity, getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            int dias = 7;
            if(diasIndex == 0){
                dias = 3;
            }

            String url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key="+getString(R.string.key)+"&q="+coordenadas.latitude+","+coordenadas.longitude+"&format=json&num_of_days="+dias+"&lang=es";
            try {
                String responseString =  RestClient.getJsonResponse(url);
                return responseString;

            } catch (Exception e) {
                Log.e("SERVICE ERROR",""+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                if(result!=null && result!=" " && result!=""){
                    response = new Gson().fromJson(result, Response.class);
                    if(response!=null){
                        String criterio1 = String.valueOf(spinner1.getSelectedItem());
                        String criterio2 = String.valueOf(spinner2.getSelectedItem());
                        String criterio3 = String.valueOf(spinner3.getSelectedItem());
                        String criterio4 = String.valueOf(spinner4.getSelectedItem());

                        critAnt1 = criterio1;
                        critAnt2 = criterio2;
                        critAnt3 = criterio3;
                        critAnt4 = criterio4;
                        destAnt = searchText.getText().toString();

                        Intent i = new Intent(activity, ResultActivity.class);
                        i.putExtra("Response",response);
                        i.putExtra("Duracion", criterio1);
                        i.putExtra("Motivo",criterio2);
                        i.putExtra("Alojamiento",criterio3);
                        i.putExtra("Transporte",criterio4);
                        i.putExtra("Destino",searchText.getText().toString());
                        i.putExtra("Lat",coordenadas.latitude);
                        i.putExtra("Lon",coordenadas.longitude);
                        activity.startActivity(i);
                        //finish();
                    }else{
                        //ERROR
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_service))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                    }
                }else{
                    if(calls>2){
                        //ERROR
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_service))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                    }else{
                        calls++;
                        new CargarTiempoTask().execute();
                    }
                }
            } catch (Exception e) {
                Log.e("onPostExecute ERROR",""+e);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean comprobarRed(){
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = false;
        if(activeNetwork!=null){
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        if(!isConnected){
            new AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.title_error_red))
                    .setMessage(getString(R.string.text_error_red))
                    .setPositiveButton(getString(R.string.reset), new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            comprobarRed();
                        }
                    })
                    .setNegativeButton(getString(R.string.exit), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(NewQueryActivity.this, IntroActivity.class));
                            finish();
                        }
                    })
                    .show();
        }
        return isConnected;
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_new_query;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewQueryActivity.this, IntroActivity.class));
        finish();
    }

    private class LaunchGeocode extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;
        private String dir;

        @Override
        protected void onPreExecute() {
            progressDialog = getCustomProgressDialog(activity, getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            dir = params[0];
            String url = " https://maps.googleapis.com/maps/api/geocode/json?address="+dir+"&language=es&components=locality&key="+getResources().getString(R.string.keyGoogle);
            try {
                String responseString =  RestClient.getJsonResponse(url);
                return responseString;
            } catch (Exception e) {
                Log.e("SERVICE ERROR",""+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            try {
                if(result!=null && result!=" " && result!=""){
                    ResponseGeocode response = new Gson().fromJson(result, ResponseGeocode.class);
                    if(response!=null && response.getStatus().equals("OK")){

                        Result resultGeocoder = response.getResults().get(0);
                        hideKeyboard(activity);
                        if(posicion!=null) {
                            posicion.remove();
                        }
                        posicion = mapa.addMarker(new MarkerOptions()
                                .position(new LatLng(resultGeocoder.getGeometry().getLocation().getLat(), resultGeocoder.getGeometry().getLocation().getLng()))
                                .title(dir)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                        coordenadas = new LatLng(resultGeocoder.getGeometry().getLocation().getLat(), resultGeocoder.getGeometry().getLocation().getLng());
                        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12));

                        String direccion = "";
                        for(AddressComponents ac : resultGeocoder.getAddress_components()){
                            for(String typeString : ac.getTypes()){
                                if(typeString.equals("locality")){
                                    direccion = ac.getLong_name();
                                    break;
                                }
                            }
                        }

                        if(direccion!= null && direccion.length()>0){
                            searchText.setText(direccion);
                        }
                    }else{
                        //ERROR
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_geocode))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }

                    }
                }else{
                    //ERROR
                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_error_service))
                            .setMessage(getString(R.string.text_error_geocode))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                }
            } catch (Exception e) {
                Log.e("onPostExecute ERROR",""+e);
            }
        }
    }

    private class LaunchGeocodeInverse extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "  https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&language=es&location_type=APPROXIMATE&result_type=political|locality|administrative_area_level_3&key="+getResources().getString(R.string.keyGoogle);
            try {
                String responseString =  RestClient.getJsonResponse(url);
                return responseString;
            } catch (Exception e) {
                Log.e("SERVICE ERROR",""+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if(result!=null && result!=" "){
                    ResponseGeocode response = new Gson().fromJson(result, ResponseGeocode.class);
                    if(response!=null && response.getStatus().equals("OK")){
                        Result resultGeocoder = response.getResults().get(0);

                        String direccion = "";
                        for(AddressComponents ac : resultGeocoder.getAddress_components()){
                            for(String typeString : ac.getTypes()){
                                if(typeString.equals("locality")){
                                    direccion = ac.getLong_name();
                                    break;
                                }
                            }
                        }
                        if(direccion==null || direccion.length()==0){
                            direccion = resultGeocoder.getAddress_components().get(0).getLong_name();
                        }

                        posicion = mapa.addMarker(new MarkerOptions()
                                .position(new LatLng(lat, lon))
                                .title(direccion)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                        coordenadas = new LatLng(lat, lon);

                        if(direccion!= null && direccion.length()>0){
                            searchText.setText(direccion);
                        }
                        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12));

                    }else{
                        //ERROR
                        new AlertDialog.Builder(activity)
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_geocode))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                    }
                }else{
                    //ERROR
                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.title_error_service))
                            .setMessage(getString(R.string.text_error_geocode))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                }
            } catch (Exception e) {
                Log.e("onPostExecute ERROR",""+e);
            }
        }
    }
}
