# Get_NHK_ProgramMS-Repo
# NHKが提供するAPIについて
NHKでは、番組表のデータをAPI（Application Programming Interface）で提供します。  
番組表APIは、全国のNHK放送番組のタイトルや放送時間などの番組情報を提供するものです。  

# 番組表API
<details><summary>Program List API</summary>

放送地域、サービス（放送波）を指定することで、現在放送している番組情報を取得することが可能です。  
#### Resource URL
```
https://api.nhk.or.jp/v2/pg/list/{area}/{service}/{date}.json?key={apikey}
```
#### Resource Infomation
| 項目 | 説明 |
|:-----------|------------:|
| リクエスト制限 | なし |
| 認証     | APIキーによる認証 |
| HTTPメソッド | GET |
| レスポンスフォーマット | json |
| レスポンスオブジェクト | List |
| APIバージョン | v2 |
|利用回数制限 | 300回/日 |

#### Request Parameters
パラメータ名及びパラメータ値は大文字小文字を区別します。  
| パラメータ | 必須 | 説明 | 値の例 |
|:-----------|------------:|:------------:|:------------:|
| area       | 〇 | 地域ID(3byte)。| [こちら](https://github.com/Masaki0625/Get_NHK_ProgramMS-Repo/blob/main/Doc/ExplainDocumentRequest.md#%E3%83%AA%E3%82%AF%E3%82%A8%E3%82%B9%E3%83%88%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF)を参照 |
| service    | 〇 | サービスID(2byte)。| [こちら](https://github.com/Masaki0625/Get_NHK_ProgramMS-Repo/blob/main/Doc/ExplainDocumentRequest.md#%E3%83%AA%E3%82%AF%E3%82%A8%E3%82%B9%E3%83%88%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF)を参照 |
| date       | 〇 | 日付（YYYY-MM-DD形式、当日から1週間先までの日付を指定） | 例）2022-09-17 |
| apikey     | 〇 | APIキー(32byte)。| _ENTER_YOUR_KEY_ |

### Error
Program List APIは番組表APIに共通の[エラーメッセージおよびコード](https://github.com/Masaki0625/Get_NHK_ProgramMSRepo/blob/main/Doc/ExplainDocumentRequest.md#%E3%82%A8%E3%83%A9%E3%83%BC%E3%83%A1%E3%83%83%E3%82%BB%E3%83%BC%E3%82%B8)を返します。  
</details>
