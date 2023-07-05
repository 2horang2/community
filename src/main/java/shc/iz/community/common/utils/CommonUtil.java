package shc.iz.community.common.utils;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    public static boolean isEmpty(Object obj){

        if(obj == null)
            return true;
        if((obj instanceof String) && ((String)obj).trim().length()==0 )
            return true;
        if(obj instanceof List )
            return ((List<?>) obj).isEmpty();
        if(obj instanceof Map)
            return ((Map<?,?>) obj).isEmpty();

        return false;

    }

    public static boolean isStringEmpty(String obj){
        //문자열이 비어있는 조건을 여러분이 "         " 도 비어있는걸로 판단할지 아닐지에 따라서
        //공통유틸을 만드는데 로직이 달라지겠죵?
        //그래서 프로젝트 시작 전에, 이러한 공통유틸/유효성검증같은 기능이 뭐가 필요할지 먼저 작성해보고
        //공통유틸 클래스 안에 다들 static으로 만들어 공유하는 편입니다 ㅎ_ㅎ
        //여기서 같이 익셉션 처리까지하면, 비즈니스로직은 더 간결해지겠죠
        if(obj == null)
            return true;
        if(obj.equals("")||obj.length()==0)
            return true;
        return false;
    }

}
