package shc.iz.community.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shc.iz.community.common.config.InitConfig;
import shc.iz.community.common.exception.CustomException;
import shc.iz.community.common.exception.ErrorCode;
import shc.iz.community.common.utils.ApiUtil;
import shc.iz.community.dto.ResponseVo;
import shc.iz.community.dto.ServiceConditionInfoVo;
import shc.iz.community.repository.ServiceConditionInfoRepository;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceConditionController {

    private final InitConfig initConfig;
    private final ServiceConditionInfoRepository serviceConditionInfoRepository;
    @Value("${openapi.condition-url}")
    String apiUri;

    @Value("${openapi.per-page}")
    int perPage;

    @GetMapping(value = "/webflux/initAllServiceConditionList")
    public ResponseEntity<ResponseVo> webfluxInitAllServiceConditionList() {

        serviceConditionInfoRepository.deleteAll();

        log.info("webflux start time : " + LocalDateTime.now());
        ServiceConditionInfoVo response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1),ServiceConditionInfoVo.class);
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {

            try{
                response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage),ServiceConditionInfoVo.class);
            }catch (Exception e){
                log.error(e.getMessage());
                serviceConditionInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.API_ERROR);
            }

            try{
                serviceConditionInfoRepository.saveAll(response.getServiceConditionInfoList());
            }catch (Exception e){
                log.error(e.getMessage());
                serviceConditionInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.SAVED_ERROR);
            }


            log.info("공공서비스 지원조건 API " + i*1000 + " 번째 적재 완료 " );
        }
        log.info("webflux end time : " + LocalDateTime.now());
        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

}
