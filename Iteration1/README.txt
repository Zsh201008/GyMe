Welcome to GyMe! 
Here are some instructions when you use our online training platform:

0. Start up
(1) Open the terminal in this directory.
(2) Enter 'javac *.java' in the command line to compile all the .java files in this directory. 
If no error, there will be .class files generated for each .java file.
Note: There may be some warnings, just ignore them.
(3) Enter 'java Login' in the command line to start up the platform. Then you can see the login page.

1. Login
As a user, enter account: u20108, password: 123456, identity choose 'User', then click login button and you can go to the user homepage.
We've created several user demos in User.csv files. You can check and pick any available one to login.
Or you can click register button to create your own account, which is demonstrated in 2. Register.
To be added:
--Trainer login
--Administrator login

2. Register
The information entered into the textfields should follow:
(1) name:
	15 chars + 15 chars, 
	>>* CAPITALED FIRST LETTER
	S p a c e In between 
(2) password:
	<=6 digits no and/or letters(Cap or non Cap)
	checked >>* >0
(3) dob:
	checked >>* Non empty
	year/month/date
	year:1900-2021
	month: 1-12
	date:	1,3,5,7,8,10,12: date 1-31
		4,6,9,11: date 1-30
		2: nonleap year 28, leap year:29
(4) telephone:
	==11 numberic digits
(5) address:
	<=25 chars
	>0

3. User Homepage-Account
Detailed account information are displayed here.
To be added:
--Logout functionality
--User specified 'My Collection' (e.g. favourite videos, activities)
--User questionaire to collect body insight data

4. User Homepage-Workout
Various workout videos are displayed here, including there names and types. Click the video image to start playing.
ATTENTION: 
(1) This version only support Windows OS.
(2) Make sure you have the system media player located at C:\Program Files\Windows Media Player\wmplayer.exe
Otherwise the video cannot be played!
To be added:
--Record the videos watched by certain user
--Add 'My Collection' functionality (related to 3. User Homepage-Account)
--Improve video quality

5. User Homepage-Trainer
You can see a list of trainers with their detailed information on the left of the page. Click button 'select' to choose him/her as your trainer.
Note: There will be instructions guiding you.
On the right of the page, you can see your own trainer, with his/her detailed information, your online-session with him/her, and your chatting messages.
To be added:
--A formal live-session booking module
--More chatting functionalities

6. User Homepage-Discover
Some activity posters for the gym company's promotion are displayed here.
To be added:
--View detail information of certain activity
--More promotion approaches

Finally, hope you enjoy GyMe!