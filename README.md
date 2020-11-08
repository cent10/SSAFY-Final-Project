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
    회원 관리					:crit, active, 2020-10-26, 3w
	레저 정보 제공(팁)				:crit, active, 2020-10-26, 2w
	레저 서비스 조회/예약		:crit, active, 2020-10-26, 3w
	레저 장비 대여 조회/예약	:crit, active, 2020-10-26, 3w
	후기 작성					 :active, 2020-10-26, 3w
	예약 현황 조회				:crit, 2020-11-02, 3w
	메인 페이지					:crit, active, 2020-10-26, 2w
	결제							:crit, active, 2020-11-02, 3w
	공지사항						:notice, active, 2020-11-02, 2w

    section 테스트
    QA 테스트          	:after notice, 4d
    
    section 기타
    UCC제작 : 2020-11-16, 5d
    발표준비 :crit, 2020-11-16, 5d
    
```



# 기능 정리

Req. 1.	회원 관리 :	회원 이메일 주소(수정 불가)와 닉네임을 등록/수정/삭제한다.

Req. 2.	로그인/로그아웃 : 회원의 웹사이트 이용을 위한 로그인/로그아웃 기능을 구현한다.

Req. 3.	레저 업체 조회 및 예약 : 레저 서비스 업체에 대해 조회, 예약할 시 레저 서비스 제공 업체를 연결해 준다.(등록/수정/삭제)

Req. 4.	레저 장비대여/구매 : 레저활동을 하는 필요한 장비를 대여, 구매할 시 대여, 판매 업체를 연결해 준다.(등록/수정/삭제)

Req. 5.	레저 정보제공 관리 : 레저 관련하여 사용자가 도움 받을 수 있는 정보, 팁들을 등록/수정/삭제할 수 있도록 만든다.

Req. 6.	공지사항 : 플랫폼에 대한 공지사항 등록/수정/삭제할 수 있도록 한다.

Req. 7.	후기, 댓글, 평점 : 사용자가 레저 업체를 이용할때, 피드백을 하기 위해서, 후기, 댓글, 평점 기능을 만든다.

--------------------------------------------------------------------

#  기술 스택

1. Java Spring : 백엔드

2. MySQL :	DB

3. Vue.js : 프론트엔드

4. Docker : 컨테이너

5. AWS	: 배포

6. Jenkins : CI/CD

7. GIT : 소스 협업 관리

8. JIRA : 이슈 협업 관리

----------------------------------------------------------------------

# git branch 정책

- feat/** → 기능 수행 (front/ back)
- fix/** → 수정 작업 (front/back)
- docs/** → 문서 작업 (코드 외적인 부분, 모든 브랜치)
- merge는 자유롭게

----------------------------------------------------------------------

# commit 정책

- [예약어] 지라 이슈 번호 : 제목(한 일)

- ex) [add] asdfljsdfal123 : 로그인 기능 추가

- 제목의 길이는 최대 40글자까지 한글로 간단 명료하게 작성
- 제목에 .(마침표) 금지

### 예약어 리스트

- add: 새로운 기능
- fix: 버그 수정
- modify: 비즈니스 로직 변경
- docs : 문서 (문서 추가, 수정, 삭제)
- test : 테스트
- etc : 기타 변경사항

-------------------------------------------------------------------

# 코드 컨벤션

- 변수명 Camel 표기법 사용

- 백엔드 localhost주소 : 8080
- 프론트엔드 localhost주소 : 3000
- 백엔드는 swagger 이용
- DB테이블의 변수명 최대한 유지

-------------------------------------------------------------------




