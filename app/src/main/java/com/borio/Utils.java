package com.borio;

import android.content.res.Resources;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static final Map<String, Integer> providersImages = new HashMap<>();
    public static final String newProviderInfo = "New Password";
    public static final String logOut = "Log Out";
    public static final String aboutUS = "Who Are We?";
    public static final String aboutApp = "What Is This?";
    public static final SecureRandom rnd = new SecureRandom();
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String serverAddress = "http://192.168.1.3";
    public static int serverPort = 7575;
    public static String serverURL = serverAddress + ":" + serverPort;

    static {
        providersImages.put("provider", R.drawable.provider);
        providersImages.put("anghami", R.drawable.anghami);
        providersImages.put("facebook", R.drawable.facebook);
        providersImages.put("gmail", R.drawable.gmail);
        providersImages.put("google", R.drawable.google);
        providersImages.put("instagram", R.drawable.instagram);
        providersImages.put("medium", R.drawable.medium);
        providersImages.put("quora", R.drawable.quora);
        providersImages.put("soundcloud", R.drawable.soundcloud);
        providersImages.put("spotify", R.drawable.spotify);
        providersImages.put("twitter", R.drawable.twitter);
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
