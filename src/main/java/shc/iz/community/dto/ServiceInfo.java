package shc.iz.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Table(name = "DEASA0001")
@Entity //JPA는 @Entity 어노테이션이 지정된 클래스만을 관리
public class ServiceInfo {
    @Id
    @Column(name = "PBC_SV_ID")
    @JsonProperty("서비스ID")
    private String serviceId;

    @Column(name = "PBC_SV_AID_TP_NM")
    @JsonProperty("지원유형")
    private String supportType;

    @Column(name = "PBC_SV_NM")
    @JsonProperty("서비스명")
    private String serviceName;

    @Column(name = "PBC_SV_PPS_NM")
    @JsonProperty("서비스목적요약")
    private String serviceSummary;

    @Column(name = "PBC_SV_AID_BJ_NM")
    @JsonProperty("지원대상")
    private String supportTarget;

    @Column(name = "PBC_SV_CHO_TA_TT")
    @JsonProperty("선정기준")
    private String selectionCriteria;

    @Column(name = "PBC_SV_AID_TA_TT")
    @JsonProperty("지원내용")
    private String supportContent;

    @Column(name = "PBC_SV_PT_MT_TT")
    @JsonProperty("신청방법")
    private String applicationMethod;

    @Column(name = "PBC_SV_PT_TRM_VL")
    @JsonProperty("신청기한")
    private String applicationDeadline;

    @Column(name = "PBC_SV_DL_QY_URL_VL")
    @JsonProperty("상세조회URL")
    private String detailedInquiryUrl;

    @Column(name = "PBC_SV_JUC_ICD_VL")
    @JsonProperty("소관기관코드")
    private String jurisdictionAgencyCode;

    @Column(name = "PBC_SV_JUC_IE_NM")
    @JsonProperty("소관기관명")
    private String jurisdictionAgencyName;

    @Column(name = "PBC_SV_JUC_DP_NM")
    @JsonProperty("부서명")
    private String departmentName;

    @Column(name = "PBC_SV_QY_CN_VL")
    @JsonProperty("조회수")
    private Integer queryCount;

    @Column(name = "NI_RG_XCT_ID")
    @JsonProperty("NI_RG_XCT_ID")
    private String niRgXctId;

    @Column(name = "NI_RG_DT")
    @JsonProperty("NI_RG_DT")
    private LocalDateTime niRgDt;

    @Column(name = "LS_ALT_XCT_ID")
    private String lsAltXctId;

    @Column(name = "LS_ALT_DT")
    private LocalDateTime lsAltDt;

    @PrePersist
    public void prePersist() {
        this.niRgDt = LocalDateTime.now();
        this.lsAltDt = LocalDateTime.now();
        this.niRgXctId = "000000";
        this.lsAltXctId = "000000";
    }


}