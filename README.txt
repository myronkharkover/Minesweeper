===================
=: Core Concepts :=
===================

  1. 2D Arrays
  2d arrays were used to make all the spaces on the board. The simplest way to create a space board where
  each element can be different.

  2. Recursion
  Recursion was utilized for the flip function. This function checked if spaces were
  either previously flipped or mines and if the space was neither it would recursively call flip to
  flip the surounding spaces that were not mines until a mine space was found.

  3. JUnit testable component
  I used JUnit testing to test methods in the GameMechanics class. Tested wins, losses, empty spaces
  not next to bombs, empty spaces next to bombs, spaces that were bombs, and the flip function

  4. Inheritance and subtyping
  Used inheritance to extend JPanel in the GameVisuals class to keep track of the current status of the
   game.

=========================
=: Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Game.java is where the actual game is ran from, where the instructions of the game are written, the reset button,
  and where all of the features get called from. GameVisuals.java is where all of the visual features are centralized,
  it is where the board is drawn, set to a preffered size, the numbers are assigned to spaces, and colors assigned to
  these numbers/mines. Spaces.java is directly responsible for the individual spaces, their x&y coordinates,
  how many mines are adjacent etc. GameMechanics.java is in charge of all of the game mechanics: the flip function,
  the generation of all the mines, and the functions made for testing are all centralized to this class. Lastly,
  MineSweeperTests.java contains all of the JUnit tests.
