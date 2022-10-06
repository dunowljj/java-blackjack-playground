블랙잭 재구현
=====

## 1. 기능 요구 사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이
이기는 게임이다.

플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## 2. 기능 구현 목록
✅ 플레이어 이름들을 입력받는 기능 (이름 10자 제한) o
✅ 배팅 금액을 입력받는 기능 (음수 입력 불가) o

- 개임카드 구성하는 기능 o
  - 숫자 : 1~9 / KQJ -> 10 / Ace -> 1 또는 11 o
  - 모양 : 하트, 다이아몬드, 클로버, 스페이드 o

- 처음에 두 장의 카드를 지급받는 기능 o
  - Cards 생성 시 기본적으로 두 장 뽑도록 구현 o
  - 이름과 함께 2장 받았다는 사실 출력 o
  - 플레이어 카드 출력 o
  - 딜러 카드는 한 장만 출력 o
  - 우선 게임 규칙을 상태를 나타내는 클래스로 추상화하기 o

- 21이 넘지 않을 경우 카드를 추가하는 기능 o
  - 추가 여부 묻고 추가 o
  - 추가 시 카드 현황 출력 o

- 처음 합이 블랙잭(21)인 경우 확인하는 기능 o
- 블랙잭인 경우 바로 종료 및 결과 도출

- Ace 카드 11로 계산하는 경우 추가 o

- 딜러의 첫 카드 합계가 16이하인 경우 1장을 의무적으로 추가하는 기능 (17이상 추가 불가) o
  - 추가 시 추가했다는 내용의 출력 o

- 최종 카드들을 출력하는 기능 o

- 최종 수익을 계산하는 기능
  - 딜러의 카드 합이 21 초과 시 남아있는 플레이어들이 승리하는 기능 -> 플레이어 배팅 금액 1.0배 지급 o
  - 카드 합이 가장 큰 사람이 승리하는 기능
  - 블랙잭이면 바로 종료 후 수익 보여주는 기능 o
  - 플레이어 Bust시 -1.0배 수익 o

- 결과를 출력하는 기능 o

Ace는 두개 부터는 어짜피 하나는 무조건 1을 골라야 한다. 둘다 11을 고르면 바로 22가 되기 떄문이다.
피드백에 Rank 메서드를 보고 11을 Ace의 score에 set해주는 것인줄 알았는데, 그러면 다른 Ace값까지 변한다 -> Ace 값을 enum에 하나 더 추가

# 코드 수정 및 오류 수정하기
✅ 메시지 클래스 만들어서 메시지 생성 책임 분리하기
✅ 입력 개선
  ✅ 예외처리를 입력 시점에 하여 해당 입력만 다시 받도록 처리하기
  ✅ 입력받는 기능을 Participants 클래스에서 분리
- Participants 리뉴얼
  ✅ 생성자 수정
    ✅ 테스트를 위해 가변인자 생성자 생성, join 메서드 삭제
    ✅ dealer, players 참조를 멤버변수로 설정 
  ✅ total기능 제외 모두 Controller로 이전 혹은 삭제
  - 요구사항 잘못 구현한 부분 수정 (total 기능)
  - Stay가 없을 경우 최댓값 오류 수정
- Ace값 선택하는 내용 수정
- 테스트에 Instanceof() 활용
- 테스트 정리

