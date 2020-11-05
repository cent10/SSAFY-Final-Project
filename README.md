# 액티비티 X - 야! 올레?

### 간트차트

```mermaid

gantt
    dateFormat  YYYY-MM-DD
    title       야! 올레?


    section 프로젝트 준비
    기술학습            :done,  des1, 2020-10-19, 4d
    환경설정 	        :done,  des2, 2020-10-21, 4d
    

    section 메인 기능
    회원 관리					:crit, active, 2020-10-26, 2w
	레저 정보 제공(팁)				:crit, active, 2020-10-26, 2w
	레저 서비스 조회/예약		:crit, active, 2020-10-26, 2w
	레저 장비 대여 조회/예약	:crit, active, 2020-10-26, 2w
	후기 작성					 :active, 2020-10-26, 2w
	예약 현황 조회				:crit, 2020-11-02, 2w
	메인 페이지					:crit, active, 2020-10-26, 1w
	결제							:crit, 2020-11-02, 2w
	공지사항						:notice, 2020-11-02, 1w

    section 테스트
    QA 테스트          	:after notice, 4d
    
    section 기타
    UCC제작 : 2020-11-16, 5d
    발표준비 :crit, 2020-11-16, 5d
    
```

