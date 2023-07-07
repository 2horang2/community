package shc.iz.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "DEASA0003")
public class ServiceConditionInfo {

    @Id
    @Column(name = "PBC_SV_ID")
    @JsonProperty("서비스ID")
    private String serviceId;

    @Column(name = "PBC_SV_SEX_CCD1")
    @JsonProperty("JA0101")
    private String sexCode1;

    @Column(name = "PBC_SV_SEX_CCD2")
    @JsonProperty("JA0102")
    private String sexCode2;

    @Column(name = "PBC_SV_AGE_CCD1")
    @JsonProperty("JA0103")
    private String ageCode1;

    @Column(name = "PBC_SV_AGE_CCD2")
    @JsonProperty("JA0104")
    private String ageCode2;

    @Column(name = "PBC_SV_AGE_CCD3")
    @JsonProperty("JA0105")
    private String ageCode3;

    @Column(name = "PBC_SV_AGE_CCD4")
    @JsonProperty("JA0106")
    private String ageCode4;

    @Column(name = "PBC_SV_AGE_CCD5")
    @JsonProperty("JA0107")
    private String ageCode5;

    @Column(name = "PBC_SV_AGE_CCD6")
    @JsonProperty("JA0108")
    private String ageCode6;

    @Column(name = "PBC_SV_AGE_CCD7")
    @JsonProperty("JA0109")
    private String ageCode7;

    @Column(name = "BJ_SR_AGE")
    @JsonProperty("JA0110")
    private Integer bjSrAge;

    @Column(name = "BJ_ED_AGE")
    @JsonProperty("JA0111")
    private Integer bjEdAge;

    @Column(name = "AID_RAT_CCD1")
    @JsonProperty("JA0201")
    private String aidRateCode1;

    @Column(name = "AID_RAT_CCD2")
    @JsonProperty("JA0202")
    private String aidRateCode2;

    @Column(name = "AID_RAT_CCD3")
    @JsonProperty("JA0203")
    private String aidRateCode3;

    @Column(name = "AID_RAT_CCD4")
    @JsonProperty("JA0204")
    private String aidRateCode4;

    @Column(name = "AID_RAT_CCD5")
    @JsonProperty("JA0205")
    private String aidRateCode5;

    @Column(name = "PPR_PRN_F")
    @JsonProperty("JA0301")
    private String pprPrnF;

    @Column(name = "PGN_K_F")
    @JsonProperty("JA0302")
    private String pgnKF;

    @Column(name = "PTU_F")
    @JsonProperty("JA0303")
    private String ptuF;


    @Column(name = "HDC_SU_CCD1")
    @JsonProperty("JA0304")
    private String hdcSuCode1;

    @Column(name = "HDC_SU_CCD2")
    @JsonProperty("JA0305")
    private String hdcSuCode2;

    @Column(name = "MMK_CCD1")
    @JsonProperty("JA0306")
    private String mmkCode1;

    @Column(name = "MMK_CCD2")
    @JsonProperty("JA0307")
    private String mmkCode2;

    @Column(name = "MMK_CCD3")
    @JsonProperty("JA0308")
    private String mmkCode3;

    @Column(name = "MMK_CCD4")
    @JsonProperty("JA0309")
    private String mmkCode4;

    @Column(name = "MMK_CCD5")
    @JsonProperty("JA0310")
    private String mmkCode5;

    @Column(name = "MMK_CCD6")
    @JsonProperty("JA0311")
    private String mmkCode6;

    @Column(name = "JBG_CZ_CCD1")
    @JsonProperty("JA0312")
    private String jbgCzCode1;

    @Column(name = "JBG_CZ_CCD2")
    @JsonProperty("JA0313")
    private String jbgCzCode2;

    @Column(name = "JBG_CZ_CCD3")
    @JsonProperty("JA0314")
    private String jbgCzCode3;

    @Column(name = "JBG_CZ_CCD4")
    @JsonProperty("JA0315")
    private String jbgCzCode4;

    @Column(name = "JBG_CZ_CCD5")
    @JsonProperty("JA0316")
    private String jbgCzCode5;

    @Column(name = "JBG_CZ_CCD6")
    @JsonProperty("JA0317")
    private String jbgCzCode6;

    @Column(name = "JBG_CZ_CCD7")
    @JsonProperty("JA0318")
    private String jbgCzCode7;

    @Column(name = "JBG_CZ_CCD8")
    @JsonProperty("JA0319")
    private String jbgCzCode8;

    @Column(name = "JBG_CZ_CCD9")
    @JsonProperty("JA0320")
    private String jbgCzCode9;

    @Column(name = "JBG_CZ_CCD10")
    @JsonProperty("JA0322")
    private String jbgCzCode10;

    @Column(name = "DISE_K_F1")
    @JsonProperty("JA0323")
    private String diseKf1;


    @Column(name = "DISE_K_F2")
    @JsonProperty("JA0324")
    private String diseKF2;

    @Column(name = "RCUR_K_F1")
    @JsonProperty("JA0325")
    private String rcurKF1;

    @Column(name = "LBR_K_F1")
    @JsonProperty("JA0326")
    private String lbrKF1;

    @Column(name = "LBR_K_F2")
    @JsonProperty("JA0327")
    private String lbrKF2;

    @Column(name = "FTU_FRM_CCD1")
    @JsonProperty("JA0401")
    private String ftuFrmCcd1;

    @Column(name = "FTU_FRM_CCD2")
    @JsonProperty("JA0402")
    private String ftuFrmCcd2;

    @Column(name = "FTU_FRM_CCD3")
    @JsonProperty("JA0403")
    private String ftuFrmCcd3;

    @Column(name = "FTU_FRM_CCD4")
    @JsonProperty("JA0404")
    private String ftuFrmCcd4;

    @Column(name = "FTU_FRM_CCD5")
    @JsonProperty("JA0410")
    private String ftuFrmCcd5;

    @Column(name = "FTU_FRM_CCD6")
    @JsonProperty("JA0411")
    private String ftuFrmCcd6;

    @Column(name = "FTU_FRM_CCD7")
    @JsonProperty("JA0412")
    private String ftuFrmCcd7;

    @Column(name = "FTU_FRM_CCD8")
    @JsonProperty("JA0413")
    private String ftuFrmCcd8;

    @Column(name = "FTU_FRM_CCD9")
    @JsonProperty("JA0414")
    private String ftuFrmCcd9;


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