# open jdk 17 버전의 환경을 구성
FROM openjdk:17-alpine

# Python3와 pip 설치
RUN apk add --update python3 py3-pip

# 필요한 빌드 의존성 설치
RUN apk add --no-cache gcc musl-dev python3-dev libffi-dev openssl-dev make

# pip, setuptools, wheel 업그레이드
RUN pip3 install --upgrade pip setuptools wheel

# Python 의존성 패키지 설치
RUN pip3 install googletrans==4.0.0rc1
RUN pip3 install openai==0.28.1
RUN pip3 install httpx==0.13.3
RUN pip3 install Pillow==10.1.0

# 환경 변수 설정으로 스크립트 경로 설정
ENV FLOWERLAB_SCRIPT_PATH=/app/flowerlab-model/main.py

# build가 되는 시점에 JAR_FILE이라는 변수 명에 build/libs/*.jar 선언
# build/libs - gradle로 빌드했을 때 jar 파일이 생성되는 경로
ARG JAR_FILE=build/libs/*.jar

# JAR_FILE을 flowerlab.jar로 복사
COPY ${JAR_FILE} flowerlab.jar

# 운영 및 개발에서 사용되는 환경 설정을 분리
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=real", "/flowerlab.jar"]
