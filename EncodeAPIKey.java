import java.util.Base64;

public class EncodeAPIKey {
	
	/**
	* 使い方
	* .javaファイルをコマンドプロンプトでコンパイルします。
	* 例）javac EncodeAPIKey.java
	* コンパイル完了後、以下実行コマンドの第一引数にapiKeyを指定します。
	* 例）java EncodeAPIKey _Enter_Your_APIKey_
	* 下記実行結果が出力されます。
	* -----------------------------------------------------------------
	* エンコードされたapiKey
	* Processing Time: 0 ms
	* -----------------------------------------------------------------
	*
	* エンコードされたapiKeyをJSONリクエストボディに入れ、MSへリクエスト
	*/
	
	public static void main(String[] args) {
		//処理開始時間
		long startTime = System.currentTimeMillis();
		
		//APIKeyのエンコード
		String encodeAPIKey = Base64.getEncoder().encodeToString(args[0].getBytes());
		
		//エンコードされたAPIKeyの出力
		System.out.println(encodeAPIKey);
		
		//処理終了時間
		long endTime = System.currentTimeMillis();
		
		//合計処理時間
		System.out.println("Processing Time: " + (endTime - startTime) + " ms");
	}
}
