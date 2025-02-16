# Spring Boot 배포 PoC

Docker를 사용해 Spring Boot 앱을 배포하는 것을 테스트하기 위한 저장소입니다.

## Docker

### Dockerfile

구름 krampoline 환경에서 이미지를 빌드하기 위한 Dockerfile.

- krampoline의 hub는 base image에 대한 제한 있음
  - 미리 빌드된 것만 사용 가능함
- `krampoline`을 사용하기 위한 설정 포함
- 외부 통신이 안 되는 환경을 위한 프록시 설정 포함

### Dockerfile.prod

`Dockerfile.prod`는 일반적인 환경에서의 일반적으로 최적화된 Docker 이미지.

- Liberica JDK 21 (CDS 지원) 사용
  - JVM 최적화를 위한 CDS(Class Data Sharing)을 포함된 이미지임
- 멀티 스테이지 빌드로 빌드와 실행 환경을 분리

## Build

```bash
docker build -f Dockerfile.prod -t easy-schedule .
```

## Run

9999번 포트로 서비스를 실행합니다.

```bash
docker run -p 9999:8080 easy-schedule
```

web 브라우저나 curl로 접속하여 확인합니다.

```bash
curl http://localhost:9999/api/test
```
