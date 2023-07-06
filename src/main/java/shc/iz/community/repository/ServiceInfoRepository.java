package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shc.iz.community.dto.ServiceInfo;

public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, String> {

}
