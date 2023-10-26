package com.korotkov.hackathon.service;

import com.korotkov.hackathon.util.CartesianCoordinates;
import com.korotkov.hackathon.util.GeocentricCoordinates;
import com.korotkov.hackathon.util.Latitude;
import com.korotkov.hackathon.util.Longitude;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.*;

@Service
public class CoordsService {

    private class Pair{
        double latitude;
        double longitude;

        public Pair(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    private final int EARTH_RADIUS = 6378100;

    public CartesianCoordinates geocentricToCartesian(String coords){
        String[] coordinates = coords.split(" ");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        double x = EARTH_RADIUS*cos(latitude)*cos(longitude);
        double y = EARTH_RADIUS*cos(latitude)*sin(longitude);
        double z = EARTH_RADIUS*sin(latitude);
        return new CartesianCoordinates(x, y, z);
    }

    private boolean containsLetter(String coords){
        Set<Character> symbols = new HashSet<>(){{
            add('S');
            add('N');
            add('E');
            add('W');
        }};

        for(int i = 0; i < coords.length(); i++){
            char sym = coords.charAt(i);
            if(symbols.contains(sym)){
                return true;
            }
        }

        return false;
    }

    public Pair coordsToDouble(GeocentricCoordinates geocentricCoordinates){
        Latitude latitude = geocentricCoordinates.getLatitude();
        Longitude longitude = geocentricCoordinates.getLongitude();

        double lat = latitude.getDegree() + latitude.getMinute()/60 + latitude.getSecond()/3600;
        if(latitude.getType() == Latitude.LatitudeType.S){
            lat *= -1;
        }

        double lon = longitude.getDegree() + longitude.getMinute()/60 + longitude.getSecond()/3600;
        if(longitude.getType() == Longitude.LongitudeType.W){
            lon *= -1;
        }

        return new Pair(lat, lon);
    }

}
