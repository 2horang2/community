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
import shc.iz.community.dto.ServiceDetailInfoVo;
import shc.iz.community.repository.ServiceDetailInfoRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/service-list")
public class ServiceDetailInfoController {

    private final InitConfig initConfig;
    private final ServiceDetailInfoRepository serviceDetailInfoRepository;
    @Value("${openapi.detail-url}")
    String apiUri;

    @Value("${openapi.per-page}")
    int perPage;

    @GetMapping(value = "/webflux/initAllServiceDetailList")
    public ResponseEntity<ResponseVo> webfluxInitAllServiceDetailList(){

        serviceDetailInfoRepository.deleteAll();

        ServiceDetailInfoVo response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, 1, 1),ServiceDetailInfoVo.class);
        int totalCount = response.getTotalCount();
        for (int i = 1; i < totalCount / perPage + 2; i++) {

            try{
                response = ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiUri, i, perPage),ServiceDetailInfoVo.class);
            }catch (Exception e){
                log.error(e.getMessage());
                serviceDetailInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.API_ERROR);
            }

            try{
                serviceDetailInfoRepository.saveAll(response.getServiceDetailInfoList());
            }catch (Exception e){
                log.error(e.getMessage());
                serviceDetailInfoRepository.deleteAll();
                throw new CustomException(ErrorCode.SAVED_ERROR);
            }


            log.info("공공서비스 상세정보 API " + i*1000 + " 번째 적재 완료 " );
        }

        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

}
