package shc.iz.community.common.utils;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@Slf4j
public class ApiUtil {

    @Value("${openapi.url}")
    static String url;

    @Value("${openapi.key.encoding}")
    static String encodingKey;

    @Value("${openapi.key.decoding}")
    static String decdoingKey;

    public URI makeOpenApiUri(String apiUri, int page, int perPage) {
        String uriStr = url + apiUri + "?page=" + page + "&perPage=" + perPage + "&serviceKey="
                + encodingKey;
        URI uri = null;
        try {
            uri = new URI(uriStr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;

    }

}
