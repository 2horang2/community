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
import shc.iz.community.dto.ServiceInfoVo;
import shc.iz.community.repository.ServiceInfoRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceInfoController {

    private final InitConfig initConfig;
    private final ServiceInfoRepository serviceInfoRepository;

    @Value("${openapi.base-url}")
    String apiUri;

    @Value("${openapi.per-page}")
    int perPage;


    @GetMapping(value = "/webflux/initAllServiceList")
    public ResponseEntity<ResponseVo> webfluxInitAllServiceList() {

        serviceInfoRepository.deleteAll();

        ServiceInfoVo response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1),ServiceInfoVo.class);
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {

            try{
                response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage),ServiceInfoVo.class);
            }catch (Exception e){
                log.error(e.getMessage());
                serviceInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.API_ERROR);
            }

            try{
                serviceInfoRepository.saveAll(response.getServiceInfoList());
            }catch (Exception e){
                log.error(e.getMessage());
                serviceInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.SAVED_ERROR);
            }

            log.info("공공서비스 기본정보 API " + i*1000 + " 번째 적재 완료 " );
        }


        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }
}
