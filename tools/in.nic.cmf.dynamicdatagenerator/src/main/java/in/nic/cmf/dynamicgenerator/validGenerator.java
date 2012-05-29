package in.nic.cmf.dynamicgenerator;

import in.nic.cmf.uniqueid.Uniqueid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class validGenerator implements Generator 
{
	String[] fileExtension={".css",".txt",".doc",".pdf",".xls"};

    public String getId(int length) {
        return Uniqueid.getId();
    }

    public String getUrl(int length) {

        return "http://test.com";
    }

    public String getEmail(int length) {
        return RandomValues.getEmail();
    }

    public String getDate(int length) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return df.format(new Date()).toString();
    }

    @Override
    public String getString(int length) {
        return RandomValues.getString(length,true);
    }

    @Override
    public String getInt(int length) {
        return RandomValues.getIntValues(length);
    }

    @Override
    public String getFloat(int length) {
        return RandomValues.getFloatValues(length);
    }
    
    public static void main(String args[]){
    	validGenerator val=new validGenerator();
    	System.out.println(val.getDate(10));
    	System.out.println(val.getMd5(32));
    	System.out.println(val.getAlpha(30));
    	System.out.println(val.getString(4000));
    }

	@Override
	public String getBoolean(int length) {		
		return "true";
	}

    @Override
    public String getAlphanum(int length) {       
        return RandomValues.getAlphaNum(length, true);
    }

    @Override
    public String getIp(int length) {        
        return RandomValues.getIp(true);
    }

    @Override
    public String getMimetype(int length) {        
        return "text/plain";
    }

    @Override
    public String getMd5(int length) {     
        return RandomValues.getMd5("test",length);
    }

	@Override
	public String getAlpha(int length) {		
		return RandomValues.getString(length);
	}

	@Override
	public String getNumeric(int length) {		
		return RandomValues.getIntValues(length);
	}

	@Override
	public String getLanguage(int length) {	
		return "ta";
	}

	@Override
	public String getFile(int length) {
		Random rs=new Random();
		return RandomValues.getString(10)+fileExtension[rs.nextInt(4)];
	}

	@Override
	public String getAllowedFormat(int length, String fileFormat) {
		Random rs=new Random();
		return RandomValues.getString(10)+"."+fileFormat;
	}

	 public String getDateForm(int length) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	        return df.format(new Date()).toString();
	    }


	

}
