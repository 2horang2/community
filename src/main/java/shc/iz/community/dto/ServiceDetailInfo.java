package shc.iz.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ServiceDetailInfo {
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
    @JsonProperty("서비스목적")
    private String servicePurpose;

    @Column(name = "PBC_SV_PT_TRM_VL")
    @JsonProperty("신청기한")
    private String applicationDeadline;

    @Column(name = "PBC_SV_AID_BJ_NM")
    @JsonProperty("지원대상")
    private String targetAudience;

    @Column(name = "PBC_SV_CHO_TA_TT")
    @JsonProperty("선정기준")
    private String selectionCriteria;

    @Column(name = "PBC_SV_AID_TA_TT")
    @JsonProperty("지원내용")
    private String supportContent;

    @Column(name = "PBC_SV_PT_MT_TT")
    @JsonProperty("신청방법")
    private String applicationMethod;

    @Column(name = "PBC_SV_PT_EQH_PPE_NM")
    @JsonProperty("구비서류")
    private String requiredDocuments;

    @Column(name = "PBC_SV_RV_IE_NM")
    @JsonProperty("접수기관명")
    private String receivingAgency;

    @Column(name = "PBC_SV_IUL_PON_VL")
    @JsonProperty("전화문의")
    private String inquiryPhoneNumber;

    @Column(name = "PBC_SV_PT_URL_AR")
    @JsonProperty("온라인신청사이트URL")
    private String onlineApplicationUrl;

    @Column(name = "PBC_SV_JUC_IE_NM")
    @JsonProperty("소관기관명")
    private String supervisingAgency;

    @Column(name = "PBC_SV_ADM_RGLT_NM")
    @JsonProperty("행정규칙")
    private String administrativeRegulation;

    @Column(name = "PBC_SV_THN_RGLT_NM")
    @JsonProperty("시행규칙")
    private String enforcementRegulation;

    @Column(name = "PBC_SV_LAW_NM")
    @JsonProperty("서비스명")
    private String serviceName2;

    @Column(name = "NI_RG_XCT_ID")
    @JsonProperty("NI_RG_XCT_ID")
    private String niRgXctId;

    @Column(name = "NI_RG_DT")
    @JsonProperty("NI_RG_DT")
    private String niRgDt;

    @Column(name = "LS_ALT_XCT_ID")
    private String lsAltXctId;

    @Column(name = "LS_ALT_DT")
    private LocalDateTime lsAltDt;
}