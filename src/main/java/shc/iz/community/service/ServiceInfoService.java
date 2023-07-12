package shc.iz.community.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shc.iz.community.common.config.TagConditionConfig;
import shc.iz.community.dto.ServiceInfo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ServiceInfoService {

    public List<ServiceInfo> updateServiceTagList(List<ServiceInfo> dataList ){

        List<ServiceInfo> updatedList = new ArrayList<>();
        for (ServiceInfo serviceInfo : dataList) {
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

            updatedList.add(serviceInfo);
        }
        return updatedList;
    }

}
