import java.util.Random;
import java.util.Scanner;

/**
 * @author SMJin
 * baseballGame Class
 * */
public class Baseball {

    private static final int NUMBER_LENGTH = 3;         // 정답 숫자열, 사용자 입력 숫자열 모두 길이는 3개로 고정이다.
    private static final int INIT_NUMBER = 0;           // 정답 숫자열의 초기화 값.

    private static final int CONTINUE_GAME = 1;         // 사용자가 1을 입력하면, 게임 다시 시작.

    private static final int RANDOM_MAX = 9;            // 컴퓨터가 숫자 초기화시, 랜덤으로 뽑을 숫자 중 가장 큰 값.
    private static final int RAND0M_MIN = 1;            // 컴퓨터가 숫자 초기화시, 랜덤으로 뽑을 숫자 중 가장 작은 값.

    public static void main(String[] args) {

        int answerNum[] = new int[NUMBER_LENGTH];        // 정답 숫자들을 넣을 배열
        int num[] = new int[NUMBER_LENGTH];             // 사용자가 입력한 숫자들을 넣을 배열
        boolean finish = false;                         // 정답을 맞출 때까지 정답을 입력받을 수 있게 하는 while 문을 멈추게 할 boolean 형 변수

        Scanner scan = new Scanner(System.in);

        while (isOverlap(answerNum)) {      // 정답 숫자들이 서로 겹치지 않게 해주는 while 문. 겹치지 않을 때까지 돌린다.
            answerNum = initBaseballNumber(INIT_NUMBER, INIT_NUMBER, INIT_NUMBER);
        }

        while (!finish) {
            System.out.print("숫자를 입력해주세요 : ");
            int UserNumber = scan.nextInt();
            num[0] = UserNumber / 100;
            num[1] = (UserNumber % 100) / 10;
            num[2] = UserNumber % 10;

            finish = printResult(isStrike(answerNum, num),
                    isBall(answerNum, num));

            if(finish) {
                if (scan.nextInt() == CONTINUE_GAME) {
                    finish = false;
                }
            }

        }

    }

    /*
    정답 숫자들을 랜덤으로 생성하여 int형 배열로 반환하여 주는 메소드이다.
     */
    static private int[] initBaseballNumber(int answerNumA,
                                            int answerNumB, int answerNumC) {
        Random random = new Random();
        answerNumA = random.nextInt(RANDOM_MAX) + RAND0M_MIN;
        answerNumB = random.nextInt(RANDOM_MAX) + RAND0M_MIN;
        answerNumC = random.nextInt(RANDOM_MAX) + RAND0M_MIN;

        int answerNum[] = {answerNumA, answerNumB, answerNumC};

        return answerNum;
    }

    /*
    생성된 정답 숫자열이 서로 중복되는지 확인해서 중복되었는지 확인하는 메소드이다.
     */
    static private boolean isOverlap(int[] answerNum) {
        for(int i = 0; i < 3; i ++) {
            if ((answerNum[i] == answerNum[(i + 1) % 3])
                    || (answerNum[i] == answerNum[(i + 2) % 3])) {
                return true;
            }
        }
        return false;
    }

   /*
    int형 정답 배열과 int형 사용자가 입력한 배열을 인자로 넣으면, Strike 횟수를 반환해주는 메소드이다.
     */
    static private int isStrike(int answerNum[], int num[]) {
        int strikeNumber = 0;

        for (int i = 0; i < 3; i ++) {
            if (isSame(answerNum[i], num[i])) {
                strikeNumber++;
            }
        }

        return strikeNumber;
    }

    /*
    int형 정답 배열과 int형 사용자가 입력한 배열을 인자로 넣으면, Ball 횟수를 반환해주는 메소드이다.
     */
    static private int isBall(int answerNum[], int num[]) {
        int ballNumber = 0;

        for (int i = 0; i < 3; i ++) {
            if (isSame(answerNum[i], num[(i + 1) % 3])) {
                ballNumber++;
            }
            if (isSame(answerNum[i], num[(i + 2) % 3])) {
                ballNumber++;
            }
        }

        return ballNumber;
    }

    /*
    인자로 들어온 두 int 형 숫자가 같으면 TRUE 를 반환하는 메소드이다.
     */
    static private boolean isSame(int answerNum, int num) {
        return (answerNum == num);
    }

    /*
    STRIKE 횟수와 BALL 횟수를 넣으면 추측에 대한 결과 프린트해주는 메소드이다.
    메인 메소드에서는 이 메소드를 while 문으로 반복하여 돌려준다. 정답을 맞출때까지!(메소드가 TRUE를 반환할 때까지!)
     */
    static private boolean printResult(int strikeNumber, int ballNumber) {
        boolean finish = false;
        if (strikeNumber == NUMBER_LENGTH) {
            println("3개의 숫자를 모두 맞히셨습니다! 게임 종료" +
                    "\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            finish = true;
        } else if ((strikeNumber == 0) && (ballNumber == 0)) {
            println("낫싱");
        } else if ((strikeNumber == 0) && (ballNumber != 0)) {
            println(ballNumber + "볼");
        } else if ((strikeNumber != 0) && (ballNumber == 0)) {
            println(strikeNumber + " 스트라이크");
        } else {
            println(strikeNumber + " 스트라이크 " + ballNumber + "볼");
        }

        return finish;
    }

    /*
    String 형 인자를 넣으면 출력해주는 메소드이다.
     */
    static private void println(String output) {
        System.out.println(output);
    }

}
