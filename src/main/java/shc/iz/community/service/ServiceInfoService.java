package shc.iz.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shc.iz.community.common.config.TagConditionConfig;
import shc.iz.community.dto.ServiceInfo;
import shc.iz.community.repository.ServiceInfoRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceInfoService {
    private final ServiceInfoRepository serviceInfoRepository;

    public List<ServiceInfo> updateData(List<ServiceInfo> dataList) {

        List<ServiceInfo> updatedList = new ArrayList<>();
        for (ServiceInfo serviceInfo : dataList) {
            try {
                try {
                    String serviceName = serviceInfo.getServiceName();
                    Set<String> matchedTags = new HashSet<>();

                    for (Map.Entry<String, Set<String>> entry : TagConditionConfig.serviceTags.entrySet()) {
                        String keyword = entry.getKey();
                        Set<String> tags = entry.getValue();
                        if (serviceName.contains(keyword)) {
                            matchedTags.addAll(tags);
                        }
                    }

                    List<String> serviceTagList = matchedTags.stream()
                            .map(tag -> "#" + tag)
                            .sorted()
                            .collect(Collectors.toList());

                    String tagListString = StringUtils.collectionToDelimitedString(serviceTagList, " ");
                    serviceInfo.setServiceTag(tagListString);
                } catch (Exception e) {
                    log.error(serviceInfo.getServiceId() + " 태그생성 실패");
                }

                Optional<ServiceInfo> existedData = serviceInfoRepository.findById(serviceInfo.getServiceId());
                serviceInfo.setNiRgDt(existedData.isEmpty() ? LocalDateTime.now() : existedData.get().getNiRgDt());
                serviceInfo.setNiRgXctId("000000");
                serviceInfo.setElF("N");
                serviceInfo.setLsAltXctId("000000");
                serviceInfo.setLsAltDt(LocalDateTime.now());
                updatedList.add(serviceInfo);

            } catch (Exception e) {
                log.error((serviceInfo.getServiceId()) + " 해당 데이터 이슈로 적재 SKIP");
            }
        }
        return updatedList;
    }

}
