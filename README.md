# 사용자 적립금 관리 API

---

## Version
`0.0.1`

## Requirements

spring boot 3.1.3 \
jdk 17 \
mariadb

## Development

#### Before
```
docker 설치
postman 사용을 권장
```



#### Docker local db 생성
```bash
docker run -p 3309:3306 -e MARIADB_ROOT_PASSWORD=eddy -e MARIADB_DB=prototype --name reserves-money -d mariadb;
```

#### Docker 컨테이너 접속 후 아래와 같이 데이터베이스 생성
```bash
docker exec -it {container id} /bin/sh

# mariadb -u root -p
Enter password: eddy

MariaDB [(none)]> create database point;
```

#### Build
#### !IntalliJ 및 기타 IDE 사용 시 로컬 빌드 바로 적용
```bash
./gradlew build -x test
nohup java -jar build/libs/reserves-money-{version}.jar &
```

### [필] TEST
```
최초 유저 가입을 통하여 uuid 를 발급
```

### 포인트 사용을 적립된 순서대로 그 잔액을 사용하도록 구현
![](/Users/eddy/Desktop/스크린샷 2023-09-21 오후 10.35.47.png)
![](/Users/eddy/Desktop/스크린샷 2023-09-21 오후 10.36.12.png)
![](/Users/eddy/Desktop/스크린샷 2023-09-21 오후 10.36.24.png)

# Achitecture

---

전체 적인 코드 구조를 계층화 하여 infra(data) <> application <> presentation \
구조로 설계되어 있으며 각 층별로 infra 에서 데이터를 application 에서 usecase 를 \
presentation 에서 접근 인터페이스를 관리하도록 책임을 분산하였습니다.

아울러, request body 와 response body 의 인터페이스 적용으로 역할(호출 조건과, 응답값 반환) 을 \
추상화 및 구현하는데 중심을 두었으며 application 에서는 호출을 위한 조건을 conditions 로 \
받아 캡슐화를 시도하였습니다.

좀 더 고칠 점이나 리뷰가 생각나시면 언제든 환영합니다 😊
