package shc.iz.community.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    SAVED_ERROR(404, "DB 적재 중 오류가 발생했습니다."),
    DELETE_ERROR(404, "DB 삭제 중 오류가 발생했습니다."),
    URI_ERROR(404, "URI 생성하다 오류가 발생했습니다."),
    API_ERROR(404, "API 호출 중 오류가 발생했습니다."),
    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;
}