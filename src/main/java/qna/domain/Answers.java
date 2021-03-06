package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.NotFoundException;

@Embeddable
public class Answers {

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private final List<Answer> answers;

  public Answers() {
    answers = new ArrayList<>();
  }

  public void addAnswer(Answer answer) {
    answers.add(answer);
  }

  public Answer getAnswerById(long id) {
    return answers.stream()
        .filter(answer -> answer.getId() == id)
        .findAny()
        .orElseThrow(NotFoundException::new);
  }

  public void delete(User loginUser) {
      answers.forEach(answer -> answer.delete(loginUser));
  }

  public List<DeleteHistory> getDeleteHistories() {
    return answers.stream()
        .filter(Answer::isDeleted)
        .map(DeleteHistory::createBy).collect(Collectors.toList());
  }
}
