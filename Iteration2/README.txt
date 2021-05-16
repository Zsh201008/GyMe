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
You can view your favourate videos or activities in 'My Collection' module.
You can submit your body insight data by clicking the corresponding button and then complete a form. Note that your body info will also be
submitted to your trainer.

4. User Homepage-Workout
Various workout videos are displayed here, including there names and types. Click the video image to start playing.
ATTENTION: 
(1) This version only support Windows OS.
(2) Make sure you have the system media player located at C:\Program Files\Windows Media Player\wmplayer.exe
Otherwise the video cannot be played!
You can also search videos of certain type by entering keywords in the searching textfield.
To be added:
--Record the videos watched by certain user (i.e. the duration of the video)
--Add 'My Collection' functionality

5. User Homepage-Trainer
You can see your own trainer, with his/her detailed information. (If you do not have a trainer, then nothing will be displayed)
You can click 'select/add trainer' button to select or add a trainer, after which a new window containing all available trainers as well as
their detailed information will be displayed. Click 'select' of the corresponding trainer to select him/her. A confirm message will be generated
once you intend to do your selection. Note that if you are a 'Common-type' user, you will not have the access to select a trainer.
You can also see your chatting messages with your trainer, your booked live sessions, as well as the videos that your trainer has recommended 
to you.
To be added:
--Display a list of trainers instead of only one trainer

6. User Homepage-Discover
Some activity posters for the gym company's promotion are displayed here.
To be added:
--View detailed information of certain activity
--More promotion approaches

Finally, hope you enjoy GyMe!