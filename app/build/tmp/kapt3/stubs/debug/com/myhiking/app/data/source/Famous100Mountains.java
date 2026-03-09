package com.myhiking.app.data.source;

/**
 * 한국의 100대 명산 통합 목록
 * - 산림청 지정 100대 명산
 * - 블랙야크(BAC) 명산100
 *
 * 산 이름에 이 목록의 이름이 포함되면 100대 명산으로 표시
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/myhiking/app/data/source/Famous100Mountains;", "", "()V", "blackyak100Extra", "", "", "famousPeakNames", "forestService100", "mountainNames", "isFamous100", "", "mountainName", "app_debug"})
public final class Famous100Mountains {
    
    /**
     * 산림청 100대 명산
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> forestService100 = null;
    
    /**
     * 블랙야크(BAC) 명산100 - 산림청과 겹치지 않는 추가 산
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> blackyak100Extra = null;
    
    /**
     * 100대 명산의 주요 봉우리명 (별도 이름으로 DB에 등록된 경우)
     * 예: "대청봉" = 설악산, "천왕봉" = 지리산/속리산, "비로봉" = 오대산/소백산
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> famousPeakNames = null;
    
    /**
     * 통합 100대 명산 목록
     * 산림청 100대 + 블랙야크 100대에서 추가된 산
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> mountainNames = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.data.source.Famous100Mountains INSTANCE = null;
    
    private Famous100Mountains() {
        super();
    }
    
    /**
     * 산 이름이 100대 명산에 해당하는지 확인
     * "북한산(백운대)" -> "북한산" 매칭
     * "속리산(천왕봉)" -> "속리산" 매칭
     * "내변산 관음봉" -> "내변산" 매칭
     */
    public final boolean isFamous100(@org.jetbrains.annotations.NotNull()
    java.lang.String mountainName) {
        return false;
    }
}