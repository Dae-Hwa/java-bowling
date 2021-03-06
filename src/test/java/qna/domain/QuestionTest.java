package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

public class QuestionTest {

  public static Question Q1;
  public static Question Q2;
  private static Question QUESTION_WITH_ANSWER_SAME_WRITER;
  private static Question QUESTION_WITH_ANSWER_DIFF_WRITER;
  private static Question QUESTION_HAS_NO_WRITER_AND_ANSWER1;
  private static Question QUESTION_HAS_NO_WRITER_AND_ANSWER2;
  private static Answer ANSWER_FOR_INSERT1;
  private static Answer ANSWER_FOR_INSERT2;

  static {
    setupMethod();
  }

  @AfterEach
  void tearDown() {
    setupMethod();
  }

  static void setupMethod() {
    Q1 = new Question("title1", "contents1").writtenBy(UserTest.JAVAJIGI);
    Q2 = new Question("title2", "contents2").writtenBy(UserTest.SANJIGI);

    QUESTION_WITH_ANSWER_SAME_WRITER = new Question("title3", "contents3")
        .writtenBy(UserTest.JAVAJIGI);
    QUESTION_WITH_ANSWER_SAME_WRITER
        .addAnswer(Answer.getBuilder(UserTest.JAVAJIGI, QuestionTest.QUESTION_WITH_ANSWER_SAME_WRITER)
                .contents("Answers Contents1")
                .build());

    QUESTION_WITH_ANSWER_DIFF_WRITER = new Question("title4", "contents4")
        .writtenBy(UserTest.JAVAJIGI);
    QUESTION_WITH_ANSWER_DIFF_WRITER
        .addAnswer(Answer.getBuilder(UserTest.SANJIGI, QuestionTest.QUESTION_WITH_ANSWER_DIFF_WRITER)
            .contents("Answers Contents2")
            .build());

    QUESTION_HAS_NO_WRITER_AND_ANSWER1 = new Question("title5", "contents5");
    QUESTION_HAS_NO_WRITER_AND_ANSWER2 = new Question("title6", "contents6");

    ANSWER_FOR_INSERT1 = Answer
        .getBuilder(UserTest.JAVAJIGI, QuestionTest.QUESTION_HAS_NO_WRITER_AND_ANSWER1)
        .id(1)
        .contents("Answers For Insert1")
        .build();

    ANSWER_FOR_INSERT2 = Answer
        .getBuilder(UserTest.SANJIGI, QuestionTest.QUESTION_HAS_NO_WRITER_AND_ANSWER2)
        .id(2)
        .contents("Answers For Insert2")
        .build();
  }

  @ParameterizedTest
  @MethodSource("provideQuestionWithLoginUser")
  void writeBy(Question question, User loginUser) {
    question.writtenBy(loginUser);

    assertThat(question.getWriter()).isEqualTo(loginUser);
  }

  static Stream<Arguments> provideQuestionWithLoginUser() {
    return Stream.of(
        arguments(
            QUESTION_HAS_NO_WRITER_AND_ANSWER1,
            UserTest.JAVAJIGI
        ),
        arguments(
            QUESTION_HAS_NO_WRITER_AND_ANSWER2,
            UserTest.SANJIGI
        )
    );
  }


  @ParameterizedTest
  @MethodSource("provideQuestionWithAnswer")
  void addAnswer(Question question, Answer answer) {
    question.addAnswer(answer);

    assertThat(question.getAnswerById(answer.getId())).isEqualTo(answer);
  }

  static Stream<Arguments> provideQuestionWithAnswer() {
    return Stream.of(
        arguments(
            QUESTION_HAS_NO_WRITER_AND_ANSWER1,
            ANSWER_FOR_INSERT1
        ),
        arguments(
            QUESTION_HAS_NO_WRITER_AND_ANSWER2,
            ANSWER_FOR_INSERT2
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideQuestionWithValidLoginUser")
  void isOwner(Question question, User user) {
    assertThat(question.isOwner(user)).isTrue();
  }


  @ParameterizedTest
  @MethodSource("provideQuestionWithValidLoginUser")
  void delete_success(Question question, User loginUser) throws Exception {
    question.delete(loginUser);

    assertThat(question.isDeleted()).isTrue();
  }

  static Stream<Arguments> provideQuestionWithValidLoginUser() {
    return Stream.of(
        arguments(
            Q1,
            UserTest.JAVAJIGI
        ),
        arguments(
            Q2,
            UserTest.SANJIGI
        ),
        arguments(
            QUESTION_WITH_ANSWER_SAME_WRITER,
            UserTest.JAVAJIGI
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideQuestionWithInvalidLoginUser")
  void delete_failure(Question question, User loginUser) {

    assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
      question.delete(loginUser);
    });
  }

  static Stream<Arguments> provideQuestionWithInvalidLoginUser() {
    return Stream.of(
        arguments(
            Q1,
            UserTest.SANJIGI
        ),
        arguments(
            Q2,
            UserTest.JAVAJIGI
        )
    );
  }

  @Test
  void delete_failure_AnswerOfOtherUserExists2() {
    Question question = QUESTION_WITH_ANSWER_DIFF_WRITER;

    assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
      question.delete(UserTest.JAVAJIGI);
    }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
  }

  @ParameterizedTest
  @MethodSource("provideQuestionWithLoginUserToGetDeleteHistories")
  void getDeleteHistories(Question question, User loginUser, List<DeleteHistory> expected)
      throws Exception {
    question.delete(loginUser);

    assertThat(question.getDeleteHistories()).isEqualTo(expected);
  }

  static Stream<Arguments> provideQuestionWithLoginUserToGetDeleteHistories() {
    return Stream.of(
        arguments(
            Q1,
            UserTest.JAVAJIGI,
            Arrays.asList(
                DeleteHistory.createBy(Q1)
            )
        ),
        arguments(
            Q2,
            UserTest.SANJIGI,
            Arrays.asList(
                DeleteHistory.createBy(Q2)
            )
        ),
        arguments(
            QUESTION_WITH_ANSWER_SAME_WRITER,
            UserTest.JAVAJIGI,
            Arrays.asList(
                DeleteHistory.createBy(QUESTION_WITH_ANSWER_SAME_WRITER),
                DeleteHistory.createBy(AnswerTest.A1)
            )
        )
    );
  }
}
