package com.mac.chris.openweathermapretrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mac.chris.openweathermapretrofit.data.WeatherData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by chris on 5/2/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

List<com.mac.chris.openweathermapretrofit.data.List> forecastList;
    WeatherData weatherData;

    public RVAdapter(List<com.mac.chris.openweathermapretrofit.data.List> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_weather, parent, false);
        return new RVViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, int i) {
        holder.day.setText("Day "+(i+1));
        holder.temp.setText(String.valueOf(forecastList.get(i).getTemp().getDay())+"c");
        holder.maxTemp.setText("Max: "+String.valueOf(forecastList.get(i).getTemp().getMax()));
        holder.minTemp.setText("Min: "+String.valueOf(forecastList.get(i).getTemp().getMin()));
        holder.humidity.setText("Humidity: "+String.valueOf(forecastList.get(i).getHumidity()));
        holder.pressure.setText("Pressure: "+String.valueOf(forecastList.get(i).getPressure()));
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder{

        TextView minTemp;
        TextView maxTemp;
        TextView temp;
        TextView humidity;
        TextView pressure;
        TextView day;


        public RVViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.dayTextView);
            minTemp = (TextView) itemView.findViewById(R.id.minTemptextView);
            maxTemp = (TextView) itemView.findViewById(R.id.MaxTemptextView);
            temp = (TextView) itemView.findViewById(R.id.tempTextView);
            humidity = (TextView) itemView.findViewById(R.id.humiditytextView3);
            pressure = (TextView) itemView.findViewById(R.id.pressureTextView);

        }
    }
}
