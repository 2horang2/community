package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shc.iz.community.dto.ServiceConditionInfo;
import shc.iz.community.dto.ServiceDetailInfo;

public interface ServiceConditionInfoRepository extends JpaRepository<ServiceConditionInfo, String> {

}
