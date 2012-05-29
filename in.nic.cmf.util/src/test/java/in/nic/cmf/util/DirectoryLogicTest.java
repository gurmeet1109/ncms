package in.nic.cmf.util;

import junit.framework.TestCase;

public class DirectoryLogicTest extends TestCase {

	public void testListFilesFromDirectory() {
		DirectoryLogic dl = DirectoryLogic.getInstanceOf("bala.in");
		dl.listFilesFromDirectory("/opt/cmf/domains", "accesscontrollist");
	}

	public void testCreateDirectoryIfNotExists() {
		fail("Not yet implemented");
	}

	public void testFindInnerchild() {
		fail("Not yet implemented");
	}

}
