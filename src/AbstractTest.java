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
	//线程数
	public static int threadNum = 1;
	//任务数
	public static int taskNum = 20;
	//是否打印执行结果
	public static boolean isPrintResult = false;
	//测试次数
	public static int test_time = 5;
	
	public static void main(String arg[]) {
		//实例化线程池
		ExecutorService exec = Executors.newFixedThreadPool(threadNum);
		//实例化任务集合
		Set<Callable<String>> callableSet = new HashSet<Callable<String>>();
		//实例化结果集合
		List<Future<String>> returnList = null;
		try {
			//添加待执行任务
			for(int i = 0; i < taskNum; i++) {
				//根据测试人task更换【ThreadForTest】类
				Callable<String> tft = new ThreadForTest(String.valueOf(i));
				callableSet.add(tft);
			}
			for(int num = 0; num < test_time; num ++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
				//打印任务开始时间
				System.out.println(sdf.format(new Date()));
				long starTime=System.currentTimeMillis();
				//执行任务
				returnList = exec.invokeAll(callableSet);
				long endTime=System.currentTimeMillis();
				//打印任务结束时间
				System.out.println(sdf.format(new Date()));
				long Time=endTime-starTime;
				System.out.println("执行时间：" + Time + "毫秒");
			
				if(isPrintResult) {
					//打印结果
					System.out.println("===================执行结果start===================");
					for(Future<String> ftemp : returnList) {
						if(ftemp.isDone()) {
							System.out.println(ftemp.get());
						}
					}
					System.out.println("===================执行结果end===================");
				}
			}
			exec.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
