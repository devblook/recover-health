package me.bryang.recoverhealth;

public class TextUtils {

    public static String replaceFirstFromIndex(String messageTarget, String charTarget, String newReplace){

        StringBuilder messageBuilder = new StringBuilder(messageTarget);

        int index = messageTarget.indexOf(charTarget);
        messageBuilder.replace(index, index + 1, newReplace);


        return messageBuilder.toString();
    }

    public static String replaceFirstFromIndex(String messageTarget, int index, String newReplace){

        StringBuilder messageBuilder = new StringBuilder(messageTarget);

        messageBuilder.replace(index, index + 1, newReplace);


        return messageBuilder.toString();
    }

    public static Object[] convertToArrayObject(String path){

        String[] pathList = path.split("\\.");

        Object[] objects = new Object[pathList.length];

        System.arraycopy(pathList, 0, objects, 0, pathList.length);

        return objects;
    }
}

