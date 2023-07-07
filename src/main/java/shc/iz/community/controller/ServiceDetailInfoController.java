package shc.iz.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
import shc.iz.community.dto.ServiceConditionInfoVo;
import shc.iz.community.dto.ServiceDetailInfoVo;
import shc.iz.community.repository.ServiceDetailInfoRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceDetailInfoController {

    private final InitConfig initConfig;
    private final ServiceDetailInfoRepository serviceDetailInfoRepository;
    final String apiUri = "/gov24/v3/serviceDetail";
    final int perPage = 1000;

    @GetMapping(value = "/webflux/initAllServiceDetailList")
    public ResponseEntity<ResponseVo> webfluxInitAllServiceDetailList() throws Exception {

        serviceDetailInfoRepository.deleteAll();

        log.info("webflux start time : " + LocalDateTime.now());
        ServiceDetailInfoVo response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1),ServiceDetailInfoVo.class);
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {
            response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage),ServiceDetailInfoVo.class);
            serviceDetailInfoRepository.saveAll(response.getServiceDetailInfoList());
        }
        log.info("webflux end time : " + LocalDateTime.now());

        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

}
