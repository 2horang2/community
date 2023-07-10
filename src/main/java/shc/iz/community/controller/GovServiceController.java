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
import shc.iz.community.common.utils.ApiUtil;
import shc.iz.community.dto.ResponseVo;
import shc.iz.community.dto.ServiceConditionInfoVo;
import shc.iz.community.dto.ServiceDetailInfoVo;
import shc.iz.community.dto.ServiceInfoVo;
import shc.iz.community.repository.ServiceConditionInfoRepository;
import shc.iz.community.repository.ServiceDetailInfoRepository;
import shc.iz.community.repository.ServiceInfoRepository;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * 정부 서비스와 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/open-api/gov-service")
public class GovServiceController {

    private final InitConfig initConfig;
    private final ServiceConditionInfoRepository serviceConditionInfoRepository;
    private final ServiceDetailInfoRepository serviceDetailInfoRepository;
    private final ServiceInfoRepository serviceInfoRepository;
    @Value("${openapi.condition-url}")
    String apiConditionUri;

    @Value("${openapi.detail-url}")
    String apiDetailUri;

    @Value("${openapi.base-url}")
    String apiBaseUri;

    @Value("${openapi.per-page}")
    int perPage;


    /**
     * 모든 서비스 목록을 초기화하기 위한 엔드포인트입니다.
     *
     * @return 응답 상태와 메시지를 포함한 ResponseEntity
     */
    @GetMapping(value = "/initAllServiceList")
    public ResponseEntity<ResponseVo> initAllServiceList() {

        ApiUtil.deleteAllData(serviceInfoRepository);
        AtomicReference<ServiceInfoVo> response = new AtomicReference<>(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiBaseUri, 1, 1), ServiceInfoVo.class));

        log.info("DATA 총 개수 : " + response.get().getTotalCount());
        IntStream.rangeClosed(1, response.get().getTotalCount() / perPage + 1)
                .forEach(page -> {
                    try {
                        response.set(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiDetailUri, page, perPage), ServiceInfoVo.class));
                    } catch (Exception e) {
                        ApiUtil.commonException(serviceInfoRepository, e);
                    }
                    ApiUtil.saveDataList(response.get().getDataList(), serviceInfoRepository);
                    log.info("공공서비스 기본정보 API " + page * 1000 + " 번째 적재 완료 ");
                });

        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

    /**
     * WebFlux를 사용하여 모든 서비스 상세 목록을 초기화하기 위한 엔드포인트입니다.
     *
     * @return 응답 상태와 메시지를 포함한 ResponseEntity
     */
    @GetMapping(value = "/initAllServiceDetailList")
    public ResponseEntity<ResponseVo> initAllServiceDetailList() {

        ApiUtil.deleteAllData(serviceDetailInfoRepository);

        AtomicReference<ServiceDetailInfoVo> response = new AtomicReference<>(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiBaseUri, 1, 1), ServiceDetailInfoVo.class));
        log.info("DATA 총 개수 : " + response.get().getTotalCount());
        IntStream.rangeClosed(1, response.get().getTotalCount() / perPage + 1)
                .forEach(page -> {
                    try {
                        response.set(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiDetailUri, page, perPage), ServiceDetailInfoVo.class));
                    } catch (Exception e) {
                        ApiUtil.commonException(serviceInfoRepository, e);
                    }
                    ApiUtil.saveDataList(response.get().getDataList(), serviceDetailInfoRepository);
                    log.info("공공서비스 상세정보 API " + page * 1000 + " 번째 적재 완료 ");
                });


        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }

    /**
     * WebFlux를 사용하여 모든 서비스 조건 목록을 초기화하기 위한 엔드포인트입니다.
     *
     * @return 응답 상태와 메시지를 포함한 ResponseEntity
     */
    @GetMapping(value = "/initAllServiceConditionList")
    public ResponseEntity<ResponseVo> initAllServiceConditionList() {

        ApiUtil.deleteAllData(serviceConditionInfoRepository);
        AtomicReference<ServiceConditionInfoVo> response = new AtomicReference<>(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiConditionUri, 1, 1), ServiceConditionInfoVo.class));

        log.info("DATA 총 개수 : " + response.get().getTotalCount());
        IntStream.rangeClosed(1, response.get().getTotalCount() / perPage + 1)
                .forEach(page -> {
                    try {
                        response.set(ApiUtil.requestWebFlux(initConfig.makeOpenApiUri(apiConditionUri, page, perPage), ServiceConditionInfoVo.class));
                    } catch (Exception e) {
                        ApiUtil.commonException(serviceInfoRepository, e);
                    }
                    ApiUtil.saveDataList(response.get().getDataList(), serviceConditionInfoRepository);
                    log.info("공공서비스 지원조건 API " + page * 1000 + " 번째 적재 완료 ");
                });

        return new ResponseEntity<>(new ResponseVo("00", "성공"), HttpStatus.OK);
    }


}