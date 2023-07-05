package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import shc.iz.community.dto.ServiceInfo;

public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE DEASA0001 A SET A.EL_F = 'Y'", nativeQuery=true)
    void updateAllElFToY();

}
