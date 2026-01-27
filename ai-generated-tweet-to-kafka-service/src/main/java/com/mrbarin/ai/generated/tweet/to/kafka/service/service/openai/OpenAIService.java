package com.mrbarin.ai.generated.tweet.to.kafka.service.service.openai;

import com.mrbarin.ai.generated.tweet.to.kafka.service.exception.AIGeneratedTweetToKafkaServiceException;
import com.mrbarin.ai.generated.tweet.to.kafka.service.service.AIService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService implements AIService {

  @Override
  public String generateTweet() throws AIGeneratedTweetToKafkaServiceException {
    return "AI Generated Tweet Content";
  }
}
