package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shc.iz.community.dto.ServiceDetailInfo;
import shc.iz.community.dto.ServiceInfo;

public interface ServiceDetailInfoRepository extends JpaRepository<ServiceDetailInfo, String> {

}
