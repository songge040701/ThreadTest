import java.util.concurrent.Callable;

public class ThreadForTest1 implements Callable<String> {

	String countStr = "";
	
	public ThreadForTest1(String countStr) {
		this.countStr = countStr;
	}

	@Override
	public String call() throws Exception {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < 10000; i++) {
			s.append("123");
		}
		// TODO Auto-generated method stub
		return countStr;
	}

}
