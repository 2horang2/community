package shc.iz.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import shc.iz.community.dto.ServiceConditionInfo;

public interface ServiceConditionInfoRepository extends JpaRepository<ServiceConditionInfo, String> {

    @Query(value = "UPDATE DEASA0003 A SET A.EL_F = 'Y'", nativeQuery=true)
    @Modifying
    @Transactional
    void updateAllElFToY();
}
