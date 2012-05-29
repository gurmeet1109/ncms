package in.nic.cmf.dynamicgenerator;

import java.util.Date;
import java.util.List;

public interface Generator {	
	public String getId(int length);	
	public String getUrl(int length);	
	public String getEmail(int length);	
	public String getDate(int length);
	public String getString(int length);
	public String getInt(int length);
	public String getFloat(int length);
	public String getBoolean(int length);
	public String getAlphanum(int length);
	public String getIp(int length);
	public String getMimetype(int length);
	public String getMd5(int length);
	public String getAlpha(int length);
	public String getNumeric(int length);
	public String getLanguage(int length);
	public String getFile(int length);
	public String getAllowedFormat(int length,String fileFormat);
	public String getDateForm(int length);
}
