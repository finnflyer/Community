### Learning Community Code 

## Quick Start
1. Install tools for this project 
JDK,Maven, IDEA
2. Git Clone code to local machine  
3. Run the SQL to build the database.


##  Sources
[Spring 文档](https://spring.io/guides)    
[Spring Web](https://spring.io/guides/gs/serving-web-content/) 
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)    
[Bootstrap](https://v3.bootcss.com/getting-started/)    
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)    
[Gitee OAuth](https://gitee.com/api/v5/oauth_doc#/)

## Tools
[Git](https://git-scm.com/download)   
[Visual Paradigm](https://www.visual-paradigm.com)

## Sql
```$xslt
create table USER
(
	ID INTEGER default auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```    