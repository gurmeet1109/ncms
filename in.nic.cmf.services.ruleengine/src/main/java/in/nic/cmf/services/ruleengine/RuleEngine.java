package in.nic.cmf.services.ruleengine;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.Storable;
import in.nic.cmf.exceptions.GenericException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Interface IRuleEngine.
 */
public interface RuleEngine {

	public Map validate(Collections<Storable> collections,String uniqueJson,List<Map> duplicateCheckMap) throws GenericException;
	
    public boolean deleteDuplicate(String domainName, String entityType, String id);
    
    public boolean generateForDuplication(List<Map> tmpDetails);
        
}
