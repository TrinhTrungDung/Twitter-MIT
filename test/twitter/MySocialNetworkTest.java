/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MySocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T14:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T16:00:00Z");
    private static final Instant d7 = Instant.parse("2016-02-17T17:00:00Z");
    private static final Instant d8 = Instant.parse("2016-02-17T18:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "trump", "You are so useful @Youtube", d3);
    private static final Tweet tweet4 = new Tweet(4, "trump", "@AllanRWhyte @maagaggie @AdnanOnMUFC hawkins fourth, misses a bronze by about 30 seconds. best run by a british man", d4);
    private static final Tweet tweet5 = new Tweet(5, "trump", "@AllanRWhyte @maagaggie @AdnanOnMUFC hawkins fourth, @AllanRWhyte misses a bronze by about 30 seconds. best run by a british man", d5);
    private static final Tweet tweet6 = new Tweet(6, "alyssa", "i figure out it's not", d6);
    private static final Tweet tweet7 = new Tweet(7, "trump", "You made my day @Youtube", d7);
    private static final Tweet tweet8 = new Tweet(8, "trump", "Today is a good day", d8);
    private static final Tweet tweet9 = new Tweet(9, "alyssa", "Watching TI7 @Youtube", d8);
    private static final Tweet tweet10 = new Tweet(10, "braston97", "Watching TI7 @Youtube", d8);
    private static final Tweet tweet11 = new Tweet(11, "ucantseeme1999", "Chelsea is my favorite team @EPL @Football", d8);
    private static final Tweet tweet12 = new Tweet(12, "knightlymuder", "Go go @Liquid #ChampionTI7 @Twitch @Youtube", d8);
    private static final Tweet tweet13 = new Tweet(13, "johnwick", "Watching TI7 @Twitch @Facebook @Youtube", d8);
    private static final Tweet tweet14 = new Tweet(14, "jason89", "Livestream TI7 at @Facebook", d8);
    private static final Tweet tweet15 = new Tweet(15, "peterpan", "talking rivest in half an hour #hype #running #exercises", d8);
    private static final Tweet tweet16 = new Tweet(16, "alex", "talking rivest in half an hour #hype #running #exercises #hype", d8);
    private static final Tweet tweet17 = new Tweet(17, "jonash", "Livestream TI7 at @Facebook #TeamLiquid", d8);
    private static final Tweet tweet18 = new Tweet(18, "asley", "Livestream TI7 at #TeamLiquid @Facebook #TeamLiquid", d8);
    private static final Tweet tweet19 = new Tweet(19, "peterjackson", "Livestream TI7 at #MonsterEnergy @Facebook #TeamLiquid", d8);
    private static final Tweet tweet20 = new Tweet(20, "asley", "Livestream TI7 at #Champion #TeamLiquid @Facebook #TeamLiquid", d8);
    private static final Tweet tweet21 = new Tweet(21, "weaver", "Can you add my Facebook @Ashley? I din't use Twitter @Ashley", d8);
    private static final Tweet tweet22 = new Tweet(22, "mrnobody", "Can you add my Facebook @Ashley? I din't use Twitter @Ashley #nontwitter", d8);
    private static final Tweet tweet23 = new Tweet(23, "dungtrinh", "Can you add my Facebook @ThuyNguyen #nonTwitter? I din't use Twitter @ThuyNguyen #nontwitter", d8);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
     
    @Test
    public void testGuessFollowsGraphOneTweetNoMentionNoHashtag() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.size(), 1);
    	assertTrue("expected empty graph", followsGraph.get(tweet1.getAuthor()).isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetNoMentionOneHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet2));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned hashtag", followsGraph.get(tweet2.getAuthor()).size(), 1);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetNoMentionMultipleHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet15));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 3 hashtags", followsGraph.get(tweet15.getAuthor()).size(), 3);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetNoMentionMultipleHashtagHasDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet16));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 3 hashtags", followsGraph.get(tweet16.getAuthor()).size(), 3);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionNoDuplicateNoHashtag() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet3));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user", followsGraph.get(tweet3.getAuthor()).size(), 1);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionNoDuplicateOneHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet17));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 1 hashtag", followsGraph.get(tweet17.getAuthor()).size(), 2);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionNoDuplicateOneHashtagHasDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet18));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned users and 1 hashtag", followsGraph.get(tweet18.getAuthor()).size(), 2);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionNoDuplicateMultipleHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet19));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 2 hashtags", followsGraph.get(tweet19.getAuthor()).size(), 3);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionNoDuplicateMultipleHashtagHasDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet20));

    	assertEquals("expected 1 user sent 1 tweet1", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 2 hashtags", followsGraph.get(tweet20.getAuthor()).size(), 3);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionHasDuplicateNoHashtag() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet21));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user", followsGraph.get(tweet21.getAuthor()).size(), 1);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionHasDuplicateOneHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet22));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 1 hashtag", followsGraph.get(tweet22.getAuthor()).size(), 2);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionHasDuplicateOneHashtagHasDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet23));
    	System.out.println(followsGraph.get(tweet23.getAuthor()));
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 1 hashtag", followsGraph.get(tweet23.getAuthor()).size(), 2);
    }
    /*
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionHasDuplicateMultipleHashtagNoDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet22));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 2 hashtag", followsGraph.get(tweet22.getAuthor()).size(), 2);
    }
    
    @Test
    public void testGuessFollowsGraphOneTweetOneMentionHasDuplicateMultipleHashtagHasDuplicate() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet22));
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.keySet().size(), 1);
    	assertEquals("expected 1 mentioned user and 2 hashtag", followsGraph.get(tweet22.getAuthor()).size(), 2);
    }
    /*
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = MySocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersOneTweetNoMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 1 user sent 1 tweet", followsGraph.size(), 1);
    	assertTrue("expected 0 influencers", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersOneTweetNoDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet4));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	
    	assertEquals("expected 3 influencers", influencers.size(), 3);
    }
    
    @Test
    public void testInfluencersOneTweetHasDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet5));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 3 influencers", influencers.size(), 3);
    }
    
    @Test
    public void testInfluencersOnePersonMultipleTweetsNoMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet6));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertTrue("expected 0 influencers", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersOnePersonMultipleTweetsNoDuplicateOneMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet8));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 1 influencers", influencers.size(), 1);
    }
    
    @Test
    public void testInfluencersOnePersonMultipleTweetsHasDuplicateOneMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet7));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 1 influencers", influencers.size(), 1);
    }
    
    @Test
    public void testInfluencersOnePersonMultipleTweetsNoDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet4, tweet8));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 4 influencers", influencers.size(), 4);
    }
    
    @Test
    public void testInfluencersOnePersonMultipleTweetsHasDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(
    			Arrays.asList(tweet3, tweet4, tweet5, tweet7, tweet8));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 4 influencers", influencers.size(), 4);
    }
    
    @Test
    public void testInfluencersMultiplePeopleMultipleTweetsNoMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet6, tweet8));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertTrue("expected 0 influencers", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersMultiplePeopleMultipleTweetsNoDuplicateOneMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet6, tweet8));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 1 influencers", influencers.size(), 1);
    }
    
    @Test
    public void testInfluencersMultiplePeopleMultipleTweetsHasDuplicateOneMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet6, tweet7, tweet8, tweet9));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 1 influencers", influencers.size(), 1);
    }
    
    @Test
    public void testInfluencersMultiplePeopleMultipleTweetsNoDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet6, tweet8, tweet9));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 4 influencers", influencers.size(), 4);
    }
    
    @Test
    public void testInfluencersMultiplePeopleMultipleTweetsHasDuplicateMultipleMention() {
    	Map<String, Set<String>> followsGraph = MySocialNetwork.guessFollowsGraph(
    			Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7,
    					tweet8, tweet9, tweet10, tweet11, tweet12, tweet13, tweet14));
    	List<String> influencers = MySocialNetwork.influencers(followsGraph);
    	
    	assertEquals("expected 9 influencers", influencers.size(), 9);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * MySocialNetwork class that follows the spec. It will be run against several
     * staff implementations of MySocialNetwork, which will be done by overwriting
     * (temporarily) your version of MySocialNetwork with the staff's version.
     * DO NOT strengthen the spec of MySocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in MySocialNetwork, because that means you're testing a
     * stronger spec than MySocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
