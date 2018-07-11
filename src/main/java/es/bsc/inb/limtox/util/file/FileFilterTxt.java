package es.bsc.inb.limtox.util.file;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilterTxt  implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".xml");
	}
	
}
