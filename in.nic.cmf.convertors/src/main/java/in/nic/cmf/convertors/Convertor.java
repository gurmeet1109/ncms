package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;

import java.io.File;
import java.util.HashMap;

public interface Convertor {

	public HashMap<String, Object> in(String o) throws GenericException;

	public HashMap<String, Object> in(File o) throws GenericException;

	public HashMap<String, Object> in(Object o) throws GenericException;

	public GenModel in(String o, GenModel g) throws GenericException;
	
	public GenModel in(HashMap <String, Object> h, GenModel g) throws GenericException;

	public Object out(HashMap<String, Object> map) throws GenericException;

	public Object out(GenModel genmodel) throws GenericException;
}
