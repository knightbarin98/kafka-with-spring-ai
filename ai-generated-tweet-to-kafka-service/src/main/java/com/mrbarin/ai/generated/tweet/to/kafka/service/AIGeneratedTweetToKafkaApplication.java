package com.mrbarin.ai.generated.tweet.to.kafka.service;

import com.mrbarin.ai.generated.tweet.to.kafka.service.config.AIGeneratedTweetToKafkaServiceConfigData;
import com.mrbarin.ai.generated.tweet.to.kafka.service.init.StreamInitializer;
import com.mrbarin.ai.generated.tweet.to.kafka.service.runner.AIStreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@EnableScheduling
@ComponentScan(basePackages = "com.mrbarin")
@SpringBootApplication
public class AIGeneratedTweetToKafkaApplication implements CommandLineRunner {

  private final AIGeneratedTweetToKafkaServiceConfigData configData;
  private final StreamInitializer streamInitializer;
  private final AIStreamRunner aiStreamRunner;
  private final TaskScheduler taskScheduler;

  public AIGeneratedTweetToKafkaApplication(AIGeneratedTweetToKafkaServiceConfigData configData,
      StreamInitializer streamInitializer, AIStreamRunner aiStreamRunner,
      TaskScheduler taskScheduler) {
    this.configData = configData;
    this.streamInitializer = streamInitializer;
    this.aiStreamRunner = aiStreamRunner;
    this.taskScheduler = taskScheduler;
  }

  public static void main(String[] args) {
    SpringApplication.run(AIGeneratedTweetToKafkaApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
    log.info("Application starting....");
    boolean init = streamInitializer.init();
    if(init){
      log.info("AI Stream Runner init");
      taskScheduler.scheduleAtFixedRate(aiStreamRunner, Duration.of(configData.getSchedulerDurationSec(), ChronoUnit.SECONDS));
    }else{
      log.error("Stream initializer error. No starting the AI Stream Runner");
    }
  }
}
