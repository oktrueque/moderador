package com.oktrueque.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Constants {

    public static final int TRUEQUE_STATUS_PENDING = 0;
    public static final String TRUEQUE_STATUS_NAME_PENDING = "Pendiente";
    public static final int TRUEQUE_STATUS_ACTIVE = 1;
    public static final String TRUEQUE_STATUS_NAME_ACTIVE = "Activo";
    public static final int TRUEQUE_STATUS_REJECTED = 2;
    public static final String TRUEQUE_STATUS_NAME_REJECTED = "Rechazado";
    public static final int TRUEQUE_STATUS_CANCELED = 3;
    public static final String TRUEQUE_STATUS_NAME_CANCELED = "Cancelado";
    public static final int TRUEQUE_STATUS_CONFIRMED = 4;
    public static final String TRUEQUE_STATUS_NAME_CONFIRMED = "Confirmado";

    public static final int ITEM_STATUS_PENDING = 0;
    public static final String ITEM_STATUS_NAME_PENDING = "Pendiente";
    public static final int ITEM_STATUS_ACTIVE = 1;
    public static final String ITEM_STATUS_NAME_ACTIVE = "Activo";
    public static final int ITEM_STATUS_DELETED = 2;
    public static final String ITEM_STATUS_NAME_DELETED = "Borrado";
    public static final int ITEM_STATUS_BANNED = 3;
    public static final String ITEM_STATUS_NAME_BANNED = "Banneado";
    public static final int ITEM_STATUS_EXCHANGED = 4;
    public static final String ITEM_STATUS_NAME_EXCHANGED = "Trocado";

    public static final int COMPLAINT_STATUS_PENDING = 0;
    public static final String COMPLAINT_STATUS_NAME_PENDING = "Pendiente";
    public static final int COMPLAINT_STATUS_PROCESSING = 1;
    public static final String COMPLAINT_STATUS_NAME_PROCESSING = "En Moderacion";
    public static final int COMPLAINT_STATUS_READY = 2;
    public static final String COMPLAINT_STATUS_NAME_READY = "Moderada";

    public static final int USER_STATUS_REGISTERED = 0;
    public static final String USER_STATUS_NAME_REGISTERED = "Registrado";
    public static final int USER_STATUS_ACTIVE = 1;
    public static final String USER_STATUS_NAME_ACTIVE = "Activo";
    public static final int USER_STATUS_DELETED = 2;
    public static final String USER_STATUS_NAME_DELETED = "Eliminado";
    public static final int USER_STATUS_BANNED = 3;
    public static final String USER_STATUS_NAME_BANNED = "Baneado";

    public static String getUserStatusName(int key){
        switch(key){
            case USER_STATUS_REGISTERED:
                return USER_STATUS_NAME_REGISTERED;
            case USER_STATUS_ACTIVE:
                return USER_STATUS_NAME_ACTIVE;
            case USER_STATUS_DELETED:
                return USER_STATUS_NAME_DELETED;
            case USER_STATUS_BANNED:
                return USER_STATUS_NAME_BANNED;
        }
        return "Error";
    }

    public static String getTruequeStatusName(int key){
        switch(key){
            case TRUEQUE_STATUS_PENDING:
                return TRUEQUE_STATUS_NAME_PENDING;
            case TRUEQUE_STATUS_ACTIVE:
                return TRUEQUE_STATUS_NAME_ACTIVE;
            case TRUEQUE_STATUS_REJECTED:
                return TRUEQUE_STATUS_NAME_REJECTED;
            case TRUEQUE_STATUS_CANCELED:
                return TRUEQUE_STATUS_NAME_CANCELED;
            case TRUEQUE_STATUS_CONFIRMED:
                return TRUEQUE_STATUS_NAME_CONFIRMED;
        }
        return "Error";
    }
    public static String getItemStatusName(int key){
        switch (key){
            case ITEM_STATUS_PENDING:
                return ITEM_STATUS_NAME_PENDING;
            case ITEM_STATUS_ACTIVE:
                return ITEM_STATUS_NAME_ACTIVE;
            case ITEM_STATUS_DELETED:
                return ITEM_STATUS_NAME_DELETED;
            case ITEM_STATUS_BANNED:
                return ITEM_STATUS_NAME_BANNED;
            case ITEM_STATUS_EXCHANGED:
                return ITEM_STATUS_NAME_EXCHANGED;
        }
        return "Error";
    }


    public static String getComplaintStatusName(int key){
        switch (key){
            case COMPLAINT_STATUS_PENDING:
                return COMPLAINT_STATUS_NAME_PENDING;
            case COMPLAINT_STATUS_PROCESSING:
                return COMPLAINT_STATUS_NAME_PROCESSING;
            case COMPLAINT_STATUS_READY:
                return COMPLAINT_STATUS_NAME_READY;
        }
        return "Error";
    }

    private static final Map<Integer, String> defaultProfilePictures;
    static
    {
        defaultProfilePictures = new HashMap<>();
        defaultProfilePictures.put(1, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-4");
        defaultProfilePictures.put(2, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-2");
        defaultProfilePictures.put(3, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-3");
        defaultProfilePictures.put(4, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-4");
        defaultProfilePictures.put(5, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-5");
        defaultProfilePictures.put(6, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-6");
        defaultProfilePictures.put(7, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-7");
        defaultProfilePictures.put(8, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-8");
        defaultProfilePictures.put(9, "https://oktrueque.s3-sa-east-1.amazonaws.com/default/U-9");
    }

    public static String returnRandomImage(){
        Random r = new Random();
        int Low = 1;
        int High = 10;
        int result = r.nextInt(High-Low) + Low;
        return defaultProfilePictures.get(result);
    }
}
