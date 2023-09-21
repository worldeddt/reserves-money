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