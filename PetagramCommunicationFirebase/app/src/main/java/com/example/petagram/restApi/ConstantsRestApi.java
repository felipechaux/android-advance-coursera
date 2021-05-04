package com.example.petagram.restApi;

final public class ConstantsRestApi {

    public static final Long TIME_OUT=30L;
    public static final String BASE_URL="https://graph.instagram.com/";
    public static final String ACCESS_TOKEN="&access_token=IGQVJXX0hpa2Ryb2tQRnBCdHFvdDk4OGllM0YweUtLVWFjWFQxYTBlRTFvTzdtMDhpaHpKX0EtN2lyY2NDMzA1eTdsUU1XeFFabGhNSTRQVkg0eGV6Ynlud2xFdEV0SV9SdnJySDh0V29QWnJQQ0s5VAZDZD";
    public static final String KEY_GET_RECENT_INFO="me/media?fields=id,media_url,media_type";
    public static final String URL_GET_RECENT_INFO=BASE_URL+KEY_GET_RECENT_INFO+ACCESS_TOKEN;

    public static final String FIREBASE_SERVER_URL = "https://agile-tor-51409.herokuapp.com/";
    public static final String KEY_POST_REGISTER_USER = "registrar-usuario";
    public static final String URL_FIREBASE_REGISTER_USER = FIREBASE_SERVER_URL+KEY_POST_REGISTER_USER;


}
