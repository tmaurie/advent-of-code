package fr.tmaurier.day6;

import java.util.ArrayList;
import java.util.List;

public class TrashCompactor {
  public long solveWorksheet(List<String> lines) {
    if (lines.isEmpty()) return 0L;

    validateLines(lines);

    List<int[]> blocks = findProblemBlocks(lines);
    return calculateTotal(lines, blocks, this::solveProblem);
  }

  private void validateLines(List<String> lines) {
    int width = lines.getFirst().length();
    for (String line : lines) {
      if (line.length() != width) {
        throw new IllegalArgumentException("All lines must have same length");
      }
    }
  }

  private long calculateTotal(List<String> lines, List<int[]> blocks, Solver solver) {
    long total = 0L;
    for (int[] block : blocks) {
      total += solver.solve(lines, block[0], block[1]);
    }
    return total;
  }

  private List<int[]> findProblemBlocks(List<String> lines) {
    int cols = lines.getFirst().length();
    List<int[]> blocks = new ArrayList<>();
    boolean inBlock = false;
    int blockStart = -1;

    for (int c = 0; c < cols; c++) {
      boolean allSpaces = isColumnEmpty(lines, c);
      if (!allSpaces) {
        if (!inBlock) {
          inBlock = true;
          blockStart = c;
        }
      } else if (inBlock) {
        blocks.add(new int[] {blockStart, c - 1});
        inBlock = false;
      }
    }

    if (inBlock) blocks.add(new int[] {blockStart, cols - 1});
    return blocks;
  }

  private boolean isColumnEmpty(List<String> lines, int col) {
    for (String line : lines) {
      if (line.charAt(col) != ' ') return false;
    }
    return true;
  }

  private long solveProblem(List<String> lines, int colStart, int colEnd) {
    char op = extractOperator(lines, colStart, colEnd);
    List<Long> numbers = extractNumbers(lines, colStart, colEnd);
    return applyOperation(op, numbers);
  }

  private char extractOperator(List<String> lines, int colStart, int colEnd) {
    String opSlice = lines.getLast().substring(colStart, colEnd + 1);
    for (char ch : opSlice.toCharArray()) {
      if (ch == '+' || ch == '*') return ch;
    }
    throw new IllegalStateException("No operator found in block [" + colStart + "," + colEnd + "]");
  }

  private List<Long> extractNumbers(List<String> lines, int colStart, int colEnd) {
    List<Long> numbers = new ArrayList<>();
    for (int r = 0; r < lines.size() - 1; r++) {
      String trimmed = lines.get(r).substring(colStart, colEnd + 1).trim();
      if (!trimmed.isEmpty()) numbers.add(Long.parseLong(trimmed));
    }
    if (numbers.isEmpty()) {
      throw new IllegalStateException(
          "No numbers found in block [" + colStart + "," + colEnd + "]");
    }
    return numbers;
  }

  private long applyOperation(char op, List<Long> numbers) {
    long result = (op == '+') ? 0L : 1L;
    for (long n : numbers) {
      result = (op == '+') ? result + n : result * n;
    }
    return result;
  }

  public long solveWorksheetRightToLeft(List<String> lines) {
    if (lines.isEmpty()) return 0L;

    validateLines(lines);

    List<int[]> blocks = findProblemBlocks(lines);
    return calculateTotal(lines, blocks, this::solveProblemColumns);
  }

  private long solveProblemColumns(List<String> lines, int colStart, int colEnd) {
    char op = extractOperator(lines, colStart, colEnd);
    List<Long> numbers = extractNumbersByColumns(lines, colStart, colEnd);
    return applyOperation(op, numbers);
  }

  private List<Long> extractNumbersByColumns(List<String> lines, int colStart, int colEnd) {
    List<Long> numbers = new ArrayList<>();
    for (int c = colEnd; c >= colStart; c--) {
      StringBuilder digits = new StringBuilder();
      for (int r = 0; r < lines.size() - 1; r++) {
        char ch = lines.get(r).charAt(c);
        if (Character.isDigit(ch)) digits.append(ch);
      }
      if (!digits.isEmpty()) numbers.add(Long.parseLong(digits.toString()));
    }
    if (numbers.isEmpty()) {
      throw new IllegalStateException(
          "No numbers (columns) found in block [" + colStart + "," + colEnd + "]");
    }
    return numbers;
  }

  @FunctionalInterface
  private interface Solver {
    long solve(List<String> lines, int colStart, int colEnd);
  }
}
