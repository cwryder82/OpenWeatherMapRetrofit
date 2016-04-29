package com.mac.chris.openweathermapretrofit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mac.chris.openweathermapretrofit.data.WeatherData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String API_KEY = "a834182bf0de3d05f5887b94e4f14b96";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        RetrofitTask rt = new RetrofitTask();
        rt.execute();
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
            query = mApi.listWeather();
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
            textView.setText(weatherData.getCity().getName());
        }

    }
}
