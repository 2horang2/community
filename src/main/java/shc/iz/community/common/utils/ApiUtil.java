package shc.iz.community.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import shc.iz.community.common.exception.CustomException;
import shc.iz.community.common.exception.ErrorCode;
import shc.iz.community.dto.BaseEntity;
import shc.iz.community.dto.ServiceDetailInfo;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class ApiUtil {

    public static <T extends BaseEntity> List<T> updateData(List<T> dataList, JpaRepository<T, Long> repository) {
        List<T> updatedList = new ArrayList<>();
        for (T data : dataList) {
            try {
               // Optional<T> existedData = repository.findById(data.getServiceId());
                //data.setNiRgDt(existedData.isEmpty() ? LocalDateTime.now() : existedData.get().getNiRgDt());
                data.setNiRgXctId("000000");
                data.setElF("N");
                data.setLsAltXctId("000000");
                data.setLsAltDt(LocalDateTime.now());
                updatedList.add(data);
            } catch (Exception e) {
                log.error(" 데이터 이슈로 적재 SKIP");
            }
        }
        return updatedList;
    }

    public static <T> T requestWebFlux(URI uri, Class<T> responseType) {
        WebClient webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return webClient.get().uri(uri).accept(MediaType.APPLICATION_JSON_UTF8).retrieve()
                .bodyToMono(responseType)
                .block();
    }




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



