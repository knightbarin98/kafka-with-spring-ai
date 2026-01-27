package com.mrbarin.ai.generated.tweet.to.kafka.service.runner;

import com.mrbarin.ai.generated.tweet.to.kafka.service.service.AIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AIStreamRunner implements Runnable{

  private final AIService aiService;

  public AIStreamRunner(AIService aiService) {
    this.aiService = aiService;
  }

  @Override
  public void run() {
    String tweetContent = aiService.generateTweet();
    log.info("AiService: {}", tweetContent);
    //Kafka implementation here
  }
}
