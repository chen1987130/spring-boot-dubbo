package com.feiniu.quartz.core.base;

/**
 * 任务抽象类，所有任务必须继承Task或者AbstractTask
 * 
 * @author chensheng
 *
 */
public abstract class AbstractTask implements Task {
	public AbstractTask() {
		interrupted = false;
	}

	public void interrupt() throws TaskInterruptException {
		if (isSupportInterrupted())
			interrupted = true;
	}

	public boolean isSupportInterrupted() {
		return false;
	}

	public boolean isInterrupted() {
		return interrupted;
	}

	private boolean interrupted;

}
