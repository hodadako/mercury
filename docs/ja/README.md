# マーキュリー(Mercury)

### Notes
> This document was generated based on AI translator, so it may not contain the exact translation of the original docs. I am currently looking for help to translate in other languages, please check out [issues](https://github.com/hodadako/mercury/issues/1) for further context. 

マーキュリーは、人間の自然言語をDNA配列に変換するオープンソースのトランスレータです。  
複雑なゲノム情報と分かりやすい言語表現をつなぐことで、研究者・開発者・バイオ愛好家が生物学データに直感的にアクセスできるようにします。

> Mercuryのコア機能は、DNA配列を解析し、意味のある自然言語文や指定されたバイナリ列に変換することです。  
> これにより、DNAに埋め込まれたメッセージを人間が読める形で解読できます。

## 主な機能
- **双方向変換**:
    - DNA → 自然言語 / バイナリ
    - 自然言語 / バイナリ → DNA

- **コドン対応エンコーディング**:
    - すべてのDNA配列は開始/終止コドンを含み、3塩基整列を維持します。

## DNAエンコーディングの背景

DNAは4つの塩基（A, C, G, T）で構成されています。  
マーキュリーはこの限られたが情報量の多いアルファベットを活用し、バイナリやテキストデータを生物学的規則に従ってエンコードします。

- **開始コドン**: `ATG`（開始信号）
- **終止コドン**: `TAA`, `TAG`, `TGA`（終了信号）
- **コドン整列**: DNAは**3塩基（コドン）**単位で解釈

マーキュリーはすべてのDNA配列がこのコドン構造に従うようにし、生物学的妥当性とデコード精度を保証します。

## 変換プロセス

### 変換テーブル

マーキュリーは各塩基を2ビットで表現します。  
以下はデフォルトのマッピング表です：

| 塩基 | バイナリ |
|------|----------|
| A    | 00       |
| C    | 01       |
| G    | 10       |
| T    | 11       |

> この表はバイナリ ↔ DNA変換時に使用されます。

### パディング

DNA配列は**3塩基（コドン）**単位で整列する必要があります。  
2ビットエンコーディング（1バイトあたり4塩基）のため、変換結果が3の倍数にならない場合があります。

マーキュリーは次のルールでパディングを行います：

#### 1. パディング塩基（`A`）

DNA長が3の倍数でない場合、末尾にパディング塩基（`A`, 00）を追加します。  
パディング後、終止コドンを付加する前にDNA長が3の倍数になるようにします。

#### 2. パディング長と終止コドンの選択

パディング塩基の数によって終止コドンが決まります。  
この仕組みにより、デコーダは終止コドンだけで元のデータ長を推定できます。

| 終止コドン | パディング塩基数 |
|------------|------------------|
| TAA        | 0                |
| TAG        | 1                |
| TGA        | 2                |

---

### 例

#### 入力
- テキスト: `"A"`
- エンコーディング: UTF-8 → `0x41` → バイナリ: `01000001`（8ビット）

#### プロセス

1. **バイナリ → DNA（2ビットずつ）:**

| ビット | 塩基 |
|--------|------|
| 01     | C    |
| 00     | A    |
| 00     | A    |
| 01     | C    |

_DNA: `CAAC`（4塩基）_

2. **パディング決定:**  
長さ4 → 4 % 3 = 1 → 2個パディング必要

3. **パディング追加:**  
`CAAC` + `AA` = `CAACAA`（6塩基、3の倍数）

4. **終止コドン選択:**  
パディング2個 → `TGA`

---

> **最終DNA:** `ATGCAACAATGA`

## 使い方
### Gradle
```kotlin
dependencies {
    implementation("io.github.hodadako:mercury-core:0.1.2")
}
```

### In Code
```java
MercuryTranslator translator = new SimpleMercuryTranslator();

String dna = translator.encode("いつもあなたと一緒にいます");
// ATGTGATGAACGACATGATGAACGGCATGATGAAGGAAGTGATGAACGAAGTGATGAACGGGGTGATGAACGCTTTGATGAACGGGATGCAGTGAGAAATGCTGTCTGCAGTGATGAACGGGTTGATGAACGACATGATGAACGTTGTGATGAACGCGCTAA
String text = translator.decode(dna);
// いつもあなたと一緒にいます
```

## コントリビュート方法
`後日案内`

## 問題・バグ

問題が発生した場合は[GitHub Issues](https://github.com/your-repo/mercury/issues)にご報告ください。

- 再現手順
- 期待結果と実際の結果
- 環境情報（OS、Java、Mercuryバージョン）
- エラーメッセージやスタックトレース

皆様のフィードバックと貢献をお待ちしています！

## インスピレーション

本プロジェクトは[hodadako](https://github.com/hodadako)氏の[G-Witchシリーズ](https://en.gundam.info/about-gundam/series-pages/witch/)への情熱に触発されました。

<details>
<summary>ネタバレ注意：重要なシーンの説明</summary>

主人公ミオリネ・レンブランが母親の残したトマトのDNAから隠されたメッセージを発見するシーンが着想源です。  
アニメではJavaベースのシステムでDNAメッセージを解読し、セキュリティシステムを解除する様子が描かれています。

</details>
<br>

[祝福](https://youtu.be/3eytpBOkOFA?si=lWngRVD31NY4kCwh).