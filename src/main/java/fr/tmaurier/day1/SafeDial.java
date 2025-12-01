package fr.tmaurier.day1;

import java.util.List;

public class SafeDial {

  private static final int DIAL_SIZE = 100;

  private record State(int pos, int hits) {}

  public int applyRotation(int position, String instruction) {
    char direction = instruction.charAt(0);
    int distance = Integer.parseInt(instruction.substring(1));

    if (direction == 'R') {
      position = (position + distance) % DIAL_SIZE;
    } else if (direction == 'L') {
      position = (position - distance + DIAL_SIZE) % DIAL_SIZE;
    } else {
      throw new IllegalArgumentException("Unsupported direction: " + direction);
    }
    return position;
  }

  public int applyRotations(int startPosition, List<String> instructions) {
    return instructions.stream().reduce(startPosition, this::applyRotation, (left, right) -> right);
  }

  public int countZeros(List<String> instructions, int startPosition) {
    return instructions.stream()
        .reduce(
            new State(startPosition, 0),
            (state, instr) -> {
              int pos = applyRotation(state.pos(), instr);
              return new State(pos, state.hits() + (pos == 0 ? 1 : 0));
            },
            (s1, s2) -> new State(s2.pos(), s1.hits() + s2.hits()))
        .hits();
  }

  public int countZerosAllClicks(List<String> instructions, int startPosition) {
    return instructions.stream()
        .reduce(
            new State(startPosition, 0),
            (state, instr) -> {
              char direction = instr.charAt(0);
              int distance = Integer.parseInt(instr.substring(1));
              int pos = state.pos();
              int hits = state.hits();

              for (int i = 0; i < distance; i++) {
                if (direction == 'R') {
                  pos = (pos + 1) % DIAL_SIZE;
                } else if (direction == 'L') {
                  pos = (pos - 1 + DIAL_SIZE) % DIAL_SIZE;
                } else {
                  throw new IllegalArgumentException("Unsupported direction: " + direction);
                }
                if (pos == 0) {
                  hits++;
                }
              }
              return new State(pos, hits);
            },
            (s1, s2) -> new State(s2.pos(), s1.hits() + s2.hits()))
        .hits();
  }
}
