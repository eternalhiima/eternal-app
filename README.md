# eternal-app
> A server side of eternal

> author @taiki0304

## 概要
- eternalのサーバーサイドとしてSpringbootを用いてRESTAPIを構築する

## 環境構築
### eclipse
1. gitからPJをクローン
  - `git clone https://github.com/eternalhiima/eternal-app.git`
- PJをeclipseにimportできるように変換する
  - `gradle eclipse`
  - [gradleをinstallしてない場合](https://gradle.org/install/)
- eclipseにimportする
- eclise上でGradleプロジェクトに変換
  - `eternal-app > 構成 > Gradleネーチャーの追加`をクリック
  - プロジェクトのアイコンにゾウさんが表示されたら成功
- dependencyの更新
  - `eternal-app > Gradle > Gradleプロジェクトのリフレッシュ`
- プロジェクトのビルド
  - `プロジェクト > すべてビルド`

> トラブルシューティング

- 大量のエラーが消えない場合
  - eclipseにlombokがインストールされていない可能性
  - 参考) [Eclipse + STSにlombokをインストールする](https://qiita.com/t-iguchi/items/a0c88df01b6a601afbed)
- eclipseにSTSがインストールされていない
  - eclipseマーケットプレイスより、`Spring Tools`をインストールする
- Gradleネーチャーの追加が表示されない
  - eclipseマーケットプレイスより、`Buildship Gradle Integration`をインストールする

## ディレクトリ構成
```
- java
  - aop // loggerなど
  - config
  - controller
  - converter
  - dto
    - request // requestオブジェクト
    - response  // responseオブジェクト
  - entity
  - exception
  - json
    - desilializer
    - selializer  // enumの独自カスタマイズ
  - logger
  - message
  - repository  // dao
  - service
  - type  // enum
  - util  // sorterなど
- resources
  - application.yml
  - logback.xml
  - message.properties
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
- [YAML](https://yaml.org/)
- [SpringSecurity](https://spring.io/projects/spring-security)
  - 認証系に使用する

## 参考
- [Jacksonのカスタマイズ](http://www.ne.jp/asahi/hishidama/home/tech/java/spring/boot/rest/jackson.html)
- [Qiita - SpringBootでGETのリクエストパラメータのバリデーションをしてバリデーションメッセージを返却するまで](https://qiita.com/shotana/items/e18df97e821d207e642d)
