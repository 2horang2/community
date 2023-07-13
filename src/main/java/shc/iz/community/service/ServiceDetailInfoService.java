package shc.iz.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shc.iz.community.dto.ServiceDetailInfo;
import shc.iz.community.repository.ServiceDetailInfoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceDetailInfoService {

    private final ServiceDetailInfoRepository serviceDetailInfoRepository;

    public List<ServiceDetailInfo> updateData(List<ServiceDetailInfo> dataList) {

        List<ServiceDetailInfo> updatedList = new ArrayList<>();
        for (ServiceDetailInfo data : dataList) {
            try{
                Optional<ServiceDetailInfo> existedData = serviceDetailInfoRepository.findById(data.getServiceId());
                data.setNiRgDt(existedData.isEmpty() ? LocalDateTime.now() : existedData.get().getNiRgDt());
                data.setNiRgXctId("000000");
                data.setElF("N");
                data.setLsAltXctId("000000");
                data.setLsAltDt(LocalDateTime.now());
                updatedList.add(data);
            }catch (Exception e){
                log.error((data.getServiceId()) +" 해당 데이터 이슈로 적재 SKIP");
            }

        }
        return updatedList;
    }

}
