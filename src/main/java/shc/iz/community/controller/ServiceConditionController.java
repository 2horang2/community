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
import shc.iz.community.dto.ServiceConditionInfoVo;
import shc.iz.community.dto.ServiceInfoVo;
import shc.iz.community.repository.ServiceConditionInfoRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceConditionController {

    private final InitConfig initConfig;
    private final ServiceConditionInfoRepository serviceConditionInfoRepository;
    final String apiUri = "/gov24/v3/supportConditions";
    final int perPage = 1000;

    @GetMapping(value = "/webflux/initAllServiceConditionList")
    public ResponseEntity<ResponseVo> webfluxInitAllServiceConditionList() throws Exception {

        serviceConditionInfoRepository.deleteAll();

        log.info("webflux start time : " + LocalDateTime.now());
        ServiceConditionInfoVo response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1),ServiceConditionInfoVo.class);
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {
            response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage),ServiceConditionInfoVo.class);
            serviceConditionInfoRepository.saveAll(response.getServiceConditionInfoList());
        }
        log.info("webflux end time : " + LocalDateTime.now());
        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

}
