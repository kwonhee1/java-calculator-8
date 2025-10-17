# java-calculator-precourse

# 기능 목록 / 요구 사항
- 계산할 문자열을 입력받는다
- 지정된 커스텀 구분자와 문자열을 분리한다
  - 커스텀 구분자는 "//" 과 "\n" 사이에 위치한다
- 문자열을 구분자 기준으로 분리한다
  - 기본 구분자(",", ":")과 커스텀 구분자를 사용하여 구분한다
- 분리한 문자열을 모두 숫자로 변환한다
  - 숫자는 양수만 가능하다
- 각 숫자의 합을 구하고 결과를 출력한다

# 추가 요구 사항
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션을 종료시킨다

# 고려 사항
- [X] 각 함수가 하나의 작업을 하는지 확인
- [ ] 각 함수와 class의 이름이 적절한지 확인
- [X] controller의 추상화 수준을 잘 고려했는지 확인
- [X] 외부 주입이 필요한 부분을 모두 고려했는지 확인
- [ ] 불필요한 import문이 남아있는지 확인

# 고민 사항
- Numbers(domain class), Number(domain class) 생성자에서 앞 뒤로 비슷한 validate를 실행하는 것이 적절한가?
- ExtractedInput (vo class)의 이름이 적절한가?
- SplitService의 public splitNumbersStrDelimiters함수 (조립함수)가 service에 존재하는 것이 적절한가?