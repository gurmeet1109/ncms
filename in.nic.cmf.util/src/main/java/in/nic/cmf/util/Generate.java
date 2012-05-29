package in.nic.cmf.util;

import java.util.List;

//included on 10th Sep 2011 by kavitha
public interface Generate {
	
	boolean remove(String rule);
	boolean removeall(List<String> rules);
	boolean add(String rule);
	boolean addall(List<String> rules);
}
