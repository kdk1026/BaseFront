------------
- 프로젝트 구동 -
------------
1. Maven Project 전환
	- 프로젝트 우클릭 > Configure > Convert to Maven Project

2. /CoeaFront/src/main/resources/application.properties
	spring.profiles.active=local 확인

3. 이클립스에 STS 플러그인 있는 경우,
	프로젝트 우클릭 > Run As > Spring Boot App

	* InfoGov인 경우, STS 3 플러그인은 설치 가능
		- 설치 희망하는 경우, Spring IDE Core 플러그인은 삭제
		- STS 4 플러그인은 2020-09 버전부터 지원
		- 이클립스 2020-09 버전부터는 Java 11 필요
			> Java 8_211 부터는 유료 이므로 OpenJDK 설치 (Zulu JDK 추천)

4. 이클립스에 STS 플러그인 없는 경우,
	com.infoin.CoeaFrontApplication 열기 > 우클릭 > Run As > Java Application

--------------------------------
- application.properties 파일 관련 -
--------------------------------
1. 이클립스 + STS 플러그인 또는 STS 권장

	* STS 실행시켜서 Import 해서 properties 작업
		- 작업 이후 삭제 시, 체크는 하지 말 것 ('Delete project contents on disk ... ')
		- 삭제한 경우에는 SVN에서 다시 체크아웃 받을 것

2. properties 우클릭 > Open With > Generic Editor - Spring Properties
3. Ctrl + Space 하면 자동완성 보기 지원

--------------------------------
- 배포 후, 실행 명령어 -
--------------------------------
java -jar CoeaFront-0.1.jar --spring.profiles.active=dev &
java -jar -Dspring.profiles.active=dev CoeaFront-0.1.jar &