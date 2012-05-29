package in.nic.cmf.formatters;

public class FormatFactory {

    public static Object getFormatInstance(String format)
	    throws ClassNotFoundException, InstantiationException,
	    IllegalAccessException {
	String className = "Format" + format.toUpperCase();
	System.out.println("classname:"+className+";format:"+format);
	Class classToLoad = Class.forName("in.nic.cmf.formatters."
		+ className);
	Object obj = classToLoad.newInstance();
	System.out.println("returned new class instance");
	return obj;
    }

}
