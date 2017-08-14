/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class MySocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	Map<String, Set<String>> followsGraph = new HashMap<>();
    	Map<String, Set<String>> hashtags = new HashMap<>();
    	
    	// Iterate through list of tweets, if any username has the same hashtag
    	// then they are mutually followed each other
    	for (Tweet tweet : tweets) {
        	String username = tweet.getAuthor();
        	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet));
        	for (String mentionedName : mentionedUsers) {
        		if (mentionedName.startsWith("#")) {
        			if (hashtags.containsKey(mentionedName)) {
        				hashtags.get(mentionedName).add(username);
        			}
        			else {
        				hashtags.put(mentionedName, new HashSet<String>(Arrays.asList(username)));
        			}
        		}
        	}
        }
    	
    	
    	// Iterate through list of tweets, if any username follows someone
    	// then store mentioned names into a set
    	// Username = (Any mentioned name,...) (No duplicate)
        for (Tweet tweet : tweets) {
        	String username = tweet.getAuthor();
        	Set<String> mentionedUsers = MyExtract.getMentionedUsers(Arrays.asList(tweet));
        	if (followsGraph.containsKey(username)) {
        		followsGraph.get(username).addAll(mentionedUsers);
        	}
        	else {
        		followsGraph.put(username, mentionedUsers);
        	}
        }
        
        // Iterate through hashtag, add all username except the author name
        // whose hashtag is the same into followsGraph
        for (String hashtag : hashtags.keySet()) {
        	for (String username : hashtags.get(hashtag)) {
        		Set<String> mutualInfluencers = hashtags.get(hashtag);
        		mutualInfluencers.remove(username);
        		followsGraph.get(username).addAll(mutualInfluencers);
        	}
        }
        
        return followsGraph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        List<String> mostFamousUsers = new ArrayList<String>();
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        
        for (String username : followsGraph.keySet()) {
        	for (String mentionedUser : followsGraph.get(username)) {
        		if (!count.containsKey(mentionedUser)) {
        			count.put(mentionedUser, 1);
        		}
        		else {
        			count.put(mentionedUser, count.get(mentionedUser) + 1);
        		}
        	}
        }
        
        HashMap<String, Integer> sortedCount = count.entrySet().stream().sorted(Entry.comparingByValue(Collections.reverseOrder()))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        for (String famousUser : sortedCount.keySet()) {
        	mostFamousUsers.add(famousUser);
        }
        
        return mostFamousUsers;
    }

}
