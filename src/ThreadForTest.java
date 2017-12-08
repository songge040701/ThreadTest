import java.util.concurrent.Callable;

public class ThreadForTest implements Callable<String> {

	String countStr = "";
	
	public ThreadForTest(String countStr) {
		this.countStr = countStr;
	}

	@Override
	public String call() throws Exception {
		String s = "";
		for(int i = 0; i < 10000; i++) {
			s = s + "123";
		}
		// TODO Auto-generated method stub
		return countStr;
	}

}
