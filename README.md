# Spring Boot 배포 PoC

이 저장소는 Docker를 사용해 Spring Boot 앱을 배포하는 테스트용 저장소입니다.

## Docker

### Dockerfile

- 구름 krampoline 환경에서 빌드용 이미지를 생성하기 위한 Dockerfile.
- 특징:
  - 베이스 이미지 제한 (미리 빌드된 것만 사용 가능했음)
  - krampoline 설정 및 프록시 설정 포함
    - 배포된 컨테이너의 환경에서는 외부와의 통신이 제한되어 있어 프록시 설정이 필요했음

### Dockerfile.prod

- 일반 환경에 최적화된 Docker 이미지
- 특징:
  - Liberica JDK 21 (CDS 지원) 사용
    - CDS: 클래스 데이터를 미리 로드해 JVM 시작 시간을 단축 가능
  - 멀티 스테이지 빌드를 통해 빌드와 실행 환경 분리
  - Gradle 8.12 설치 및 빌드 수행
  - 디버깅용 도구들

### Build

간단하게 로컬에서 빌드 후 실행하는 방법입니다.

```bash
# 빌드
docker build -f Dockerfile.prod -t easy-schedule .
# 9999번 포트로 서비스 실행
docker run -p 9999:8080 easy-schedule
# web 브라우저나 curl로 접속
curl http://localhost:9999/api/test
```

#### 이미지 업로드

DockerHub에 이미지를 업로드하는 방법입니다.

버전명은 변경해주세요.

```bash
# 이미지 빌드
docker build -f Dockerfile.prod -t codinggroot/easy-schedule:0.0.1 .
# 로컬에서 테스트
docker run -p 9999:8080 codinggroot/easy-schedule:0.0.1
curl http://localhost:9999/api/test
# DockerHub에 이미지 업로드
# (처음이면) docker login
docker push codinggroot/easy-schedule:0.0.1
```

### Run

DockerHub에서 이미지를 다운로드 받아 실행하는 방법입니다.

```bash
# 9999번 포트로 서비스 실행
docker run -p 9999:8080 codinggroot/easy-schedule:0.0.1
# web 브라우저나 curl로 접속
curl http://localhost:9999/api/test
```
