package in.nic.cmf.dynamicgenerator;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RandomValues implements Constants {   
    public final static int maxLength = 50;
    public static Random rand = new Random();

    /**
     * Return valid Email Id
     * 
     * @return
     */
    public static String getEmail() {
        return getEmail(false);
    }

    /**
     * Return Invalid Email Id.
     * 
     * @param valid
     *            true to get the bad email id.
     * @return
     */
    public static String getEmail(final boolean valid) {
        String emailId = Constants.emailIds[rand
                .nextInt(Constants.emailIds.length)];
        if (valid) {
            emailId = emailId + "@gma\\@il.c/&om";
        }
        return emailId;
    }

    /**
     * Return Alpha Numeric Text with Given Size.
     * 
     * @param sizeOfString
     * @return
     */
    public static String getAlphaNum(final int sizeOfString) {
        return getString(sizeOfString, false, true);
    }

    /**
     * Return Special Chars Alpha Numberic Test for the given Size.
     * 
     * @param sizeOfString
     * @param valid
     * @return
     */
    public static String getAlphaNum(final int sizeOfString, final boolean valid) {
        return getString(sizeOfString, false, true);
    }

    /**
     * Return Valid Readable String.
     * 
     * @param sizeOfString
     * @return
     */
    public static String getString(final int sizeOfString) {
        return getString(sizeOfString, false, false);
    }

    /**
     * Return String with Special Chars String.
     * 
     * @param sizeOfString
     * @param valid
     * @return
     */
    public static String getString(final int sizeOfString, final boolean valid) {
        return getString(sizeOfString, false, false);
    }

    /**
     * Return String as per the input parameters.
     * 
     * @param sizeOfString
     * @param valid
     * @param alpanumStatus
     * @return
     */
    public static String getString(final int sizeOfString, final boolean valid,
            final boolean alpanumStatus) {
        boolean terminate = true;
        String questions="";
        for (int i = 0; terminate; ++i) {
            if (alpanumStatus)
               questions += rand.nextInt(10000);
            questions += Constants.prefix[rand.nextInt(Constants.prefix.length)]                    
                    + Constants.wordsOne[rand
                            .nextInt(Constants.wordsOne.length)]                    
                    + Constants.wordsTwo[rand
                            .nextInt(Constants.wordsTwo.length)];                  
                   /* + Constants.wordsThr[rand
                            .nextInt(Constants.wordsThr.length)]+
                            Constants.wordsfour[rand.nextInt(Constants.wordsfour.length)];*/
                    
                                                  
            if (valid)
                questions += Constants.specialChars[rand
                        .nextInt(Constants.specialChars.length)]
                        + Constants.specialChars[rand
                                .nextInt(Constants.specialChars.length)]
                        + Constants.specialChars[rand
                                .nextInt(Constants.specialChars.length)]
                        + Constants.specialChars[rand
                                .nextInt(Constants.specialChars.length)]
                        + Constants.specialChars[rand
                                .nextInt(Constants.specialChars.length)]+
                                Constants.wordsfour[rand.nextInt(Constants.wordsfour.length)];
                               
            if (questions.length() > sizeOfString) {
                terminate = false;
                questions = questions.substring(0, sizeOfString);
                return questions;
            }
        }
        return questions;
    }

    /**
     * Return Integer Value for the given length.
     * 
     * @param length
     * @return
     */
    public static String getIntValues(int length) {    	
        return String.valueOf(rand.nextInt(length));
    }

    /**
     * Return negative Integers.
     * 
     * @param length
     * @param badvalues
     * @return
     */
    public static String getIntValues(int length, boolean badvalues) {
        return "-" + String.valueOf(rand.nextInt(length));
    }

    /**
     * Return Flot value for the given length.
     * 
     * @param length
     * @return
     */
    public static String getFloatValues(int length) {
        return String.valueOf(rand.nextInt(length) / 1.0);
    }

    /**
     * Return Valid Rand IP Address
     * 
     * @return
     */
    public static String getIp() {
        return getIp(false);
    }

    /**
     * Return Invalid IP Address.
     * 
     * @param valid
     * @return
     */
    public static String getIp(boolean valid) {
        String random = rand.nextInt(255) + "." + rand.nextInt(255) + "."
                + rand.nextInt(255) + ".";
        if (valid)
            return random + "-" + rand.nextInt(3558);
        else
            return random + rand.nextInt(255);
    }

    /**
     * Return Valid Mime Type
     * 
     * @return
     */
    public static String getMimeType() {
        return getMimeType(false);
    }

    /**
     * Return In-Valid Mime Type
     * 
     * @param valid
     * @return
     */
    public static String getMimeType(boolean valid) {
        String[] mimeType = { "application/xml", "application/zip",
                "text/plain", "image/jpeg" };
        if (valid) {
            return mimeType[rand.nextInt(mimeType.length)]
                    + "\\thisi fir testing";
        } else {
            return mimeType[rand.nextInt(mimeType.length)];

        }
    }

    /**
     * Return Valid Md5 Value Type
     * 
     * @return
     */
    public static String getMd5(String inputValue,int length) {
        return getMd5(inputValue, false).substring(0,length);
    }

    /**
     * Return In Valid Md5 Value Type
     * 
     * @return
     */
    public static String getMd5(String inputValue, boolean valid) {
        try {
            if (valid)
                return getMD5(inputValue);
            else
                return getMD5(inputValue)+"SIFY!@#";
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputValue;
    }

    /**
     * Generate Md5 value
     * 
     * @param stringInput
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static final String getMD5(final String stringInput)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final Integer ffffCons = 0xffffff00;
        final Integer ffCons = 0x000000ff;
        final int subString = 6;
        String uriInfo = stringInput;
        String uriInfoResult = "";
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(uriInfo.getBytes("UTF8"));
        byte[] s = m.digest();
        for (int i = 0; i < s.length; i++) {
            uriInfoResult += Integer.toHexString((ffCons & s[i]) | ffffCons)
                    .substring(subString);
        }
        return uriInfoResult;
    }

    
    
    public static void main(String[] args) {
        // q = ;
         System.out.println(RandomValues.getString(200));
         System.out.println(RandomValues.getString(200));
    }
}
