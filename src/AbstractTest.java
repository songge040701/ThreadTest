import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AbstractTest {
	//�߳���
	public static int threadNum = 1;
	//������
	public static int taskNum = 20;
	//�Ƿ��ӡִ�н��
	public static boolean isPrintResult = false;
	//���Դ���
	public static int test_time = 5;
	
	public static void main(String arg[]) {
		//ʵ�����̳߳�
		ExecutorService exec = Executors.newFixedThreadPool(threadNum);
		//ʵ�������񼯺�
		Set<Callable<String>> callableSet = new HashSet<Callable<String>>();
		//ʵ�����������
		List<Future<String>> returnList = null;
		try {
			//��Ӵ�ִ������
			for(int i = 0; i < taskNum; i++) {
				//���ݲ�����task������ThreadForTest����
				Callable<String> tft = new ThreadForTest(String.valueOf(i));
				callableSet.add(tft);
			}
			for(int num = 0; num < test_time; num ++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
				//��ӡ����ʼʱ��
				System.out.println(sdf.format(new Date()));
				long starTime=System.currentTimeMillis();
				//ִ������
				returnList = exec.invokeAll(callableSet);
				long endTime=System.currentTimeMillis();
				//��ӡ�������ʱ��
				System.out.println(sdf.format(new Date()));
				long Time=endTime-starTime;
				System.out.println("ִ��ʱ�䣺" + Time + "����");
			
				if(isPrintResult) {
					//��ӡ���
					System.out.println("===================ִ�н��start===================");
					for(Future<String> ftemp : returnList) {
						if(ftemp.isDone()) {
							System.out.println(ftemp.get());
						}
					}
					System.out.println("===================ִ�н��end===================");
				}
			}
			exec.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
