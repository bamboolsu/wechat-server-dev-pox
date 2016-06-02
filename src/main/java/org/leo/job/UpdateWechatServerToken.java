package org.leo.job;


import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component 
//@Lazy(false)
public class UpdateWechatServerToken {  
	
      //@Scheduled(cron="* * 1  *  * ?")   //每5秒执行一次  
      public void myTest(){  
            System.out.println("lsu:   begin myTest; 进入测试");  
      }  
      
  	//@Scheduled(fixedDelayString = "${job.static_refresh_wechat_server.delay}")
  	public void refreshWechatServerToken() {
  		System.out.println("lsu:   begin refreshWechatServerToken; 进入测试");  
  	}
}  