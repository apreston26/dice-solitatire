package edu.cnm.deepdive.dicesolitatire.model;

public enum GameState {

  /** Initial state  before the first roll. */
  INITIAL,
  /** State starting with first roll, until maximum scratch count is reached. */
  IN_PROGRESS,
  /** State after maximum scratch count is reached. */
  TERMINAL;

}
