package com.yoprogramo.earthquake.mvvm;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.yoprogramo.earthquake.Earthquake;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import androidx.lifecycle.MutableLiveData;

public class FetchDataAsyncTask extends AsyncTask<Void, Void, List<Earthquake>> {
    private static final String TAG = "EarthquakesUpdate";
    private MutableLiveData<List<Earthquake>> earthquakes;

    private static final String QUAKE_FEED = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.atom";

    public FetchDataAsyncTask(MutableLiveData<List<Earthquake>> earthquakes) {
        this.earthquakes = earthquakes;
    }


    @Override
    protected List<Earthquake> doInBackground(Void... voids) {
        ArrayList<Earthquake> earthquakesList = new ArrayList<>(0);
        URL url;
        try {
            url = new URL(QUAKE_FEED);

            URLConnection connection;
            connection = url.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            int reponseCode = httpURLConnection.getResponseCode();

            if (reponseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpURLConnection.getInputStream();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                //Parse the earthquake feed.
                Document dom = db.parse(in);
                Element docEle = dom.getDocumentElement();

                //Get list of each earthquakes elements
                NodeList nl = docEle.getElementsByTagName("entry");
                if (nl != null && nl.getLength() > 0) {
                    for (int i = 0; i < nl.getLength(); i++) {
                        if (isCancelled()) {
                            Log.d(TAG, "doInBackground: " + "Loadig cancelled");
                            return earthquakesList;
                        }

                        Element entry = (Element) nl.item(i);
                        Element id = (Element) entry.getElementsByTagName("id").item(0);
                        Element title = (Element) entry.getElementsByTagName("title").item(0);
                        Element g = (Element) entry.getElementsByTagName("georss:point").item(0);
                        Element when = (Element) entry.getElementsByTagName("updated").item(0);
                        Element link = (Element) entry.getElementsByTagName("link").item(0);

                        String idString = id.getFirstChild().getNodeValue();
                        String detail = title.getFirstChild().getNodeValue();
                        String hostmane = "https://earthquake.usgs.gov";
                        String linkString = hostmane + link.getAttribute("href");
                        String point = g.getFirstChild().getNodeValue();

                        String dt = when.getFirstChild().getNodeValue();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                        Date qDate = new GregorianCalendar(0, 0, 0).getTime();
                        try {
                            qDate = sdf.parse(dt);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String location[] = point.split(" ");
                        Location l = new Location("dummyGps");
                        l.setLatitude(Double.parseDouble(location[0]));
                        l.setAltitude(Double.parseDouble(location[1]));

                        String magnitudeString = detail.split(" ")[1];
                        int end = magnitudeString.length() - 1;
                        double magnitude = Double.parseDouble(magnitudeString.substring(0, end));

                        if (detail.contains("-")) {
                            detail = detail.split("-")[1].trim();
                        } else {
                            detail = "";
                        }

                        final Earthquake earthquake = new Earthquake(idString, qDate, detail, new Location("0"), magnitude, linkString);
                        earthquakesList.add(earthquake);
                    }
                }
            }
            httpURLConnection.disconnect();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return earthquakesList;
    }

    @Override
    protected void onPostExecute(List<Earthquake> earthquakesList) {
        earthquakes.setValue(earthquakesList);
    }
}
