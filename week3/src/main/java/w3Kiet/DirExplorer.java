package w3Kiet;

import java.io.File;

public class DirExplorer {
	public interface FileHandler{
		void handle(int level, String path, File file);
		
	}
	
	public interface Filter{
		boolean interested(int level, String path, File file);
	}

	private FileHandler fileHandler;
	
	private Filter filter;


	
	public DirExplorer( Filter filter,FileHandler fileHandler) {
		this.filter = filter;
		this.fileHandler = fileHandler;
	
	}
	


	public void explore(File root) {
		explore(0, "", root);
	}

	private void explore(int level, String path, File file) {
		if(file.isDirectory()) {
			for(File child : file.listFiles()) {
				explore(level + 1, path + "/" + child.getName(), child);
			}
		}else {
			if(filter.interested(level, path, file)) {
				fileHandler.handle(level, path, file);
			}
		}
		
	}
	
	public static void main(String[] args) {
		//Ex1:
		
//		File projectDir = new File("T:\\ThucHanh\\json-demo");
//		new DirExplorer(
//				(level, path, file) -> path.endsWith("com.companyname.*"),
//				(level, path, file) ->{
//					System.out.println(path);
//				})
		
		old_style();
	}

	private static void old_style() {
		File projectDir = new File("T:\\ThucHanh\\json-demo");
		Filter filter = new Filter() {
			@Override
			public boolean interested(int level, String path, File file) {
				
				return path.endsWith(".java");
			}
		};
		FileHandler handler = new FileHandler() {
			
			@Override
			public void handle(int level, String path, File file) {
				// TODO Auto-generated method stub
				System.out.println(path);
			}
		};
		DirExplorer di = new DirExplorer(filter,handler);
		di.explore(projectDir);
		
	}
	
	
}
