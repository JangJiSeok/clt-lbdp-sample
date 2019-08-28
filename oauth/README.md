## 인증토큰을 얻는 방법  

## 1. postman 에서 확인
-- url 주소 POST
http://localhost:8090/oauth/token

Type : Basic Auth
Username : uengine-client
Password : uengine-secret

-- body 부분
grant_type : password
username : 4@4.com
password : password


## httpie 로 테스트
http --form POST localhost:8090/oauth/token \  
"Authorization: Basic dWVuZ2luZS1jbGllbnQ6dWVuZ2luZS1zZWNyZXQ=" \  
grant_type=password \  
username=4@4.com \  
password=password

-- Authorization: Basic dWVuZ2luZS1jbGllbnQ6dWVuZ2luZS1zZWNyZXQ= 이값은 client ID, client secret을 base64로 변환한 값으로 서버의 사용자그룹을 증명하는 값



http --form POST localhost:8090/oauth/token "Authorization: Basic dWVuZ2luZS1jbGllbnQ6dWVuZ2luZS1zZWNyZXQ=" grant_type=password username=4@4.com password=password

## jks 파일 생성 방법
https://www.lesstif.com/pages/viewpage.action?pageId=20775436  

##git확인
