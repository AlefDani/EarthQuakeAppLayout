package com.example.alefdani.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        Earthquake current = (Earthquake) getItem(position);
        //set mag
        DecimalFormat format = new DecimalFormat("0.0");
        String mag = format.format(current.getMagnitude());
        TextView magText = (TextView) listItemView.findViewById(R.id.magnitude);
        magText.setText(mag);



    TextView genLoc = (TextView) listItemView.findViewById(R.id.near_location);
    genLoc.setText(part1(current.getLocation()));
    // set precise location

    TextView precLoc = (TextView) listItemView.findViewById(R.id.location);
    precLoc.setText(part2(current.getLocation()));


    Date dateRaw = new Date(current.getDate());
    //set date
    TextView date = (TextView) listItemView.findViewById(R.id.date);
    String dateString = toDateString(dateRaw);
                date.setText(dateString);
    //set time
    TextView time = (TextView) listItemView.findViewById(R.id.time);
    String timeString = toTimeString(dateRaw);
                time.setText(timeString);

                return listItemView;
}

    private String toDateString(Date date) {
        SimpleDateFormat dateS = new SimpleDateFormat("MMM DD, yyyy");
        String dateFinal = dateS.format(date);
        return dateFinal;
    }

    private String toTimeString(Date date) {
        SimpleDateFormat dateS = new SimpleDateFormat("h:mm, a");
        String dateFinal = dateS.format(date);
        return dateFinal;
    }
    private String part1(String str){
        String subString1 ="";
        int ind = 0;
        if (str.contains("of")){
            int k = 0;
            String[] arr = str.split("\\s+");
            for (int i = 0;i<arr.length;i++){
                if (arr[i].matches("of")){
                    ind = i;
                }
            }
            while(k<ind){
                subString1+=arr[k]+" ";
                k++;
            }
        }else{
            subString1 = "Near of";
        }
      return subString1;
    }


    private String part2(String str){
        String subString2 ="";
        int ind = 0;
        if (str.contains("of")){
            String[] arr = str.split("\\s+");
            int k = arr.length;
            for (int i = 0;i<arr.length;i++){
                if (arr[i].matches("of")){
                    ind = i;
                }
            }
            while(ind+1<arr.length){
                subString2+=arr[ind+1]+" ";
                ind++;
            }
        }else{
            subString2 = str;
        }
        return subString2;
    }
}
