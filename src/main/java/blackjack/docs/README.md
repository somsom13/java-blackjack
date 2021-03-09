# 블랙잭 게임!

## 개념(도메인) 관점

- 카드
- 카드모음
- 플레이어
- 딜러

### 도메인 설계

![image](https://user-images.githubusercontent.com/48986787/109641715-603ebd00-7b95-11eb-9f53-467024e8ab28.png)

## 명세(메세지) 관점

![image](https://user-images.githubusercontent.com/48986787/109643272-5453fa80-7b97-11eb-962c-b6d1d657dec5.png)

## 기능 요구사항

- [x] 보장된 참여자 이름 입력
    - [x] 보장된 이름 (특수문자를 제외한, 대소문자의 문자)
    - [x] 보장된 이름이 아니면 예외가 발생한다
        - `예외발생 : IllegalArgumentException`
    - [x] 쉼표 구분자로 이름을 구분한다.
    - [x] 인원수는 최소 2명, 최대 8명이다.
    - [x] 이름이 중복되는 경우 예외가 발생한다.
- [x] 카드를 2장씩 나눈 후, 현재 상태를 출력한다.

### 딜러

- [x] 2장의 카드를 받는다.
- [x] 딜러는 하나의 카드만 오픈한다.
- [x] 플레이어의 카드가 완성되고, 카드의 합이 16이하이면 한장의 카드를 더 받는다.
    - [x] 첫 두장의 카드가 A이면, 한장의 카드를 A를 1로 계산한다.
        - 그 이외의 경우 A는 11로 계산한다.
    - [x] 새로 뽑은 카드의 합이 16이하면 한번더 뽑는다.
    - [x] 아니면 계속 뽑는다.
- [x] 첫 두 장의 합이 17 이상이면 추가로 받지 않는다.
    -[x] 한장의 카드를 더 받을 시 출력 로직 완료

### 플레이어

- [x] 2장의 카드를 받는다.
- [x] 21을 넘지 않을 경우 카드를 더 받을 지 선택한다.
    - [x] 프로덕션 코드에서 실행완료.
    -  [x] `이후 리팩토링 필요`
- [x] 21을 넘는 경우 게임에서 진다.
- [x] 더 받겠냐 질문하는 곳에서 입력 값이 이상할 시 카드가 추가되는 에러수정

### 결과

- [x] 게임을 완료한 후 딜러와 모든 플레이어의 결과를 출력한다.
- [x] 플레이어의 결과 로직 변경

### 최종 승패

- [x] 딜러를 기준으로 플레이어의 승패를 판단한다.
- [x] 딜러와 플레이어의 승패를 출력한다.
- [x] 딜러의 총 승패를 계산한다.

- [ ] `예외가 발생해도 서비스가 죽지 않아야 한다.`

## 리펙토링 목록

- [x] 인터페이스 추상클래스로 변경
- [x] 테스트에서만 사용하는 메소드 제거
- [x] 재발명한 컬랙션 클래스 제거
- [x] 필요없는 static 제거
- [x] 유틸성 클래스 private을 통해 인스턴스화 막기
- [x] 클래스 내부에만 쓰이는 메소드 private로 변경
- [x] view를 위해 도메인에서 반환하던 메소드 제거
- [x] Deck 클래스 생성
- [x] get의 의미를 더 드러낼 네이밍으로 변경
- [x] String.join사용 -> String.join(DELIMITER, cardsToString(cards))
- [x] 테스트 케이스 추가
- [x] enum의 람다 표현식에서 적절한 네이밍 짓기