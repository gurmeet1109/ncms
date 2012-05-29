package in.nic.cmf.services.ruleengine;

import in.nic.cmf.util.Generate;

import java.util.ArrayList;
import java.util.List;

public class RuleGenerateImpl implements Generate{

	RuleEngineImpl ruleEngine = RuleEngineImpl.getInstance("generate");
	
	public RuleGenerateImpl(){
		System.out.println("inside constr-RuleGenerateImpl");
	}

	public boolean remove(String rule) {
		List<String> rules = new ArrayList<String>();
		rules.add(rule);
		ruleEngine.readKnowlegeBase("remove",rules);
		return true ;
	}

	public boolean removeall(List<String> rules) {
		ruleEngine.readKnowlegeBase("removeall",rules);
		return true;
	}

	public boolean add(String rule) {
		List<String> rules = new ArrayList<String>();
		rules.add(rule);
		ruleEngine.readKnowlegeBase("add",rules);
		return true;
	}
	
	public boolean addRules(List<String> rules) {
		System.out.println("addRules inside");
		ruleEngine.readKnowlegeBase("addrule",rules);
		return true;
	}

	public boolean addall(List<String> rules) {
		ruleEngine.readKnowlegeBase("addall",rules);
		return true;
	}

}
