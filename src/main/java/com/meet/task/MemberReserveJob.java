package com.meet.task;

import com.meet.common.constants.OrderConstants;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Title: MemberReserveJob.java
 * @Package com.order.task
 * @Description: TODO(用户预定门店时间过期轮询)
 * @author baizhixing
 * @date 2017年7月5日 上午10:59:31
 * @version V1.0
 */
public class MemberReserveJob extends QuartzJobBean {

	private final Logger logger = LoggerFactory.getLogger(OrderConstants.LOG_NAME);

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		// 获取大于当前时间的预定信息
		logger.info("..........................开始轮询获取用户预定数据....................");
		logger.info("..........................结束轮询获取用户预定数据....................");
	}

	
}
