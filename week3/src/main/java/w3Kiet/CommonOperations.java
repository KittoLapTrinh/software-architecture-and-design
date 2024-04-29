package w3Kiet;

import java.io.File;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

public class CommonOperations {
	public static void sample(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java") , (level, path, file)->{
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);
						String clsName = n.getNameAsString();
						char c = clsName.charAt(0);
						if(c > 'a' && c < 'z') {
							//report
							System.out.println("===Invalid class name=== : " + clsName+ ";" + " Posistion [" + n.getBegin()+ " , ]" + n.getEnd());
						}
					}
				}.visit(StaticJavaParser.parse(file),null);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}).explore(projectDir);
	}
	
	static boolean checkLegalPackage(String pkgName) {
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		File projectDir = new File("T:\\ThucHanh\\json-demo");
		new CommonOperations();
		CommonOperations.sample(projectDir);
	}
	
}
