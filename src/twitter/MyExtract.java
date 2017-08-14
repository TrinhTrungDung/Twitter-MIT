/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class MyExtract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	
        // Get timestamp of the first tweet in the list
        Tweet tweetFirst = tweets.get(0);
        Instant timestampFirst = tweetFirst.getTimestamp();
        
        // Get timestamp of the last tweet in the list
        Tweet tweetLast = tweets.get(tweets.size() - 1);
        Instant timestampLast = tweetLast.getTimestamp();
        
        Timespan timespan;
        
        // Compare which tweet has the latest time
        if (timestampLast.isAfter(timestampFirst)) {
           	timespan = new Timespan(timestampFirst, timestampLast);
        }
        else {
          	timespan = new Timespan(timestampLast, timestampFirst);
        }
        
        return timespan;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<String>();
        
        // Regular expression for parsing String has pattern of " @anycharhere" or "@anychar" at the beginning of text
        // similarly replacing @ with # to get same pattern
        Pattern p = Pattern.compile("^[@|#][A-Za-z0-9]*|[ ]+[@|#][A-Za-z0-9]*", Pattern.CASE_INSENSITIVE);
        
        // Iterate through all the tweets list to add any string has above pattern
        for (Tweet tweet : tweets) {
        	Matcher m = p.matcher(tweet.getText());
        	while (m.find()) {
        		mentionedUsers.add(m.group().toLowerCase());
        	}
        }
        
        Set<String> parsedMentionedUsers = new HashSet<String>();
        
        // Regular expression for parsing String has pattern of "mentionedUsers" in case-insensitive
        Pattern p2 = Pattern.compile("#?[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);
        
        // Iterate through all mentionedName in mentionedUsers set to add any string has p2 pattern
        for (String mentionedName : mentionedUsers) {
        	Matcher m = p2.matcher(mentionedName);
        	if (m.find()) {
        		parsedMentionedUsers.add(m.group());
        	}
        }
        
        return parsedMentionedUsers;
    }
}
