package proyect.travelassistant.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import proyect.travelassistant.R;
import proyect.travelassistant.activitys.HelpActivity;
import proyect.travelassistant.activitys.HistoricalActivity;
import proyect.travelassistant.activitys.NewQueryActivity;
import proyect.travelassistant.adapters.CarouselUpIntroAdapter;
import proyect.travelassistant.adapters.CarrouselDownIntroAdapter;
import proyect.travelassistant.beans.ItemAdvice;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.beans.openweather.OpenWeatherResponseBean;
import proyect.travelassistant.sqlite.Consult;
import proyect.travelassistant.sqlite.ConsultsDB;
import proyect.travelassistant.utils.RestClient;

import static proyect.travelassistant.activitys.NewQueryActivity.getCustomProgressDialog;

public class FragmentIntro  extends Fragment {

    private View mView;
    private Button btn_op1;
    private Button btn_op2;
    private Button btn_op3;
    private Button btn_reload;
    private CarouselUpIntroAdapter adapter;
    private ViewPager pager_up;
    private LinearLayout pager_up_no_internet;
    private ViewPager pager_down;
    private PagerAdapter pagerDownAdapter;
    private String city;
    private List<OpenWeatherResponseBean> elementsUpCarrousel;
    private List<ItemAdvice> elementsDownCarrousel = new ArrayList<>();
    private List<String> queueCities = new ArrayList<>();
    private ProgressDialog progressDialog;

    private int currentPage = 0;
    private Timer timer;
    private DatabaseReference mDatabaseAdvices;
    private DatabaseReference mDatabasCities;

    public int idItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_intro, container, false);

        btn_op1 = (Button) mView.findViewById(R.id.buttonHomeSearch);
        btn_op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewQueryActivity.class));
                getActivity().finish();
            }
        });

        btn_op2 = (Button) mView.findViewById(R.id.buttonHomeHistorical);
        btn_op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoricalActivity.class));
                getActivity().finish();
            }
        });

        btn_op3 = (Button) mView.findViewById(R.id.buttonHomeHelp);
        btn_op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
                getActivity().finish();
            }
        });

        btn_reload = (Button) mView.findViewById(R.id.buttonReloadIntro);
        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawAdvices();
            }
        });

        pager_up = (ViewPager) mView.findViewById(R.id.vp_up_intro);
        pager_up_no_internet = (LinearLayout) mView.findViewById(R.id.vp_up_intro_no_internet);
        pager_down = (ViewPager) mView.findViewById(R.id.vp_down_intro);
        pagerDownAdapter = new CarrouselDownIntroAdapter(getActivity().getSupportFragmentManager(),getActivity(),elementsDownCarrousel);
        pager_down.setAdapter(pagerDownAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if(elementsDownCarrousel.size()>0){
                    if (currentPage == elementsDownCarrousel.size()-1) {
                        currentPage = 0;
                    }else{
                        currentPage++;
                    }
                    pager_down.setCurrentItem(currentPage, true);
                }
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 5000);

        elementsUpCarrousel = new ArrayList<>();

        if(isConnectionAvailable()){
            pager_up.setVisibility(View.VISIBLE);
            pager_up_no_internet.setVisibility(View.GONE);

            DrawCities();
            drawAdvices();
        }else{
            pager_up.setVisibility(View.GONE);
            pager_up_no_internet.setVisibility(View.VISIBLE);
        }
        return mView;
    }

    private void drawAdvices() {
        mDatabaseAdvices = FirebaseDatabase.getInstance().getReference("advices");

        mDatabaseAdvices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                elementsDownCarrousel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    elementsDownCarrousel.add(snapshot.getValue(ItemAdvice.class));
                }
                pagerDownAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void DrawCities() {
        progressDialog = getCustomProgressDialog(getActivity(), getString(R.string.loading));
        progressDialog.show();

        queueCities = obtainHistoricalResults();

        if(queueCities.size()>0 && AuxiliarData.getSingletonInstance().getItemsCarrousel().size()>0 &&
                AuxiliarData.getSingletonInstance().getItemsCarrousel().size() == queueCities.size()){

            elementsUpCarrousel = AuxiliarData.getSingletonInstance().getItemsCarrousel();
            drawViewPager();
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }else{
            if(queueCities.size()==0){
                mDatabasCities = FirebaseDatabase.getInstance().getReference("cities");

                mDatabasCities.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            queueCities.add((String)snapshot.getValue());
                        }
                        callNextRequest(0);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }else{
                callNextRequest(0);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(idItem!=-1){
            Intent intent = new Intent(getActivity(), HistoricalActivity.class);
            intent.putExtra("NotifID",idItem);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    private class ObtainCityWeatherTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params) {

            String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid="+getResources().getString(R.string.appid)+"&lang=es";
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
                if(result!=null && result!=" " && result!=""){
                    OpenWeatherResponseBean response = new Gson().fromJson(result, OpenWeatherResponseBean.class);
                    if(response!=null){
                        elementsUpCarrousel.add(response);
                        callNextRequest(elementsUpCarrousel.size());
                    }else{
                        //ERROR
                        new AlertDialog.Builder(getActivity())
                                .setTitle(getString(R.string.title_error_service))
                                .setMessage(getString(R.string.text_error_service))
                                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        pager_up.setVisibility(View.GONE);
                        pager_up_no_internet.setVisibility(View.VISIBLE);
                    }
                }else{
                    //ERROR
                    new AlertDialog.Builder(getActivity())
                            .setTitle(getString(R.string.title_error_service))
                            .setMessage(getString(R.string.text_error_service))
                            .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    pager_up.setVisibility(View.GONE);
                    pager_up_no_internet.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                Log.e("onPostExecute ERROR",""+e);
            }
        }


    }

    private void callNextRequest(int pos){
        if(pos<queueCities.size()){
            city = queueCities.get(pos);
            new ObtainCityWeatherTask().execute();
        }else{
            AuxiliarData.getSingletonInstance().setItemsCarrousel(elementsUpCarrousel);
            drawViewPager();
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private void drawViewPager(){
        adapter = new CarouselUpIntroAdapter(getActivity(),getFragmentManager(), elementsUpCarrousel);
        pager_up.setAdapter(adapter);
        pager_up.setPageTransformer(false, adapter);
        pager_up.setCurrentItem(elementsUpCarrousel.size());
        pager_up.setOffscreenPageLimit(3);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScreen = size.x;
        int width= (widthScreen/3*(-1))+20;

        pager_up.setPageMargin(width);
        if(elementsUpCarrousel.size()>1){
            pager_up.setCurrentItem((elementsUpCarrousel.size()*adapter.loops)/2);
        }
    }

    private List<String> obtainHistoricalResults(){
        List<Consult> consults;
        List<String> cities = new ArrayList<>();

        ConsultsDB consultsDB = new ConsultsDB(getContext());
        consultsDB.open();
        consults = consultsDB.getConsultas();
        consultsDB.close();

        if(consults!=null && consults.size()>0){
            for(int i=0;i<consults.size();i++){
                cities.add(consults.get(i).getDestino());
            }
        }
        return cities;
    }
    private boolean isConnectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = false;
        if(activeNetwork!=null){
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }

}
