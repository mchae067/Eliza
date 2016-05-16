import java.util.Arrays;

public class ElizaTests {

	public static void testChooseString() {

		//is one of the 3 strings chosen?
		String [] strList = {"The", "happy", "cat"};
		String choice = Eliza.chooseString( strList);
		if ( choice != null && (choice.equals("The") || choice.equals("happy") 
				|| choice.equals("cat"))) {
			System.out.println("testChooseString 1 passed.");
		} else {
			System.out.println("testChooseString 1 failed.");
		}

		//if I call it 100 times, are the choices approximately random?
		int countThe = 0;
		int countHappy = 0;
		int countCat = 0;
		for ( int i = 1; i <= 100; i++) {
			choice = Eliza.chooseString( strList);
			if ( choice != null) {
				if ( choice.equals("The")) {
					countThe++;
				} else if ( choice.equals("happy")) {
					countHappy++;
				} else if ( choice.equals("cat")) {
					countCat++;
				}
			}
		}
		if ( countThe >=20 && countThe <= 45 
				&& countHappy >= 20 && countHappy <= 45
				&& countCat >= 20 && countCat <= 45) {
			System.out.println("testChooseString 2 passed. " + countThe
					+ "," + countHappy + "," + countCat);
		} else {
			System.out.println("testChooseString 2 failed. " + countThe
					+ "," + countHappy + "," + countCat);
		}

		//additional test suggestions: 
		//What should happen when an array with a single string is provided? 
		//What should happen when null is passed to chooseString?
	}

	/**
	 *Tests for words in array quitWords
	 *Test1: tests for "bye" at index 0
	 *Test2: tests for a word not in the list
	 *Test3: tests for a part of a word in the list, but not itself in the list
	 *Test4: tests that only the "bye" at index 0 is returned
	 */
	public static void testInList() {
		String [] quitWords = {"bye","goodbye","quit", "bye"};		
		int index = Eliza.inList( "bye", quitWords);
		if ( index >= 0) {
			System.out.println("testInList 1 passed.");
		} else {
			System.out.println("testInList 1 failed.");
		}

		index = Eliza.inList( "seeya", quitWords);
		if ( index == -1) {
			System.out.println("testInList 2 passed.");
		} else {
			System.out.println("testInList 2 failed.");
		}

		//test suggestion: 
		//what should happen if "good" is looked for within the quitWords
		//above?
		index = Eliza.inList("good", quitWords);
		if ( index == -1) {
			System.out.println("testInList 3 passed.");
		} else {
			System.out.println("testInList 3 failed.");
		}

		//Which index is returned if a word is 
		//listed more than once in the list?
		//Should return 0.
		index = Eliza.inList( "bye", quitWords);
		if ( index != 0) {
			System.out.println("testInList 4 failed.");
		} else {
			System.out.println("testInList 4 passed.");
		}
		//can you think of other tests?
	}

	/**
	 *Tests for assembly of words from an array
	 *Test1: Test for "This is a sentence"
	 *Test2: Null test
	 *Test3: Null element
	 */
	public static void testAssemblePhrase() {
		String [] words = {"This", "is a", "sentence"};
		String sentence = Eliza.assemblePhrase( words);

		String expectedSentence = "This is a sentence";
		if ( sentence.equals( expectedSentence)) {
			System.out.println("testAssembleSentence 1 passed.");
		} else {
			System.out.println("testAssembleSentence 1 failed '"
					+ sentence + "'");
		}

		//suggested test: what should happen when null is passed in?
		String[] words2 = {"This", "is", null};
		sentence = Eliza.assemblePhrase(words2);
		expectedSentence = "This is";
		if ( sentence.equals( expectedSentence)) {
			System.out.println("testAssembleSentence 2 passed.");
		} else {
			System.out.println("testAssembleSentence 2 failed '"	
					+ sentence + "'");
		}

		String[] words3 = {"This", null, "is"};
		sentence = Eliza.assemblePhrase(words3);
		expectedSentence = "This is";
		if ( sentence.equals( expectedSentence)) {
			System.out.println("testAssembleSentence 3 passed.");
		} else {
			System.out.println("testAssembleSentence 3 failed '" 
					+ sentence + "'");
		}
	}

	/**
	 *Tests for longest sentence in the array
	 *Test1: Test for "This is the longest one"
	 *Test2: Null test
	 *Test3: Duplicate sentences
	 */
	public static void testFindLongest() {
		String [] sentences = {"short", "This is longer.", 
				"This is the longest one.", "s"};
		String longest = Eliza.findLongest( sentences);
		if ( longest == sentences[2]) {
			System.out.println("testFindLongest 1 passed.");
		} else {
			System.out.println("testFindLongest 1 failed.");
		}

		//what additional tests can you create?
		//null test
		String [] sentences2 = null;
		longest = Eliza.findLongest( sentences2);
		if ( longest == null) {
			System.out.println("testFindLongest 2 passed.");
		} else {
			System.out.println("testFindLongest 2 failed.");
		}

		//test for duplicate sentences
		String [] sentences3 = {"short", "This is longer.", 
				"This is the longest one.", "This is not longest one."};
		longest = Eliza.findLongest( sentences3);
		if ( longest == sentences[2]) {
			System.out.println("testFindLongest 3 passed.");
		} else {
			System.out.println("testFindLongest 3 failed.");
		}
	}

	/**
	 *Tests for number of occurrences of substring in string
	 *Test1: Test for number of spaces
	 *Test2: Test for "<2>"
	 *Test3: Test for empty String
	 *Test4: Null test
	 */
	public static void testHowMany() {
		int countSpaces = Eliza.howMany( " ", " you me ");
		if ( countSpaces == 3) {
			System.out.println( "testHowMany 1 passed.");
		} else {
			System.out.println( "testHowMany 1 failed.  countSpaces == " 
					+ countSpaces);
		}

		int countNum = Eliza.howMany("<2>", "<2> or Hillary? <2> or Drumpf?");
		if ( countNum == 2) {
			System.out.println( "testHowMany 2 passed.");
		} else {
			System.out.println( "testHowMany 2 failed.  countNum == " 
					+ countNum);
		}

		//additional tests
		//empty string
		countSpaces = Eliza.howMany( "", " you me ");
		if ( countSpaces == 0) {
			System.out.println( "testHowMany 3 passed.");
		} else {
			System.out.println( "testHowMany 3 failed.  countSpaces == " 
					+ countSpaces);
		}
		
		//null string
		countSpaces = Eliza.howMany( null, " you me ");
		if ( countSpaces == -1) {
			System.out.println( "testHowMany 4 passed.");
		} else {
			System.out.println( "testHowMany 4 failed.  countSpaces == " 
					+ countSpaces);
		}

	}		

	/**
	 *Tests if a proper char array is formed from userInput
	 *Test1: Test for correct array size
	 *Test2: Test for correct filtering
	 *Test3: Null test
	 */
	public static void testFilterChars() {
		String userInput = "w? #t   i't e   4t m-s!";
		char [] expectedChars = {'w','!',' ',' ','t',' ',' ',' ','i','\'','t',
				' ','e',' ',' ',' ','4','t',' ','m',' ','s','!'};
		char [] characters = Eliza.filterChars( userInput);
		if ( userInput.length() == characters.length) {
			System.out.println("testFilterChars 1 passed.");
		} else {
			System.out.println("testFilterChars 1 failed.");
		}
		boolean error = false;
		for ( int i = 0; i < expectedChars.length; i++) {
			if ( expectedChars[i] != characters[i]) {
				System.out.print( String.format("characters[%d]"
						+ " '%c' expected '%c'\n", i, 
						characters[i], expectedChars[i]));
				error = true;
			}
		}
		if ( error) {
			System.out.println("testFilterChars 2 failed.");
		} else {
			System.out.println("testFilterChars 2 passed.");
		}

		//additional tests
		//test for null
		userInput = null;
		char[] nullArray = Eliza.filterChars(userInput);
		if (nullArray==null)
		{
			System.out.println("testFilterChars 3 passed.");
		} else {
			System.out.println("testFilterChars 3 failed.");
		}
	}

	/**
	 *Tests for removal of duplicate spaces
	 *Test1: Test if duplicate spaces are properly removed
	 *Test2: Test for three spaces in a row
	 */
	public static void testRemoveDuplicateSpaces() {
		char [] chars1 = 
			{'a', 't', ' ', '.', ' ', ' ', 'g', ' ', ' ', 'h', ' '};
		Eliza.removeDuplicateSpaces( chars1);
		char [] expectedResult = {'a', 't', ' ', '.', ' ', 'g', ' ', 'h', ' ',
				'\u0000', '\u0000'};

		boolean error = false;
		String errorStr = "";
		for ( int i = 0; i < expectedResult.length; i++) {
			if ( chars1[i] != expectedResult[i]) {
				errorStr += String.format("chars1[%d] '%c' expected '%c'\n",
						i, chars1[i], expectedResult[i]);
				error = true;
			}
		}
		if ( error) {
			System.out.println("testRemoveDuplicateSpaces 1 failed. " 
					+ errorStr);
		} else {
			System.out.println("testRemoveDuplicateSpaces 1 passed.");
		}

		//additional tests
		//test for 3 spaces in a row
		char[] chars2 = {'a','t',' ',' ',' ','z','y','z'};
		Eliza.removeDuplicateSpaces(chars2);
		char [] expectedResult2 = {'a', 't', ' ', 'z', 'y', 'z',
				'\u0000','\u0000'};
		String errorStr2 = "";
		error = false;
		for ( int i = 0; i < expectedResult2.length; i++) {
			if ( chars2[i] != expectedResult2[i]) {
				errorStr2 += String.format("chars2[%d] '%c' expected '%c'\n",
						i, chars2[i], expectedResult2[i]);
				error = true;
			}
		}
		if ( error) {
			System.out.println("testRemoveDuplicateSpaces 2 failed. "
					+ errorStr2);
		} else {
			System.out.println("testRemoveDuplicateSpaces 2 passed.");
		}
	}

	/**
	 *Tests if a word is found from a wordList properly
	 *Test1: Test for true
	 *Test2: Test for false
	 *Test3: Null test
	 */
	public static void testFindAnyWords() {
		String[] someWords = {"Going", "now", "goodbye"};
		boolean found = Eliza.findAnyWords( someWords, Config.QUIT_WORDS);
		if ( found) {
			System.out.println("testFindAnyWords 1 passed.");
		} else {
			System.out.println("testFindAnyWords 1 failed.");
		}

		String[] someMoreWords = {"Hello", "how", "are", "you"};
		found = Eliza.findAnyWords( someMoreWords, Config.QUIT_WORDS);
		if ( !found) {
			System.out.println("testFindAnyWords 2 passed.");
		} else {
			System.out.println("testFindAnyWords 2 failed.");
		}

		//additional tests
		//null test
		String[] someEvenMoreWords = {null, null, null, null};
		found = Eliza.findAnyWords( someEvenMoreWords, Config.QUIT_WORDS);
		if ( !found) {
			System.out.println("testFindAnyWords 3 passed.");
		} else {
			System.out.println("testFindAnyWords 3 failed.");
		}
	}

	/**
	 *Tests if initial processing is executed correctly
	 *Test1: Test for proper initial processing
	 *Test2: Null test
	 */
	public static void testInitialProcessing() {
		String sentence = Eliza.initialProcessing("What? This isn't the "
				+ "    4th messy-sentence!");
		if ( sentence != null 
				&& sentence.equals( "this isn't the 4th messy sentence")){
			System.out.println("testInitialProcessing 1 passed.");
		} else {
			System.out.println("testInitialProcessing 1 failed:" + sentence);
		}

		//additional tests
		//null test
		String sentence2 = Eliza.initialProcessing(null);
		if ( sentence2 == null )
		{
			System.out.println("testInitialProcessing 2 passed.");
		} 
		else 
		{
			System.out.println("testInitialProcessing 2 failed:" + sentence);
		}
	}

	/**
	 *Tests words in array that are found in beforeList are replaced
	 *by the afterList
	 *Test1: Test for proper replacements
	 *Test2: Null test
	 *Test3: Test for prepareUserPhrase
	 */
	public static void testReplacePairs() {
		String [] someWords = {"i'm", "cant", "recollect"};
		String [] beforeList = {"dont", "cant", "wont", "recollect", "i'm"};
		String [] afterList = {"don't", "can't", "won't", "remember", "i am"};
		String [] result = Eliza.replacePairs( someWords,
					beforeList, afterList);
		if ( result != null && result[0].equals("i") && result[1].equals("am") 
				&& result[2].equals("can't") && result[3].equals("remember")) {
			System.out.println("testReplacePairs 1 passed.");
		} else {
			System.out.println("testReplacePairs 1 failed.");
		}

		//additional tests
		//null test
		String [] someWords2 = {"i'm", "cant", "recollect"};
		String [] beforeList2 = {"dont", "cant", "wont", "recollect", "i'm"};
		String [] afterList2 = null;
		String [] result2 = Eliza.replacePairs( someWords2,
					beforeList2, afterList2);
		if ( result2 == null ) 
		{
			System.out.println("testReplacePairs 2 passed.");
		} else {
			System.out.println("testReplacePairs 2 failed.");
		}
		
		//test for prepareUserPhrase
		String [] someWords3 = {"i'm", "happy"};
		String [] beforeList3 = Config.POST_PROCESS_BEFORE;
		String [] afterList3 = Config.POST_PROCESS_AFTER;
		String [] result3 = Eliza.replacePairs( someWords3,
					beforeList3, afterList3);
		if ( result3 != null && result3[0].equals("you") && 
					result3[1].equals("are") 
				&& result3[2].equals("happy")) {
			System.out.println("testReplacePairs 3 passed.");
		} else {
			System.out.println("testReplacePairs 3 failed.");
		}
	}

	/**
	 *Tests if words between pattern words are returned
	 *Test1: Test if pattern word is caught at end
	 *Test2: Test if pattern word is caught at beginning
	 *Test3: Test for multiple pattern words
	 *Test4: Test for incorrect pattern order
	 */
	public static void testFindPatternInSentence() {
		String [] pattern1 = { "computer"};
		String [] sentence1 = {"are", "you", "a", "computer"};

		String [] matches = Eliza.findPatternInSentence( pattern1, sentence1);
		if ( matches != null && matches.length == 2 
				&& matches[0].equals( "are you a") && matches[1].equals("")) {
			System.out.println("testFindPatternInSentence 1 passed.");
		} else {
			System.out.println("testFindPatternInSentence 1 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern2 = { "computer"};
		String [] sentence2 = {"computer", "are", "you"};

		matches = Eliza.findPatternInSentence( pattern2, sentence2);
		if ( matches != null && matches.length == 2 && matches[0].equals("") 
				&& matches[1].equals( "are you")) {
			System.out.println("testFindPatternInSentence 2 passed.");
		} else {
			System.out.println("testFindPatternInSentence 2 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern5 = { "computer"};
		String [] sentence5 = {"does", "that", "computer", "on", "your", 
				"desk", "work"};
		matches = Eliza.findPatternInSentence( pattern5, sentence5);
		if ( matches != null && matches.length == 2 
				&& matches[0].equals( "does that") 
				&& matches[1].equals( "on your desk work")) {
			System.out.println("testFindPatternInSentence 3 passed.");
		} else {
			System.out.println("testFindPatternInSentence 3 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern6 = {"you", "me"};
		String [] sentence6 = {"why", "don't", "you", "like",  "me"};
		matches = Eliza.findPatternInSentence( pattern6, sentence6);
		if ( matches != null && matches.length == 3 
				&& matches[0].equals( "why don't") 
				&& matches[1].equals( "like") && matches[2].equals("")) {
			System.out.println("testFindPatternInSentence 4 passed.");
		} else {
			System.out.println("testFindPatternInSentence 4 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern7 = {"you", "me"};
		String [] sentence7 = {"me", "don't", "like", "you"};
		matches = Eliza.findPatternInSentence( pattern7, sentence7);
		if ( matches == null) {
			System.out.println("testFindPatternInSentence 5 passed.");
		} else {
			System.out.println("testFindPatternInSentence 5 failed.");
			System.out.println( Arrays.toString(matches));
		}

		//additional tests
	}

	/**
	 *Tests if correct words are replaced in phrase
	 *Test1: Test for proper word replacement
	 *Test2: Null test
	 */
	public static void testPrepareUserPhrase()  {
		String someWords = "i'm happy";
		String result = Eliza.prepareUserPhrase( someWords);
		if ( result != null && result.equals("you are happy")) {
			System.out.println("testPrepareUserPhrase 1 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 1 failed. '" 
					+ result + "'");
		}

		//additional tests
		//null test
		String someWords2 = null;
		String result2 = Eliza.prepareUserPhrase( someWords2);
		if ( result2== null) {
			System.out.println("testPrepareUserPhrase 1 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 1 failed. '"
					+ result2 + "'");
		}
		
	}

	/**
	 *Tests if correct words are replaced in phrase
	 *Test1: Test that proper response is formed from user input
	 */
	public static void testPrepareResponse() {
		String draftResponse = "Really, <3>?";
		String []userPhrases = {"", "", "about my dog"};
		String response = Eliza.prepareResponse( draftResponse, userPhrases);

		String expectedResponse = "Really, about your dog?";

		if ( response.equals( expectedResponse)) {
			System.out.println("testPrepareResponse 1 passed.");
		} else {
			System.out.println("testPrepareResponse 1 failed. '"
					+ response + "'");
		}
	}

	/**
	 *Tests if correct words are replaced in phrase
	 *Test1: Test that response is matched to input
	 *Test2: Test that response is matched to input
	 */
	public static void testMatchResponse() {
		String []words1 = {"are", "you", "a", "computer"};
		String response1 = Eliza.matchResponse( words1);
		if ( Eliza.inList( response1, Config.RESPONSE_TABLE[0][1]) >= 0) {
			System.out.println("testMatchResponse 1 passed.");
		} else {
			System.out.println("testMatchResponse 1 failed. "+response1);
		}

		String []words2 = {"you", "are", "like", "my", "father"};
		String response2 = Eliza.matchResponse( words2);
		if ( response2 != null && response2.contains( "like your father")) {
			System.out.println("testMatchResponse 2 passed.");
		} else {
			System.out.println("testMatchResponse 2 failed. "+response2);
		}
	}

	private static void testProblem(String problem) {
		//supporting method for testProcessInput
		System.out.println("Patient:  " + problem);
		System.out.println("Eliza: " + Eliza.processInput( problem));
	}

	public static void testProcessInput() {
		//note: the responses may vary as they are randomly selected and the 
		//random generator results will vary based on the previous times it 
		//has been called. Therefore, see if each response is appropriate.

		//updated 2/17
		testProblem("I like computers.");
		testProblem("What is your name?");
		testProblem("You remind me of a grumpy uncle.");
		testProblem("You don't seem to say much.");
		testProblem("You reflect me.");
		testProblem("I wish to believe you.");
		testProblem("I dreamed of going into space.");
		testProblem("I apologize for boring you.");
		testProblem("Because it seems the polite thing to do.");
		testProblem("Yes.");
		testProblem("I remember being in 1st grade.");
		testProblem("No, not often. Sometimes I remember being on a boat.");
		testProblem("My family liked to play cards.");
		testProblem("Do you remember growing up?");
		testProblem("Are you a Packers fan?");
		testProblem("I am sad to hear that.");
		testProblem("I cannot explain this.");
		testProblem("You seem to have a different perspective than many.");
		testProblem("I'm talking to my dog.");
		testProblem("goodbye");
	}

	public static void main(String[] args) {
		//feel free to comment out tests that are not of current interest
		//in order to focus on certain tests.  Eventually, all the tests
		//should run successfully.

		testChooseString();

		//		testInList();
		//		testAssemblePhrase();
		//		testFindLongest();
		//		testHowMany();
		//
		//		testFilterChars();
		//		testRemoveDuplicateSpaces();
		//		testFindAnyWords();
		//		testInitialProcessing();
		//
		//		testReplacePairs();
		//		testFindPatternInSentence();
		//		testPrepareUserPhrase();
		//		testPrepareResponse();
		//
		//		testMatchResponse();
		//		testProcessInput();

	}
}
