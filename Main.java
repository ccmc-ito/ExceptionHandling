import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("好きなトランプのカード1枚を教えてください。");
        System.out.print("SUITは");
        for(Suit suit : Suit.values()) {
            if(suit.ordinal() > 0) System.out.print(", ");
            System.out.print(suit);
        }
        System.out.print("のどれか、");
        System.out.printf("RANKは%d～%dのどれかを入力してください。\n", Card.MIN_RANK, Card.MAX_RANK);

        Card favo = createFavoCard();   // createFavoCard()は何らかのExceptionが発生する可能性があるメソッド
        System.out.println(favo);
    }

    public static Card createFavoCard() throws IllegalArgumentException, NumberFormatException, RuntimeException  {        // catchされなかったIllegalArgumentException, NumberFormatException, RuntimeExceptionは呼び出し元に渡す
        try(
            Scanner scan = new Scanner(System.in);
        ) {
            System.out.print("- SUIT: ");
            String suitName = scan.nextLine();  // nextLine()に適合しない入力を行うとInputMismatchExceptionが発生
            Suit suit;
            try { suit = Suit.valueOf(suitName); }  // 列挙型Suitの列挙子として不適切な引数の場合はIllegalArgumentExceptionが発生
            catch(IllegalArgumentException e) { throw e; }  // IllegalArgumentExceptionのインスタンスをそのままthrowする。

            System.out.print("- RANK: ");
            int rank = Integer.parseInt(scan.nextLine());   // nextLine()に適合しない入力を行うとInputMismatchExceptionが発生、parseInt()で変換できない場合はNumberFormatExceptionが発生

            Card favoCard = new Card(suit, rank);   // rankに問題がある場合は独自の例外であるCardRankOutOfRangeExceptionが発生
            return favoCard;
        } catch(InputMismatchException | NumberFormatException e) { // InputMismatchExceptionかNumberFormatExceptionが発生した場合
            e.printStackTrace();    // catchされたeの詳細を表示する。
        }
        
        return null;
    }
}
