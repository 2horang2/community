package shc.iz.community.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shc.iz.community.common.exception.CustomException;
import shc.iz.community.common.exception.ErrorCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Configuration
public class TagConditionConfig {

    public static Map<String, Set<String>> serviceTags;

    @Bean
    public void readTagConditionFile (){
        serviceTags = new HashMap<>();

        try (BufferedReader conditionReader = new BufferedReader(new FileReader("src/file/tag_condition.txt"))) {
            String line;
            while ((line = conditionReader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String keyword = parts[0];
                Set<String> tags = new HashSet<>(Arrays.asList(parts).subList(1, parts.length));
                serviceTags.merge(keyword, tags, (existingTags, newTags) -> {
                    existingTags.addAll(newTags);
                    return existingTags;
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
