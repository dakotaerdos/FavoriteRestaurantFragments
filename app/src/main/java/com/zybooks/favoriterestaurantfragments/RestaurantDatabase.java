package com.zybooks.favoriterestaurantfragments;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDatabase {

    private static RestaurantDatabase RestaurantDatabase;
    private List<Restaurant> Restaurants;


    public static RestaurantDatabase getInstance(Context context) {
        if (RestaurantDatabase == null) {
            RestaurantDatabase = new RestaurantDatabase(context);
        }
        return RestaurantDatabase;
    }

    private RestaurantDatabase(Context context) {
        Restaurants = new ArrayList<>();
        Resources res = context.getResources();
        String[] restaurants = res.getStringArray(R.array.restaurants);
        String[] links = res.getStringArray(R.array.links);
        for (int i = 0; i < restaurants.length; i++) {
            Restaurants.add(new Restaurant(i + 1, restaurants[i], links[i]));
        }
    }

    public List<Restaurant> getBands() {
        return Restaurants;
    }

    public Restaurant getRestaurant(int RestaurantId) {
        for (Restaurant restaurant : Restaurants) {
            if (restaurant.getId() == RestaurantId) {
                return restaurant;
            }
        }
        return null;
    }
}
