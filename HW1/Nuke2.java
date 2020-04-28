import java.io.*;

/** A class that provides a main function to read a string from the keyboard,
*   then print the same string removing the second character.
*/

class Nuke2{
	public static void main(String[] arg) throws Exception {
		BufferedReader keyboard;
		StringBuilder inputLine;
		int inputLength;
		String resultString;

		keyboard = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Please enter a string: ");
		System.out.flush();     /* Make sure the line is printed immediately. */
		inputLine = new StringBuilder(keyboard.readLine());
		inputLength = inputLine.length();

		String leftPart = inputLine.substring(0, 1);
		String rightPart = inputLine.substring(2, inputLength);
		resultString = leftPart.concat(rightPart);

		System.out.println(resultString);

	}

}