## Instructions
---------------

1.	Download the JAR file from the Release folder.
2.	Place a "questions.txt" file in the same directory as the JAR file.
3.	Add questions line by line into the TXT file.
	Note: you may optionally put a ~squiggly~ after the question, and give it a weighting.
	
	For Example: `What are you eating?~5`
	
*New Feature*

4.	Now you can put default questions first, like so:

```
First question
Second question
Third question
_
RandomQuestion~2
RandomQuesti0n
RandomQuest1on~5
```
	The First, Second, and Third questions will always show first.
	Afterwards, the random questions will be displayed randomly based on their weightings.