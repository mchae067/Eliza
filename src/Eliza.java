import java.util.Arrays;
import java.util.Scanner;

//TODO class header

public class Eliza {

	/**
	 * This method randomly picks one of the strings from the list. If list 
	 * is null or has 0 elements then null is returned.
	 * 
	 * @param list An array of strings to choose from.
	 * @return One of the strings from the list.
	 */		
	public static String chooseString( String [] list) 
	{
		if ( list == null || list.length == 0)
		{
			return null;
		}
		int index = Config.RNG.nextInt(list.length);
		return list[index]; 
	}

	/**
	 * If the word is found in the wordList then the index of the word
	 * is returned, otherwise -1 is returned. If there are multiple matches
	 * the index of the first matching word is returned. If either word or 
	 * wordList is null then -1 is returned. (Update 2/17) A zero length
	 * string should not be treated differently then non-zero length strings.
	 * A null value within wordList should be ignored.  
	 * 
	 * @param word  A word to search for in the list.
	 * @param wordList  The list of Strings in which to search.
	 * @return The index of list where word is found, or -1 if not found.
	 */
	public static int inList(String word, String []wordList) 
	{
		if(wordList==null || word==null) 
		{
			return -1;
		}
		for(int i=0;i<wordList.length;i++)
		{
			if(wordList[i]==null) 
			{

			}
			if(word.equals(wordList[i]))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Combines the Strings in list together with a space between each
	 * and returns the resulting string. If list is null then null is
	 * returned. (Update 2/17) If the list has 0 elements then return a
	 * string with length 0 (""). If any elements within the list are null
	 * or zero length strings then ignore them since we don't want more than
	 * one space in a row.
	 * 
	 * @param list An array of words to be combined.
	 * @return A string containing all the words with a space between each.
	 */
	public static String assemblePhrase( String[] list) {
		String out = "";
		if (list==null) 
		{
			out = null;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<list.length;i++) 
			{	
				if(list[i]!=null)
				{
					if(i!=0)
					{
						builder.append(" ");
					}
					builder.append(list[i]);
				}	
			}
			out = builder.toString();
		}
		return out;
	}

	/**
	 * Returns the longest sentence, based on the String length, from 
	 * the array of sentences. Removes spaces from the beginning and
	 * end of each sentence before comparing lengths. If sentences is null
	 * or has a length of 0 then null is returned. (Update 2/16) In the 
	 * case of equal length longest strings, return the string that has
	 * the lowest array index.
	 * 
	 * Note: String trim method may be useful.
	 * 
	 * @param sentences The array of sentences passed in.
	 * @return The longest sentence without spaces at the beginning or end.
	 */
	public static String findLongest( String [] sentences) {
		String out = null;
		if (sentences!=null && sentences.length!=0) 
		{
			out = sentences[0];
			for(int i=0;i<sentences.length;i++)
			{	
				int count = 0;
				if (sentences[i]!=null) 
				{
					sentences[i].trim();
					for (int j=0;j<sentences[i].length();j++)
					{
						count++;
					}
					if (count>out.length()) 
					{
						out = sentences[i];
					}
				}
			}
		}
		return out;
	}

	/**
	 * Counts the number of times the substring is in the str. Does not 
	 * count overlapping substrings separately. If either parameter 
	 * substring or str is null then -1 is returned.  (Update 2/17) 
	 * If the substring is the empty string ("") then return the length 
	 * of the string.
	 * 
	 * Note: String methods indexOf may be useful for finding whether
	 *       substring occurs within str. One of the indexOf methods
	 *       has a parameter that says where to start looking in the str.
	 *       This might be useful to find the substring again in str, 
	 *       once you have found a substring once.
	 * 
	 * @param str The string to be searched.
	 * @param substring The string to be searched for within str.
	 * @return The number of times substring is found within str or -1 if
	 *         either parameter is null parameter.
	 */
	public static int howMany( String substring, String str) {
		int out = -1;
		if(str!=null && substring!=null && str.indexOf(substring)!=-1)
		{
			if (substring=="")
			{
				return 0;
			}
			int count = 0;
			if (str.indexOf(substring) == 0 && substring.length()>1)
			{
				count++;
			}
			for(int i=0;i<str.length();i++)
			{
				if (str.indexOf(substring, i)!=-1)
				{
					i = str.indexOf(substring, i+substring.length()-1);
					count++;
				}
			}
			out = count;
		}
		return out;
	}

	/**
	 * This method performs the following processing to the userInput.
	 * - substitute spaces for all characters but (alphabetic characters, 
	 *   numbers, and ' , ! ? .).
	 * - Change (,!?.) to (!). Parenthesis not included.
	 * (Update 2/17) If the userInput is null then return null otherwise 
	 * the array returned should be the same length as the userInput.
	 * 
	 * @param userInput The characters that the user typed in.
	 * @return The character array containing the valid characters.
	 */
	public static char [] filterChars( String userInput) {
		if (userInput!=null)
		{
			char[] out = new char[userInput.length()];
			for(int i=0;i<userInput.length();i++)
			{
				if (Character.isAlphabetic(userInput.charAt(i)) ||
						Character.isDigit(userInput.charAt(i)) ||
						userInput.charAt(i)=='\'') 
				{
					out[i] = userInput.charAt(i);
				}
				else if (userInput.charAt(i)=='!' || userInput.charAt(i)=='?' ||
						userInput.charAt(i)==',' || userInput.charAt(i)=='.')
				{
					out[i] = '!';
				}
				else 
				{
					out[i] = ' ';
				}
			}
			return out;
		}
		return null;
	}

	/**
	 * Reduces all sequences of 2 or more spaces to 1 space within the 
	 * characters array. If any spaces are removed then the same number
	 * of Null character '\u0000' will fill the elements at the end of the
	 * array.
	 * 
	 * @param characters The series of characters that may have more than one
	 *     space in a row when calling. On return the array of characters will
	 *     not have more than one space in a row and there may be '\u0000'
	 *     characters at the end of the array.
	 */
	public static void removeDuplicateSpaces( char [] characters) {
		char[] temp = new char[characters.length];
		boolean previousIsSpace = false;
		int count = 0;
		int j=0;
		for (int i=0;i<characters.length;i++)
		{
			if (previousIsSpace && characters[i]==' ')
			{
				count++;
			}
			else
			{
				temp[j] = characters[i];
				j++;
			}
			if (characters[i]==' ')
			{
				previousIsSpace = true;
			}
			if (characters[i]!=' ')
			{
				previousIsSpace = false;
			}
		}
		for(int l=0;l<count;l++)
		{
			temp[j]='\u0000';
			j++;
		}
		for(int k=0;k<characters.length;k++)
		{
			characters[k]=temp[k];
		}
	}

	/**
	 * Looks for each word in words within the wordList. 
	 * If any of the words are found then true is returned, otherwise false.
	 * 
	 * @param words List of words to look for.
	 * @param wordList List of words to look through.
	 * @return Whether one of the words was found in wordList.
	 */
	public static boolean findAnyWords(String[] words, String []wordList ) {
		for (int i=0;i<wordList.length;i++)
		{
			for (int j=0;j<words.length;j++)
			{
				if (words[j]==wordList[i])
				{
					return true;
				}
			}
		}
		return false;
	}	

	/**
	 * This method performs the following processing to the userInput and 
	 * returns the longest resulting sentence.
	 * 1) Converts all characters to lower case  
	 * 		See String toLowerCase() method.
	 * 2) Removes any extra spaces at the beginning or end of the input
	 * 		See String trim() method.
	 * 3) Substitute spaces for all characters but alphabetic characters, 
	 *    numbers, and ',.!? and then substitute ! for ,.?
	 *      See filterChars method.
	 * 4) Reduce all sequences of 2 or more spaces to be one space.
	 *      See removeDuplicateSpaces method.
	 * 5) Divide input into separate sentences based on !
	 *      Construct a String from a character array with 
	 *      	String str = new String( arrayName);
	 *      See String split method.
	 *      Example: String[] sentences = str.split("!");
	 * 6) Determine which sentence is longest in terms of characters and
	 *    return it. 
	 *      See findLongest method.
	 *     
	 * (Update 2/17) If the userInput is null then null should be returned.
	 * 
	 * @param userInput The characters that the user typed in.
	 * @return The longest sentence from the input.
	 */
	public static String initialProcessing( String userInput) {
		if (userInput == null)
		{
			return null;
		}
		String out = "";
		out = userInput.toLowerCase();
		out = out.trim();
		char[] outChar = Eliza.filterChars(out);
		Eliza.removeDuplicateSpaces(outChar);
		String str = new String(outChar);
		String[] sentences = str.split("!");
		out = Eliza.findLongest(sentences);
		out = out.trim();
		return out;
	}

	/**
	 * This method creates a new words list replacing any words it finds in
	 * the beforeList with words in the afterList. An array of the resulting
	 * words is returned.  
	 * 
	 * (Update 2/17) If any parameter is null, return null.
	 * 
	 * @param words List of words to look through.
	 * @param beforeList List of words to look for.
	 * @param afterList List of words to replace, if the corresponding
	 *  word in the before list is found in the words array.
	 * @return The new list of words with replacements.
	 */
	public static String[] replacePairs(String []words, String [] beforeList, 
			String [] afterList) {
		if (words==null || beforeList==null || afterList == null)
		{
			return null;
		}
		String[] out = words;
		for(int i=0;i<words.length;i++)
		{
			for(int j=0;j<beforeList.length;j++) 
			{
				if(words[i].equals(beforeList[j])) 
				{
					out[i] = afterList[j];
				}
			}
		}
		String temp = Eliza.assemblePhrase(out);
		out = temp.split(" ");
		return out;
	}

	/**
	 * Checks to see if the pattern matches the sentence. In other words, 
	 * checks to see that all the words in the pattern are in the sentence 
	 * in the same order as the pattern. If the pattern matches then this
	 * method returns the phrases before, between and after the 
	 * pattern words. If the pattern does not match then null is returned.
	 * (Update 2/17) If any parameter is null, return null.
	 * 
	 * @param pattern The words to match, in order, in the sentence.
	 * @param sentence Each word in the sentence.
	 * @return The phrases before, between and after the pattern words
	 * 		or null if the pattern does not match the sentence.
	 */	
	public static String [] findPatternInSentence( String [] pattern, 
			String [] sentence) {
		int currIndex=0;
		for (int p=0;p<pattern.length;p++)
		{
			int inListIndex = Eliza.inList(pattern[p], sentence);
			if (inListIndex<currIndex)
			{
				return null;
			}
			currIndex = inListIndex;
		}

		String[] userPhrases = new String[pattern.length+1];
		for(int i=0;i<userPhrases.length;i++)
		{
			userPhrases[i] = "";
		}
		int patternIndex = 0;
		int phraseIndex = 0;
		for(int j=0;j<sentence.length;j++)
		{
			if(sentence[j]==pattern[patternIndex])
			{
				if(patternIndex+1<pattern.length)
				{
					patternIndex++;
				}
				phraseIndex++;
			}
			else 
			{
				userPhrases[phraseIndex]=userPhrases[phraseIndex]
						+" "+sentence[j];
				userPhrases[phraseIndex]=userPhrases[phraseIndex].trim();
			}
		}
		return userPhrases;
	}

	/**
	 * Replaces words in the phrase if they are in the Config.
	 * POST_PROCESS_BEFORE
	 * with the corresponding words from Config.POST_PROCESS_AFTER.
	 * (Update 2/17) If the parameter is null then return null.
	 * 
	 * @param phrase One or more words separated by spaces.
	 * @return A string composed of the words from phrase with replacements.
	 */
	public static String prepareUserPhrase( String phrase) {
		if (phrase==null)
		{
			return null;
		}
		String [] temp = Eliza.replacePairs(phrase.split(" "),
				Config.POST_PROCESS_BEFORE, Config.POST_PROCESS_AFTER);
		String out = Eliza.assemblePhrase(temp);
		return out;
	}

	/**
	 * Prepares a response based on the draftResponse. If draftResponse
	 * contains <1>, <2>, <3> or <4> then the corresponding userPhrase
	 * is substituted for the <1>, <2>, etc.  The userPhrase however must
	 * be prepared by exchanging words that are in Config.POST_PROCESS_BEFORE
	 * with the corresponding words from Config.POST_PROCESS_AFTER.
	 * (Update 2/17) If draftResponse is null, then return null. If 
	 * userPhrases is null then return draftResponse.
	 * 
	 * @param draftResponse A response sentence potentially containing <1>, 
	 *             <2> etc.
	 * @param userPhrases An array of phrases from the user input.
	 * @return A string composed of the words from sentence with replacements.
	 */
	public static String prepareResponse( String draftResponse,	
				String []userPhrases) {
		if (draftResponse==null)
		{
			return null;
		}
		if (userPhrases==null)
		{
			return draftResponse;
		}

		String out = draftResponse;
		if (draftResponse.contains("<1>"))
		{
			out = out.replaceFirst("<1>", 
					Eliza.prepareUserPhrase(userPhrases[0]));
		}
		if (draftResponse.contains("<2>"))
		{
			out = out.replaceFirst("<2>", 
					Eliza.prepareUserPhrase(userPhrases[1]));
		}
		if (draftResponse.contains("<3>"))
		{
			out = out.replaceFirst("<3>", 
					Eliza.prepareUserPhrase(userPhrases[2]));
		}
		if (draftResponse.contains("<4>"))
		{
			out = out.replaceFirst("<4>", 
					Eliza.prepareUserPhrase(userPhrases[3]));
		}
		return out;
	}

	/**
	 * Looks through Config.RESPONSE_TABLE to find the first pattern 
	 * that matches the words. When a pattern is matched then a response 
	 * is randomly chosen from the corresponding list of responses.
	 * If the response has a parameter denoted with <1>, <2> 
	 * etc. the parameter is replaced with the 0th, 1st, etc String
	 * from the user phrases returned by the findPatternInSentence method.
	 * (Update 2/17) If words parameter is null then choose a response 
	 * from NO_MATCH_RESPONSES and return.
	 * 
	 * @param words The words of a sentence.
	 * @return The completed response ready to be shown to the user.
	 */
	//ERROR: Searches through array backwards.
	public static String matchResponse( String [] words) {
		String out = Eliza.chooseString(Config.NO_MATCH_RESPONSES);
		for(int i=0;i<Config.RESPONSE_TABLE.length;i++)
		{
			String[] userPhrases =
					Eliza.findPatternInSentence(Config.RESPONSE_TABLE[i][0],
							words);
			if (userPhrases!=null)
			{
				String Response =
						Eliza.chooseString(Config.RESPONSE_TABLE[i][1]);
				Response = Eliza.prepareResponse(Response, userPhrases);
				out = Response;
			}
		}
		return out;
	}

	/**
	 * Takes the input as a parameter and returns a response. If any of the
	 * QUIT_WORDS are found then null is returned. 
	 * (Update 2/17) If userInput is null then return null;
	 * 
	 * @param userInput The problem sentence(s) the user types in.
	 * @return A response string to be shown to the user or null if a QUIT_WORD
	 *         is found.
	 */
	public static String processInput(String userInput) {
		if (userInput == null)
		{
			return null;
		}
		userInput = Eliza.initialProcessing(userInput);
		String[] input = userInput.split(" ");
		for (int i=0;i<input.length;i++)
		{
			for (int j=0;j<Config.QUIT_WORDS.length;j++)
			{
				if (input[i].equals(Config.QUIT_WORDS[j]))
				{
					return null;
				}
			}	
		}
		Eliza.replacePairs(input, Config.PRE_PROCESS_BEFORE,
				Config.PRE_PROCESS_AFTER);
		String out = Eliza.matchResponse(input);
		return out;
	}

	/**
	 * This method displays an INITIAL_MESSAGE, accepts typed input, calls 
	 * the processInput method, and prints out the response (of processInput)
	 * until the response is null at which point the FINAL_MESSAGE is shown
	 * and the program terminates.
	 */
	public static void interactive() {
		System.out.println("Eliza: "+Config.INITIAL_MESSAGE);
		Scanner in = new Scanner(System.in);
		while(true) 
		{
			String input = in.nextLine();
			if (Eliza.processInput(input) == null)
			{
				System.out.println("Eliza: "+Config.FINAL_MESSAGE);
				in.close();
				break;
			}
			else
			{
				System.out.println("Eliza: "+Eliza.processInput(input));
			}
		}
	}

	/**
	 * Program execution starts here.
	 * @param args unused
	 */  	
	public static void main(String[] args) {

		interactive();
		/*ElizaTests.testInList();
		ElizaTests.testAssemblePhrase();
		ElizaTests.testFindLongest();
		ElizaTests.testHowMany();
		ElizaTests.testFilterChars();
		ElizaTests.testRemoveDuplicateSpaces();
		ElizaTests.testFindAnyWords();
		ElizaTests.testInitialProcessing();
		ElizaTests.testReplacePairs();
		ElizaTests.testFindPatternInSentence();
		ElizaTests.testPrepareUserPhrase();
		ElizaTests.testPrepareResponse();
		ElizaTests.testMatchResponse();
		ElizaTests.testProcessInput();*/
	}
}