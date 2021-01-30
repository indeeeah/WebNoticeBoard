# WebNoticeBoard
--------
## Contents
1. [설계의 주안점](#설계의-주안점)
2. [Using](#Using)
3. [WebNoticeBoard 기능 설명](#WebNoticeBoard-기능-설명)
4. [추후 구현 예정 기능](#추후-구현-예정-기능)
--------
설계의 주안점
1. MVC Model 2 방식을 사용한 CRUD 기능
2. 부트스트랩을 이용한 간단한 UI 구성
3. 원하는 폴더에 업로드된 파일 저장
4. 페이징 처리
---
## Using
1. FrontEnd - HTML5, CSS3, JS, Bootstrap
2. BackEnd - Java(JDK 1.8), Servlet
3. OS - MacOs
4. Library&API - json-simple-1.1.1.jar, COS, JSTL, Bootstrap
5. IDE - Eclipse(EE, 2020-06)
6. Server - Tomcat(v8.5)
7. Cl - Github, git
8. DataBase - Oracle DataBase 11g
------
## WebNoticeBoard 기능 설명
### [게시판 목록 페이지]
<img width="1438" alt="스크린샷 2021-01-30 오후 7 36 23" src="https://user-images.githubusercontent.com/72774483/106354703-0c8f4880-6337-11eb-8d64-7baf77be0b0f.png">

> + 게시판 목록 조회 기능
> + 한 페이지에 3개의 글씩 페이징 기능

### [게시글 입력 페이지]
<img width="1433" alt="스크린샷 2021-01-30 오후 7 36 15" src="https://user-images.githubusercontent.com/72774483/106354899-3432e080-6338-11eb-91be-ca436357a71f.png">

> + 게시글, 답글 입력 기능
> + 단일 파일 업로드 기능
> + 다시작성 버튼을 눌러 내용 리셋 가능

### [단일 게시글 조회 기능]
<img width="1432" alt="스크린샷 2021-01-30 오후 7 38 31" src="https://user-images.githubusercontent.com/72774483/106354949-8247e400-6338-11eb-972d-d14bc9baef6b.png">

> + 단일 게시글 조회 기능
> + 게시글 삭제 기능
----
## 추후 구현 예정 기능
1. 게시물 클릭 시 조회수 증가 기능
2. 게시물 수정 기능
3. UI 개선
4. 게시물 재 다운로드 기능