# MIT 6.005 Problem Set 1: Tweet Tweet
<p>This is my source code for solving problem set 1 in the course 6.005 at OpenCourseWare.</p>
<p>The project is about a demo of extracting piece of data from Twitter API such as author, id, tweet and timestamp and handle it in a very interesting way. The main purpose of this project is to implement bunch of methods and to practice testing using unit test in Java.</p>
<p>Feel free to contact me at my personal email: <em><a href="mailto:dungtrungtrinh@gmail.com" target="_top">Trung Dũng Trịnh</a></em></p>
<h2>Resource:</h2>
<p>You can find this course at the website:</p>
<ul>
	<li><a href="https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-005-software-construction-spring-2016/">MIT OpenCourseWare</a></li>
</ul>
<p>This project contains twitter package which has 2 main parts (source code and unit test files):</p>
<ul>
    <li>Source code:<ol>
        <li>Extract consists of methods that extract data from a list of tweets.</li>
        <li>Filter for filtering a list of tweets for particularly given conditions.</li>
        <li>Main file for testing the given URL of the course.</li>
        <li>SocialNetwork provides methods for operating a social network such as building data structures for it.</li>
        <li>Timespan is an immutable datatype representing an interval time.</li>
        <li>Tweet is an immutable datatype for a tweet from Twitter.</li>
        <li>TweetReader reads tweets from files or from web server, it uses fewer fields than Twitter API.</li>
    </ol></li>
    <li>Testing code:<ol>
        <li>ExtractTest contains 11 test cases for testing Extract file</li>
        <li>FilterTest - ID</li>
        <li>SocialNetworkTest consists of 26 test cases for testing SocialNetwork file</li>
        <li>MySocialNetworkTest - ID</li>
    </ol></li>
</ul>
<h3>License:</h3>
<p>This course is under <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Common License</a></p>
