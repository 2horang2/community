package shc.iz.community.common.utils;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class ApiUtil {

    public static <T> T requestWebFlux(URI uri, Class<T> responseType) {
        WebClient webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return webClient.get().uri(uri).accept(MediaType.APPLICATION_JSON_UTF8).retrieve()
                .bodyToMono(responseType)
                .block();
    }
}



