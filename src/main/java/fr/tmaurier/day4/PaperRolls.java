package fr.tmaurier.day4;

import java.util.ArrayList;
import java.util.List;

public class PaperRolls {

  public int countAccessibleRolls(List<String> grid) {
    if (grid.isEmpty()) return 0;

    int rows = grid.size();
    int cols = grid.getFirst().length();
    int accessible = 0;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid.get(r).charAt(c) == '@' && countAdjacentRolls(grid, r, c) < 4) {
          accessible++;
        }
      }
    }
    return accessible;
  }

  private int countAdjacentRolls(List<String> grid, int r, int c) {
    int rows = grid.size();
    int cols = grid.getFirst().length();
    int count = 0;

    for (int dr = -1; dr <= 1; dr++) {
      for (int dc = -1; dc <= 1; dc++) {
        if (dr == 0 && dc == 0) continue;

        int nr = r + dr;
        int nc = c + dc;
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid.get(nr).charAt(nc) == '@') {
          count++;
        }
      }
    }
    return count;
  }

  public int countRemovableRolls(List<String> lines) {
    if (lines.isEmpty()) return 0;

    int rows = lines.size();
    int cols = lines.getFirst().length();
    char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
    int totalRemoved = 0;

    while (true) {
      List<int[]> toRemove = new ArrayList<>();

      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          if (grid[r][c] == '@' && countAdjacentRolls(grid, r, c) < 4) {
            toRemove.add(new int[] {r, c});
          }
        }
      }

      if (toRemove.isEmpty()) break;

      toRemove.forEach(pos -> grid[pos[0]][pos[1]] = '.');
      totalRemoved += toRemove.size();
    }
    return totalRemoved;
  }

  private int countAdjacentRolls(char[][] grid, int r, int c) {
    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int dr = -1; dr <= 1; dr++) {
      for (int dc = -1; dc <= 1; dc++) {
        if (dr == 0 && dc == 0) continue;

        int nr = r + dr;
        int nc = c + dc;
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '@') {
          count++;
        }
      }
    }
    return count;
  }
}
