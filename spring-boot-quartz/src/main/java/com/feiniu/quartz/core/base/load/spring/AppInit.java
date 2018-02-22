package com.feiniu.quartz.core.base.load.spring;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

import com.feiniu.quartz.core.util.QuartzUtils;

public class AppInit implements ApplicationListener<ContextRefreshedEvent> {

	public void onApplicationEvent(ContextRefreshedEvent event) {
		Environment environment = event.getApplicationContext().getEnvironment();

		try {
			ServerEnv.getInstance().init(event.getApplicationContext());

			QuartzUtils.initScheduler(environment);

			if (Boolean.valueOf(environment.getProperty("cron.autoRun", "false"))) {
				QuartzUtils.getScheduler().start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}
