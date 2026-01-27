package com.mrbarin.ai.generated.tweet.to.kafka.service.service;

import com.mrbarin.ai.generated.tweet.to.kafka.service.config.AIGeneratedTweetToKafkaServiceConfigData;
import com.mrbarin.ai.generated.tweet.to.kafka.service.exception.AIGeneratedTweetToKafkaServiceException;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(name = "ai-generated-tweet-to-kafka-service.ai-service", havingValue = "OpenAI-JavaClient")
public class OpenAIJavaClientService implements AIService{
  private final AIGeneratedTweetToKafkaServiceConfigData configData;

  public OpenAIJavaClientService(AIGeneratedTweetToKafkaServiceConfigData configData) {
    this.configData = configData;
  }

  @Override
  public String generateTweet() throws AIGeneratedTweetToKafkaServiceException {
    log.info("Generating tweet content using OpenAIJavaClientService");
    String prompt = configData.getPrompt().replace(configData.getKeywordsPlaceHolder(),
        String.join(",", configData.getStreamingDataKeywords()));
    return getPromptResponse(prompt);
  }

  private String getPromptResponse(String prompt) {
    OpenAIClient client = OpenAIOkHttpClient.fromEnv();
    ChatCompletionCreateParams.Builder createParams = ChatCompletionCreateParams.builder()
        .model(ChatModel.of(configData.getOpenAI().getModel()))
        .addDeveloperMessage("You're helping me to create a tweet content based on the given format and keywords.")
        .maxCompletionTokens(configData.getOpenAI().getMaxCompletionTokens())
        .temperature(configData.getOpenAI().getTemperature())
        .addUserMessage(prompt);
    List<ChatCompletionMessage> chatCompletionMessages =
        client.chat().completions().create(createParams.build()).choices().stream()
            .map(ChatCompletion.Choice::message).toList();
    return chatCompletionMessages.getFirst().content().get();
  }
}
