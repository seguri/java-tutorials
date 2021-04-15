package name.seguri.java.tutorials.spring_shedlock.application.scheduling;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class MyScheduler {
  private static final String EVERY_FIVE_SECONDS = "0/5 * * * * ?";
  private static final Logger logger = LoggerFactory.getLogger(MyScheduler.class.getName());

  /** A task that is executed every 5 seconds */
  @Scheduled(cron = EVERY_FIVE_SECONDS)
  @SchedulerLock(name = "MyScheduler_task1", lockAtLeastFor = "PT1S", lockAtMostFor = "PT1M")
  public void task1() {
    logger.info("Executing MyScheduler_task1, expected every 5 seconds");
  }

  /** A task that should be executed every 5 seconds, but the lock lasts at least 10 seconds */
  @Scheduled(cron = EVERY_FIVE_SECONDS)
  @SchedulerLock(name = "MyScheduler_task2", lockAtLeastFor = "PT10S", lockAtMostFor = "PT1M")
  public void task2() {
    logger.info("Executing MyScheduler_task2, expected at most every 10 seconds");
  }
}
