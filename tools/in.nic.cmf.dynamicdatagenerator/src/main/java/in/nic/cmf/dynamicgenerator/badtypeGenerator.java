package in.nic.cmf.dynamicgenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class badtypeGenerator implements Generator{
	String[] fileExtension={".css",".txt",".doc",".pdf",".xls"};
	public String getId(int length) {		
		return "-!@#<<>><_)*(&&&&&*&";
	}
	public String getUrl(int length) {
		
		return  RandomValues.getString(length);
	}
	public String getEmail(int length) {
	    return RandomValues.getIntValues(length);
	}
	 public String getDate(int length) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSSS'Z'");
	        return df.format(new Date()).toString();
	    }
    @Override
    public String getString(int length) {
        return RandomValues.getIntValues(length);
    }
    @Override
    public String getInt(int length) {
        // TODO Auto-generated method stub
        return  RandomValues.getString(length);
    }
    @Override
    public String getFloat(int length) {
        // TODO Auto-generated method stub
        return  RandomValues.getString(length);
    }
	@Override
	public String getBoolean(int length) {
		// TODO Auto-generated method stub
		return RandomValues.getIntValues(length);
	}
    @Override
    public String getAlphanum(int length) {
        // TODO Auto-generated method stub
        return RandomValues.getIntValues(length);
    }
    @Override
    public String getIp(int length) {
        // TODO Auto-generated method stub
        return  RandomValues.getIntValues(length);
    }
    @Override
    public String getMimetype(int length) {
        // TODO Auto-generated method stub
        return  RandomValues.getIntValues(length);
    }
    @Override
    public String getMd5(int length) {
        // TODO Auto-generated method stub
        return  RandomValues.getIntValues(length);
    }
	@Override
	public String getAlpha(int length) {
		// TODO Auto-generated method stub
		return RandomValues.getIntValues(length);
	}
	@Override
	public String getNumeric(int length) {
		// TODO Auto-generated method stub
		return RandomValues.getString(length);
	}
	@Override
	public String getLanguage(int length) {
		// TODO Auto-generated method stub
		return RandomValues.getIntValues(length);
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
