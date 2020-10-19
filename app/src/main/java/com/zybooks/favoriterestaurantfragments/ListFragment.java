package com.zybooks.favoriterestaurantfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class ListFragment extends Fragment {

    // For the activity to implement
    public interface OnRestaurantSelectedListener {
        void onRestaurantSelected(String restaurantLink);
    }

    // Reference to the activity
    private OnRestaurantSelectedListener Listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        LinearLayout layout = (LinearLayout) view;

        // Create the buttons using the band names and ids from RestaurantDatabase
        List<Restaurant> restaurantList = RestaurantDatabase.getInstance(getContext()).getBands();
        for (int i = 0; i < restaurantList.size(); i++) {
            Button button = new Button(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 10);   // 10 px
            button.setLayoutParams(layoutParams);

            // Set the text to the band's name and tag to the restaurant ID
            Restaurant restaurant = RestaurantDatabase.getInstance(getContext()).getRestaurant(i + 1);
            button.setText(restaurant.getName());
            button.setTag(restaurant.getLink());

            // All the buttons have the same click listener
            button.setOnClickListener(buttonClickListener);

            // Add the button to the LinearLayout
            layout.addView(button);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRestaurantSelectedListener) {
            Listener = (OnRestaurantSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRestaurantSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Listener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Notify activity of band selection
            String RestaurantLink = (String) view.getTag();
            Listener.onRestaurantSelected(RestaurantLink);
        }
    };
}