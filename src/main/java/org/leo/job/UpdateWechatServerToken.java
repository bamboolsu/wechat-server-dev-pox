package org.leo.job;


import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
@Lazy(false)
public class UpdateWechatServerToken {  
	
      @Scheduled(cron="5 * * *  *  * ?")   //每5秒执行一次  
      public void myTest(){  
            System.out.println("lsu:   begin myTest; 进入测试");  
      }  
      
  	@Scheduled(fixedDelayString = "${job.static_generate_index.delay}")
  	public void generateIndex() {
  		System.out.println("lsu:   begin generateIndex; 进入测试");  
  	}
}  