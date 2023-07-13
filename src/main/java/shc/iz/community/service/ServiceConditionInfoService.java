package shc.iz.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shc.iz.community.dto.ServiceConditionInfo;
import shc.iz.community.repository.ServiceConditionInfoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceConditionInfoService {

    private final ServiceConditionInfoRepository serviceConditionInfoRepository;

    public List<ServiceConditionInfo> updateData(List<ServiceConditionInfo> dataList) {

        List<ServiceConditionInfo> updatedList = new ArrayList<>();
        for (ServiceConditionInfo data : dataList) {

            try {
                Optional<ServiceConditionInfo> existedData = serviceConditionInfoRepository.findById(data.getServiceId());
                data.setNiRgDt(existedData.isEmpty() ? LocalDateTime.now() : existedData.get().getNiRgDt());
                data.setNiRgXctId("000000");
                data.setElF("N");
                data.setLsAltXctId("000000");
                data.setLsAltDt(LocalDateTime.now());
                updatedList.add(data);
            } catch (Exception e) {
                log.error((data.getServiceId()) + " 해당 데이터 이슈로 적재 SKIP");
            }

        }
        return updatedList;
    }

}
