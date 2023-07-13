package shc.iz.community.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {
    private LocalDateTime niRgDt;
    private String niRgXctId;
    private String elF;
    private String lsAltXctId;
    private LocalDateTime lsAltDt;
}