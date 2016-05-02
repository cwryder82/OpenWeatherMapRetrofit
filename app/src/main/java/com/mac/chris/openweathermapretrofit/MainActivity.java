package com.mac.chris.openweathermapretrofit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mac.chris.openweathermapretrofit.data.List;
import com.mac.chris.openweathermapretrofit.data.WeatherData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String API_KEY = "a834182bf0de3d05f5887b94e4f14b96";

    EditText searchText;
    TextView cityName;
    Button search;

    WeatherData mWeatherData;
    java.util.List<List> mList;

    RecyclerView recyclerView;
    RVAdapter rvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchText);
        cityName = (TextView) findViewById(R.id.cityText);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        search = (Button) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitTask rt = new RetrofitTask();
                rt.execute();

            }
        });

    }

    public class RetrofitTask extends AsyncTask<Void, Void, WeatherData> {

        OpenWeatherService mApi;
        Call<WeatherData> query;
        WeatherData response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApi = retrofit.create(OpenWeatherService.class);
            query = mApi.listWeather(
                    searchText.getText().toString(),
                    "json",
                    "metric",
                    "7",
                    API_KEY);
        }

        @Override
        protected WeatherData doInBackground(Void... params) {

            try {
                response = query.execute().body();
                if (response == null)
                    response = new WeatherData();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                response = new WeatherData();
            }
            return response;
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            super.onPostExecute(weatherData);

            mWeatherData = weatherData;
            mList = mWeatherData.getList();
            cityName.setText(searchText.getText().toString());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rvAdapter = new RVAdapter(mList);
            recyclerView.setAdapter(rvAdapter);

        }

    }
}
