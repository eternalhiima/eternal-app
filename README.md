# eternal-app
A server side of eternal

## 概要
- eternalのサーバーサイドとしてRESTAPIを構築する

## 環境構築
- gitからPJをクローン
  - `git clone https://github.com/eternalhiima/eternal-app.git`
- PJをeclipseにimportできるように変換する
  - `gradle eclipse`
- eclipseにimport

## ディレクトリ構成
- **TODO:exceptionHandler的なのをどこに置くのか**

```
- java
  - aop // loggerなど
  - controller
  - dto
    - request // requestオブジェクト
    - response  // responseオブジェクト
  - domain
    - service
    - model
    - type
  - infrastructure
    - repository  // dao
    - entity
  - util  // sorterなど
- resources
  - application.properties
```

## コーディングルール
- DIはコンストラクタインジェクションを使用する
- javadocをちゃんと書く

## 実装例
- controller

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/hiima/api/internal/v1")
public class RefTalkThemeController {

	private final TalkThemeService talkThemeService;

	/**
	 * {APIのタイトル}
	 * @param Request
	 * @return Response
	 */
	@RequestMapping(value="/talkDetail", method=RequestMethod.GET)
	public TalkThemeDetailResponse getTalkThemeDetail(@RequestParam TalkThemeDetailRequest request) {
		return talkThemeService.getTalkThemeDetail(request);
	}
  ...
 }
```
- service
  - interfaceを作成する
  - コードは省略
  
- repository

```java
/** TODO */
```
- entity

```java
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TalkThemeEntity {

  private BigDecimal id;
  ...
}
```
- formatter
  - **TODO:何を使うか選定する**
- checkstyle
  - **TODO:何を使うか選定する**
  
## 使用技術
- [Java8](https://docs.oracle.com/javase/8/docs/api/)
- [Springboot](http://spring.io/projects/spring-boot)
- [gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/jp/)
- [Redis](https://redis.io/)
  - 認証にセッションキーを持ったりとかに使用する
- [Lombok](https://projectlombok.org/)
- [Jackson](https://github.com/FasterXML/jackson)
- [SpringSecurity](https://spring.io/projects/spring-security)
  - 認証系に使用する

## 参考
- **TODO:参考になるページのリンクとかを**
