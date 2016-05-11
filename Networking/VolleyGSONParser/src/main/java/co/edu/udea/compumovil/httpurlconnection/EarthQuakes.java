package co.edu.udea.compumovil.httpurlconnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaiber on 22/04/16.
 */
public class EarthQuakes {

    private ArrayList<EarthQuake> earthQuakeList;


    public ArrayList<EarthQuake> getEarthQuakeList() {
        return earthQuakeList;
    }

    public void setEarthQuakeList(ArrayList<EarthQuake> earthQuakeList) {
        this.earthQuakeList = earthQuakeList;
    }

    @Override
    public String toString() {
        return "EarthQuakes [EarthQuakes=" + earthQuakeList + "]";
    }
}
