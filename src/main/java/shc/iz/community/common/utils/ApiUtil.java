package shc.iz.community.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import shc.iz.community.common.exception.CustomException;
import shc.iz.community.common.exception.ErrorCode;

import java.net.URI;
import java.util.List;

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

//    public static void deleteAllData(JpaRepository<?,?> repository){
//        try {
//            repository.deleteAll();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new CustomException(ErrorCode.DELETE_ERROR);
//        }
//    }

    public static void commonException(JpaRepository<?, ?> repository, Exception e) {
        log.error(e.getMessage());
        //ApiUtil.deleteAllData(repository);
        throw new CustomException(ErrorCode.API_ERROR);
    }


    public static <T> void saveDataList(List<T> dataList, JpaRepository<T, ?> repository) {
        try {


            repository.saveAll(dataList);
        } catch (Exception e) {
            ApiUtil.commonException(repository, e);
        }
    }
}



