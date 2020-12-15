/**
 * Utilities for Reservations.
 */
package com.knu.medifree.util;

import android.content.Context;

import com.knu.medifree.model.Reservation;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReservationUtil {
    private static final String TAG = "ReservationUtil";

    /**
     * Create a random Reservation
     */
    public static Reservation getSomeReservation(Context context) {
        Reservation reservation = new Reservation(
                "oYnAfFJe8XXih2klptUueBnwtic2"
                , "0qS2tC9fLfYEPlR5A4IuE7DUR3E2"
                , "2020/12/12/00/00"
        );

        return reservation;
    }



//
//    /**
//     * Get a random image.
//     */
//    private static String getRandomImageUrl(Random random) {
//        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
//        int id = random.nextInt(MAX_IMAGE_NUM) + 1;
//
//        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id);
//    }
//
//    /**
//     * Get price represented as dollar signs.
//     */
//    public static String getPriceString(Restaurant restaurant) {
//        return getPriceString(restaurant.getPrice());
//    }
//
//    /**
//     * Get price represented as dollar signs.
//     */
//    public static String getPriceString(int priceInt) {
//        switch (priceInt) {
//            case 1:
//                return "$";
//            case 2:
//                return "$$";
//            case 3:
//            default:
//                return "$$$";
//        }
//    }
//
//    private static double getRandomRating(Random random) {
//        double min = 1.0;
//        return min + (random.nextDouble() * 4.0);
//    }
//
//    private static String getRandomName(Random random) {
//        return getRandomString(NAME_FIRST_WORDS, random) + " "
//                + getRandomString(NAME_SECOND_WORDS, random);
//    }
//
//    private static String getRandomString(String[] array, Random random) {
//        int ind = random.nextInt(array.length);
//        return array[ind];
//    }
//
//    private static int getRandomInt(int[] array, Random random) {
//        int ind = random.nextInt(array.length);
//        return array[ind];
//    }
}


