# 例外処理のサンプル
例外処理のサンプルプログラム。好きなトランプのカード1枚を指定するため、スート（マーク）とランク（数）を入力させる。そのときに不適切な入力を行った場合に例外が発生する。

mainメソッドがあるMainクラスの他に、トランプのカード1枚を表す独自クラスの**Card**、トランプのスートを表す列挙型の**Suit**、トランプのランクを設定しようとしたときに不適な値であることを示す例外クラスの**CardRankOutOfRange**の3つがある。

## 独自の例外クラスの作り方
    public class CardRankOutOfRangeException extends RuntimeException {
        CardRankOutOfRangeException(){}
        CardRankOutOfRangeException(String message){ super(message); }
    }

通常通りに「extends」を用いて例外クラスを継承する。メソッドやフィールドは変更する必要はない。引数がないコンストラクタ以外を使いたい場合は、コンストラクタを自分で定義する。

## 例外の発生
    throw new CardRankOutOfRangeException("RANKが範囲外です。");

**throw**文を利用する。「throw」の後に例外クラスのインスタンスを指定する。

## 例外処理
    try { suit = Suit.valueOf(suitName); }
    catch(IllegalArgumentException e) { throw e; }

try-catch文を利用する。try句で例外が発生するか監視し、例外が発生した場合は対応するcatch句に移動する。

## 複数種類のcatch句
    catch(InputMismatchException | NumberFormatException e) {

Java SE 7以降では、「|」を使ってcatchする例外クラスの種類を複数まとめて指定することができる。

## try-with-resources文
    try(
        Scanner scan = new Scanner(System.in);
    ) {

Java SE 7以降では、特定のクラスをtry句の()の中で定義することで自動的にclose()等の処理を行うようになった。以前はfinally句に記述する必要があった部分を丸ごと省略することができる。

## 例外処理の委任
    public Card(Suit suit, int rank) throws CardRankOutOfRangeException {

メソッドやコンストラクタのthrows句で指定した例外は、そのメソッド（コンストラクタ）内で発生してcatchされなかった場合に呼び出し元に渡される。呼び出し元は例外処理が委任されるため、そのメソッド（コンストラクタ）に対してtry-catch文を使うなどの対応が求められる。

try-catch文などで対応されず、誰にも委任されない例外が生じたときはプログラムが停止して標準エラー出力にメッセージが表示される。

