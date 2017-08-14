/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class MyExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T13:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "trump", "You are so useful @Youtube", d3);
    private static final Tweet tweet4 = new Tweet(4, "trump", "@AllanRWhyte @maagaggie @AdnanOnMUFC hawkins fourth, misses a bronze by about 30 seconds. best run by a british man", d4);
    private static final Tweet tweet5 = new Tweet(5, "bbitdiddle", "Go go #TeamLiquid #TI7 #Champion", d4);
    private static final Tweet tweet6 = new Tweet(6, "knightlymuder", "Go go @TeamLiquid #TI7Champ", d4);
    private static final Tweet tweet7 = new Tweet(7, "knightlymuder", "@RealMadrid #LaLiga #C1 #Champ", d4);
    private static final Tweet tweet8 = new Tweet(8, "ashley", "rivest talk #HYPE in 30 minutes #hype", d4);
    private static final Tweet tweet9 = new Tweet(9, "john_know_nothing", "rivest talk #HYPE in 30 minutes #hype #excited", d4);
    private static final Tweet tweet10 = new Tweet(10, "peter", "Go go #teamliquid @TI7 #TeamLiquid", d4);
    private static final Tweet tweet11 = new Tweet(11, "jackson", "Go go #teamliquid @TI7 #TeamLiquid #TI7Champ", d4);
    private static final Tweet tweet12 = new Tweet(12, "dungtrinh", "Can add my FB @ThuyNguyen? I want to be @ThuyNguyen friend", d4);
    private static final Tweet tweet13 = new Tweet(13, "watson", "Can add my FB @ThuyNguyen? I want to be @ThuyNguyen #friend", d4);
    private static final Tweet tweet14 = new Tweet(14, "cyka_power", "Watching #teamliquid on @twitch, please win #TeamLiquid @Twitch", d4);
    private static final Tweet tweet15 = new Tweet(15, "hashley", "Watching @twitch, please win #teamliquid #TI7Champ  @Twitch", d4);
    private static final Tweet tweet16 = new Tweet(16, "fig", "Watching #TeamLiquid @twitch, please #teamliquid #TI7Champ beats that #Newbee @Twitch", d4);
    private static final Tweet tweet17 = new Tweet(17, "henry", "Can you contact @Ashley for me @Ford? He borrows me some money #urgent", d4);
    private static final Tweet tweet18 = new Tweet(18, "johnsnow", "Can you contact @Ashley for me @Henry #Urgent? He borrows me some money #urgent", d4);
    private static final Tweet tweet19 = new Tweet(19, "peterpan", "Can you contact @Ashley for me @John #Urgent? He borrows me some money #friday", d4);
    private static final Tweet tweet20 = new Tweet(20, "mrnobody", "Can you contact @John for me @Peter #Urgent? He borrows me some money #friday #urgent", d4);
    private static final Tweet tweet21 = new Tweet(21, "ashley_cole", "Can add my FB @ThuyNguyen? I want to be your friend @ThuyNguyen. Please add me @Ashley_cole", d4);
    private static final Tweet tweet22 = new Tweet(22, "decarte", "Can you contact @Ashley for me @Henry? @Ashley borrows me some money #urgent", d4);
    private static final Tweet tweet23 = new Tweet(23, "marry", "Can you contact @Ashley for me @Henry #Urgent? @Ashley borrows me some money #urgent", d4);
    private static final Tweet tweet24 = new Tweet(24, "jane97", "Go go @Twitch @TI7 #TeamLiquid #TI7Champ, watching @twitch", d4);
    private static final Tweet tweet25 = new Tweet(25, "nguyen_jason", "Go go @Twitch @TI7 #TeamLiquid #TI7Champ, watching #teamliquid @twitch", d4);
    private static final Tweet tweet26 = new Tweet(26, "watter", "Today is a good day", d4);
    private static final Tweet tweet27 = new Tweet(27, "dungtrungtrinh", "Watching The International 7 @Youtube", d4);
    
    /*
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
	@Test(expected=IndexOutOfBoundsException.class)
    public void testGetTimespanNoTweet() {
    	Timespan timespan = MyExtract.getTimespan(Arrays.asList());
    	timespan.getStart();
    }
    
    @Test
    public void testGetTimespanOneTweet() {
    	Timespan timespan = MyExtract.getTimespan(Arrays.asList(tweet1));
    	
    	assertEquals("expected start", d1, timespan.getStart());
    	assertEquals("expected end", d1, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = MyExtract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    */
    @Test
    public void testGetMentionedUsersNoTweet() {
        Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList());
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersOneTweetNoMentionNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersOneTweetNoMentionOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet2));
        
        assertEquals("expected 1 mentioned hashtag", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionedUsersOneTweetNoMentionOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet8));
        
        assertEquals("expected 1 mentioned hashtag", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionedUsersOneTweetNoMentionMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet5));
        
        assertEquals("expected 3 mentioned hashtags", mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionedUsersOneTweetNoMentionMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet9));
    	
        assertEquals("expected 2 mentioned hashtags", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionedUsersOneTweetOneMentionNoDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet3));
        
        assertEquals("expected 1 mentioned user", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionedUsersOneTweetOneMentionNoDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet6));
        
        assertEquals("expected 1 mentioned user and 1 hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionNoDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet10));
        
        assertEquals("expected 1 mentioned user and 1 duplicate hashtags", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionNoDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet7));
        
        assertEquals("expected 1 mentioned user and 3 hashtags", mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionNoDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet11));
        
        assertEquals("expected 1 mentioned user and 1 distinct hashtag and 1 duplicate hashtags", 
        		mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionHasDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet12));
        
        assertEquals("expected 1 duplicate mentioned user", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionHasDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet13));
        
        assertEquals("expected 1 duplicate mentioned user and 1 hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionHasDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet14));
        
        assertEquals("expected 1 duplicate mentioned user and 1 duplicate hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionHasDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet15));
        
        assertEquals("expected 1 duplicate mentioned user and 2 hashtags", mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetOneMentionHasDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet16));
        
        assertEquals("expected 1 duplicate mentioned user and 1 duplicate hashtag and 2 distinct hashtags", 
        		mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionNoDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet4));
        
        assertEquals("expected 3 mentioned users", mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionNoDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet17));
        
        assertEquals("expected 2 mentioned users and 1 hashtag", mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionNoDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet18));
        
        assertEquals("expected 2 mentioned users and 1 duplicate hashtag", mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionNoDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet19));
        
        assertEquals("expected 2 mentioned users and 2 hashtags", mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionNoDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet20));
        
        assertEquals("expected 2 mentioned users, 1 duplicate hashtag and 1 distinct hashtag",
        		mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionHasDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet21));
        
        assertEquals("expected 1 distinct mentioned user and 1 duplicate mentioned user",
        		mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionHasDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet22));
        
        assertEquals("expected 1 distinct mentioned user, 1 duplicate mentioned user and 1 hashtag",
        		mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionHasDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet23));
        
        assertEquals("expected 1 distinct mentioned user, 1 duplicate mentioned user and 1 duplicate hashtag",
        		mentionedUsers.size(), 3);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionHasDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet24));
        
        assertEquals("expected 1 distinct mentioned user, 1 duplicate mentioned user and 2 hashtags",
        		mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersOneTweetMultipleMentionHasDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet25));
        
        assertEquals("expected 1 distinct mentioned user, 1 duplicate mentioned user,"
        		+ " 1 distinct hashtag and 1 duplicate hashtag",
        		mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsNoMentionNoHashtag() {
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	tweets.add(tweet1);
    	tweets.add(tweet26);
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(tweets);
        
        assertTrue("expected empty", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionNoDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet1, tweet3));
        
        assertEquals("expected 1 mentioned user", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionNoDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet2, tweet3));
    	
        assertEquals("expected 1 mentioned user and 1 hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionNoDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet2, tweet3, tweet8));
    	
        assertEquals("expected 1 mentioned user and 1 duplicate hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionNoDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet2, tweet3, tweet5));
    	
        assertEquals("expected 1 mentioned user and 4 hashtags", mentionedUsers.size(), 5);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionNoDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet2, tweet3, tweet5, tweet8));
    	
        assertEquals("expected 1 mentioned user, 3 distinct hashtags and 1 duplicate hashtag", 
        		mentionedUsers.size(), 5);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionHasDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet1, tweet3, tweet27));
    	
        assertEquals("expected 1 mentioned user", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionHasDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet27));
    	
        assertEquals("expected 1 mentioned user and 1 hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionHasDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet8, tweet27));
    	
        assertEquals("expected 1 mentioned user and 1 duplicate hashtag", mentionedUsers.size(), 2);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsOneMentionHasDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet5, tweet27));
    	
        assertEquals("expected 1 mentioned user and 4 hashtags", mentionedUsers.size(), 5);
    }
    
	@Test
    public void testGetMentionUsersMultipleTweetsOneMentionHasDuplicateMultipleHashtagHasDuplicate() {
		Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet5, tweet8, tweet27));
    	
        assertEquals("expected 1 mentioned user and 3 distinct hashtags and 1 duplicate hashtag", 
        		mentionedUsers.size(), 5);
    }
	
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionNoDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet3, tweet4));
    	
        assertEquals("expected 4 mentioned users", mentionedUsers.size(), 4);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionNoDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4));
    	
        assertEquals("expected 4 mentioned users and 1 hashtag", mentionedUsers.size(), 5);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionNoDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet8));
    	
        assertEquals("expected 4 mentioned users and 1 duplicate hashtag", mentionedUsers.size(), 5);
    }
    
	@Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionNoDuplicateMultipleHashtagNoDuplicate() {
		Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet7));
    	
        assertEquals("expected 5 mentioned users and 4 hashtags", mentionedUsers.size(), 9);
    }
	
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionNoDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet7, tweet9));
    	
        assertEquals("expected 5 mentioned users, 4 distinct hashtags and 1 duplicate hashtag", 
        		mentionedUsers.size(), 10);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionHasDuplicateNoHashtag() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet3, tweet4, tweet21));
    	
        assertEquals("expected 6 mentioned users", 
        		mentionedUsers.size(), 6);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionHasDuplicateOneHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet21));
    	
        assertEquals("expected 6 mentioned users and 1 hashtag", 
        		mentionedUsers.size(), 7);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionHasDuplicateOneHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet8, tweet21));
    	
        assertEquals("expected 6 mentioned users and 1 duplicate hashtag", 
        		mentionedUsers.size(), 7);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionHasDuplicateMultipleHashtagNoDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet21));
    	
        assertEquals("expected 6 mentioned users and 4 hashtags", 
        		mentionedUsers.size(), 10);
    }
    
    @Test
    public void testGetMentionUsersMultipleTweetsMultipleMentionHasDuplicateMultipleHashtagHasDuplicate() {
    	Set<String> mentionedUsers = MyExtract.getMentionedUsers(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet9, tweet21));
    	
        assertEquals("expected 6 mentioned users, 4 hashtags and 1 duplicate hashtag", 
        		mentionedUsers.size(), 11);
    }


    /*
     * Warning: all the tests you write here must be runnable against any
     * MyExtract class that follows the spec. It will be run against several staff
     * implementations of MyExtract, which will be done by overwriting
     * (temporarily) your version of MyExtract with the staff's version.
     * DO NOT strengthen the spec of MyExtract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in MyExtract, because that means you're testing a
     * stronger spec than MyExtract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
