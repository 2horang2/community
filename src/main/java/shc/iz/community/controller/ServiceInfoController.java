package shc.iz.community.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import shc.iz.community.common.config.InitConfig;
import shc.iz.community.common.utils.ApiUtil;
import shc.iz.community.dto.ResponseVo;
import shc.iz.community.dto.ServiceInfo;
import shc.iz.community.dto.ServiceInfoVo;
import shc.iz.community.repository.ServiceInfoRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceInfoController {

    private final InitConfig initConfig;
    private final ServiceInfoRepository serviceInfoRepository;
    final String apiUri = "/gov24/v3/serviceList";
    final int perPage = 1000;

    @GetMapping(value = "/webflux/initAllServiceList")
    public ResponseEntity<ResponseVo> webfulxInitAllServiceList() throws Exception {

        serviceInfoRepository.deleteAll();

        log.info("webflux start time : " + LocalDateTime.now());
        ServiceInfoVo response = this.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1));
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {
            response = this.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage));
            serviceInfoRepository.saveAll(response.getServiceInfoList());
        }
        log.info("webflux end time : " + LocalDateTime.now());
        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

    private ServiceInfoVo requestWebFlux(URI uri) {

        WebClient webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return webClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(ServiceInfoVo.class).block();
    }
}
