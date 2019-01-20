# eternal-app
> A server side of eternal

> author @taiki0304

## 概要
- eternalのサーバーサイドとしてRESTAPIを構築する

## 環境構築
- gitからPJをクローン
  - `git clone https://github.com/eternalhiima/eternal-app.git`
- PJをeclipseにimportできるように変換する
  - `gradle eclipse`
- eclipseにimport

## ディレクトリ構成
```
- java
  - aop // loggerなど
  - config
  - controller
  - dto
    - request // requestオブジェクト
    - response  // responseオブジェクト
  - model
  - service
  - type
  - exception
  - entity
  - repository  // dao
  - json
    - desilializer
    - selializer  // enumの独自カスタマイズ
  - util  // sorterなど
- resources
  - application.properties
  - message.properties?
```

## コーディングルール
- DIはコンストラクタインジェクションを使用する
  - `@RequiredArgsConstructor`を使用する
- formatter
  - `~\documents\20.開発\99.tool\eclipse\checkstyle\eclipse-java-eternal-style.xml`
- checkstyle
  - google_checkstyle.xmlを使用
- javadocをちゃんと書く

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
- [Jacksonのカスタマイズ](http://www.ne.jp/asahi/hishidama/home/tech/java/spring/boot/rest/jackson.html)
- [Qiita - SpringBootでGETのリクエストパラメータのバリデーションをしてバリデーションメッセージを返却するまで](https://qiita.com/shotana/items/e18df97e821d207e642d)
