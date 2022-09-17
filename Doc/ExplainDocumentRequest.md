# ドキュメントリクエストの説明

## リクエストパラメータ
* パラメータ  
service
* 説明  
g1:NHK総合１  
g2:NHK総合２  
e1:NHKEテレ１  
e2:NHKEテレ２  
e3:NHKEテレ３  
e4:NHKワンセグ２  
s1:NHKBS１  
s2:NHKBS1(102ch)  
s3:NHKBSプレミアム  
s4:NHKBSプレミアム(104ch)
r1:NHKラジオ第1  
r2:NHKラジオ第2
r3:NHKFM  
n1:NHKネットラジオ第1  
n2:NHKネットラジオ第2  
n3:NHKネットラジオFM  
===============================================================================  
tv:テレビ全て  
radio:ラジオ全て
netradio:ネットラジオ全て  

* パラメータ  
area
* 説明  
010:札幌, 011:函館, 012:旭川, 013:帯広, 014:釧路, 015:北見, 016:室蘭, 020:青森,  
030:盛岡, 040:仙台, 050:秋田, 060:山形, 070:福島,080:水戸, 090:宇都宮, 100:前橋,  
110:さいたま, 120:千葉, 130:東京, 140:横浜,150:新潟, 160:富山, 170:金沢, 180:福井,  
190:甲府, 200:長野, 210:岐阜, 220:静岡, 230:名古屋, 240:津, 250:大津, 260:京都,  
270:大阪, 280:神戸, 290:奈良, 300:和歌山,310:鳥取, 320:松江, 330:岡山,340:広島,  
350:山口, 360:徳島, 370:高松, 380:松山, 390:高知, 400:福岡, 401:北九州, 410:佐賀,  
420:長崎, 430:熊本, 440:大分, 450:宮崎, 460:鹿児島, 470:沖縄  

* パラメータ  
genre
* 説明  
ARIB STD-B10 デジタル放送に使用する番組配列情報標準規格 5.1版  
第2部番組配列情報における基本情報のデータ構造と定義  
「付録Ｈ:コンテント記述子におけるジャンル指定」で定義される  
ジャンル大分類の値とジャンル中分類の値を  
それぞれ10進数（上位桁を0詰めする）として連結したものとする  
XXYY:{ジャンル大分類}{ジャンル中分類}  
（例）0000:定時・総合, 0001:天気, 0100:スポーツニュース, 0101:野球, 0102サッカー  
ARIB STD-B10 デジタル放送に使用する番組配列情報標準規格 5.1版  
http://www.arib.or.jp/english/html/overview/doc/2-STD-B10v5_1.pdf  

## レスポンスオブジェクト

`List`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| list | Collection of Program | 番組 | 〇 |

`DescriptionList`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| list | Collection of Program | 番組 | 〇 |

`NowOnAirList`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| nowonair_list | Collection of NowOnAir | 現在提供中の番組 | 〇 |

`NowOnAir`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| previous | Program | 前に放送した番組 | △ |
| present | Program | 現在放送中の番組 | ◯ |
| following | Program | 次に放送予定の番組 | △ |

`Program`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| id | String | 番組ID | ◯ |
| event_id | String | 番組イベントID | ◯ |
| start_time | String | 放送開始日時（YYYY-MM-DDTHH:mm:ssZ形式） | ◯ |
| end_time | String | 放送終了日時（YYYY-MM-DDTHH:mm:ssZ形式） | ◯ |
| area | Area | Areaオブジェクト | ◯ |
| service | Service | Serviceオブジェクト | ◯ |
| title | String | 番組名 | ◯ |
| subtitle | String | 番組内容 | ◯ |
| genres | Array of String | 番組ジャンル | ◯ |

`Description`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| id | String | 番組ID | ◯ |
| event_id | String | 番組イベントID | ◯ |
| start_time | String | 放送開始日時（YYYY-MM-DDTHH:mm:ssZ形式） | ◯ |
| end_time | String | 放送終了日時（YYYY-MM-DDTHH:mm:ssZ形式） | ◯ |
| area | Area | Areaオブジェクト | ◯ |
| service | Service | Serviceオブジェクト | ◯ |
| title | String | 番組名 | ◯ |
| subtitle | String | 番組内容 | ◯ |
| content | String | 番組詳細 | ◯ |
| act | String | 出演者 | ◯ |
| genres | Array of String | 番組ジャンル | ◯ |
| program_logo | Logo | 番組ロゴ画像（Logoオブジェクト） | ◯ |
| program_url | String | 番組サイトURL（番組単位） | △ |
| episode_url | String | 番組サイトURL（放送回単位） | △ |
| hashtags | Array of String | 番組に関連するハッシュタグ | ◯ |
| extras | Extra | 拡張情報（Extraオブジェクト） | △ |

`Aria`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| id | String | 地域ID | ◯ |
| name | String | 地域名 | ◯ |

`Service`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| id | String | サービスID | ◯ |
| name | String | サービス名 | ◯ |
| logo_s | Logo | サービスロゴ画像:小（Logoオブジェクト） | ◯ |
| logo_m | Logo | サービスロゴ画像:中（Logoオブジェクト） | ◯ |
| logo_l | Logo | サービスロゴ画像:大（Logoオブジェクト） | ◯ |

`Logo`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| url | String | ロゴ画像のURL | △ |
| width | String | ロゴ画像の幅 | △ |
| height | String | ロゴ画像の高さ | △ |

`Extra`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| ondemand_program | Link | NHKオンデマンドのコンテンツ(番組単位) | △ |
| ondemand_episode | Link | NHKオンデマンドのコンテンツ(放送回単位) | △ |

`Link`  
| フィールド | 型 | 詳細 | 出力 |
|:-----------|:------------:|:------------:|:------------:|
| url | String | URL | △ |
| title | String | タイトル | △ |
| id | String | ID | △ |

※出力...〇：値が空の場合でも出力されるフィールド、△：値が空の場合出力されないフィールド  

## エラーメッセージ
APIのリクエスト時に問題があった場合には、HTTPステータスコードとともにレスポンスを返します。  

`HTTP Status Codes`  
| コード | 説明 | 詳細 |
|:-----------|:------------:|:------------:|
| 200 | OK | 正常 |
| 304 | Not Modified | アクセスされたリソースが更新されていない場合に返されます。 |
| 400 | Bad Request | パラメータがAPIで期待されたものと一致しない場合に返されます。 |
| 401 | Unauthorized | 許可されていないアクセスであった場合に返されます。 |
| 403 | Forbidden | リソースへのアクセスを許されていないか、利用制限を超えている場合に適用されます。 |
| 404 | Not Found | 指定されたリソースが見つからない場合に返されます。 |
| 500 | Internal Server Error | 内部的な問題によってデータを返すことができない場合に返されます。 |
| 503 | Service Unavailable | 内部的な問題によってデータを返すことができない場合に返されます。 |

`Error Message`
```
1.{

2.  "error":

3.  {

4.    "code" : 1,

5.    "message" : "Invalid parameters "

6.  }

7.}
```

`Error Codes`
| コード | 説明 |
|:-----------|:------------:|
| 1 | Invalid parameters |
| 2 | Sorry, that page does not exist  |
| 3 | Rate limit exceeded  |
| 4 | Invalid or expired token  |
| 5 | Over capacity |
| 6 | Internal error |

## 参照
[NHK番組表API](https://api-portal.nhk.or.jp/doc-request#error_codes)
