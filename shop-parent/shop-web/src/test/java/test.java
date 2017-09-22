import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();  
		BasicDataSource basicDataSource = (BasicDataSource)context.getBean("dataSource");
		
		System.out.println(
				basicDataSource.getUrl() + " " +
				basicDataSource.getUsername() + " " + 
				basicDataSource.getPassword()
				);
		
	}
}
