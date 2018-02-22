package com.feiniu.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feiniu.quartz.core.base.Task;
import com.feiniu.quartz.core.base.TaskContext;
import com.feiniu.quartz.core.base.TaskException;
import com.feiniu.quartz.core.base.TaskInterruptException;

public class HelloTask implements Task {

	private Logger log = LoggerFactory.getLogger(HelloTask.class);

	@Override
	public void execute(TaskContext taskcontext) throws TaskException {
//		log.info("-------------HelloTask-------------");
	}

	@Override
	public boolean isSupportInterrupted() {
		return false;
	}

	@Override
	public void interrupt() throws TaskInterruptException {
	}

}
