package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import shc.iz.community.dto.ServiceDetailInfo;

public interface ServiceDetailInfoRepository extends JpaRepository<ServiceDetailInfo, String> {

    @Query(value = "UPDATE DEASA0002 A SET A.EL_F = 'Y'", nativeQuery=true)
    @Modifying
    @Transactional
    void updateAllElFToY();
}
