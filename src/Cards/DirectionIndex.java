package Cards;

public enum DirectionIndex {
  FORWARD(1),
  BACKWARD(-1);
  public final int index;

  DirectionIndex(int index) {
    this.index = index;
  }

  public DirectionIndex toggle() {
    if (this.equals(FORWARD)) {
      return BACKWARD;
    } else {
      return FORWARD;
    }
  }
}
