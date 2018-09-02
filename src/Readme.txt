Usage:
Compile: javac TicTacToe.java
Run: java TicTacToe

Assumptions:
1. X always starts
2. Cases that are not possible in a real game situation are discardes as invalid board
   eg. both have a winning row/column, this is not possible as the game cannot continue
   if there already is a winning combination
3. Tie is only possible if all pieces have been put and no winning combination otherwise the game is still on
4. As input only X, O (case sensitive) and empty (whitespace) is accepted
  - '/0' is also considered to be empty in case you have a char[] for testing this solution   

Testing:
Provided is a txt file "test.txt".
In the .txt file I have created a combination of different game situations.
In order to add another test use the format:
XXX
OO 
XO 
Important: If there is neither X or O a whitespace is needed. After one game board has been put add an extra line to mark the beginning of a new game board. See test.txt for reference.

My main function reads from the txt file and converts to char[] that are handled in the requested function int solution(char board[]).

