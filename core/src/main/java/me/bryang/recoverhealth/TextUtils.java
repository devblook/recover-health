package me.bryang.recoverhealth;

public class TextUtils {

    public static String replaceFirst(String messageTarget, String charTarget, String newReplace){

        StringBuilder messageBuilder = new StringBuilder(messageTarget);

        int index = messageTarget.indexOf(charTarget);
        messageBuilder.replace(index, index + 1, newReplace);


        return messageBuilder.toString();
    }
    public static String replaceFirst(String messageTarget, String newReplace, char... charTargets){

        StringBuilder messageBuilder = new StringBuilder(messageTarget);

        for (char charTarget : charTargets){

            int index = messageTarget.indexOf(charTarget);
            messageBuilder.replace(index, index + 1, newReplace);
        }

        return messageBuilder.toString();
    }
}
