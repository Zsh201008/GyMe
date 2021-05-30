Welcome to GyMe! 
Here are some instructions when you use our online training platform:

-----------------------------------------------------------------Instructions for User-----------------------------------------------------------------
0. Start up
(1) Open the terminal in this directory.
(2) Enter 'javac *.java' in the command line to compile all the .java files in this directory. 
If no error, there will be .class files generated for each .java file.
Note: There may be some warnings, just ignore them.
(3) Enter 'java Login' in the command line to start up the platform. Then you can see the login page.

1. Login
(1) As a user, enter account: u20108, password: 123456, identity choose 'User', then click login button and you can go to the user homepage.
(2) We've created several user demos in User.csv files. You can check and pick any available one to login.
(3) Or you can click register button to create your own account, which is demonstrated in 2. Register.
(4) Note that your password has been encrypted in MD5, which guarantees the security.

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
(1) When you enter your homepage, if you have an on-going live session, the system will send you a message to remind you.
(2) Detailed account information are displayed in the account page, including your name, account type, rank, etc.
(3) You can view your body insight information (i.e. height, weight, BMI, etc) in 'My Body Insight' module.
(4) You can submit your body insight data and your requirements by clicking the corresponding button and then complete a form. 
     You can fill any of the blanks that you want to keep a record or update. For those fields that you have not provided information, the system
     will keep your last record of the corresponding field, or write a 'null' value to the back-end file.
     (Note that your body info and requirements can also be viewed by your trainer)
(5) You can view your favourate videos in 'My Collection' module. You can play the video directly by clicking the video image.
(6) You can view your workout history in 'Workout History' module, containing which video you have watched and when you have done the workout.
(7) You can view some notifications in 'Notification' module, such as a live session message, an account-update notification, tec.
(8) You can click 'Update Account' button to update your account from 'Common' to 'Privilege'. However, if your account is already Privilege, 
     a message will generate for you reminding you that you have no need to upgrade.
(9) Our gym will give you some badges as reward based on the content of your workout. You can click 'View My Badges' to view your badges.
    When you have got a new badge, the system will also send you a notification, which you can see in the 'Notification' module.

4. User Homepage-Workout
(1) Various workout videos are displayed here, including there names and types. Click the video image to start playing.
ATTENTION: 
 -This version only support Windows OS.
 -Make sure you have the system media player located at C:\Program Files\Windows Media Player\wmplayer.exe
 -Otherwise the video cannot be played!
(2) You can also search videos of certain type by entering keywords in the searching textfield, then click 'search' button.
(3) Click the '+' button on the right of the type label to add the corresponding video to your collection.

5. User Homepage-Trainer
(1) You can see your own trainer, with his/her detailed information. (If you do not have a trainer, then nothing will be displayed)
If you have more than one trainer, click 'view next trainer' button to view other trainers.
(2) If you want to delete the current trainer, you can click 'delete this trainer' button to delete him/her. A confirm message will be generated
to confirm your delete operation.
(3) You can click 'select/add trainer' button to select or add a trainer, after which a new window containing all available trainers as well as
their detailed information will be displayed. Click 'select' of the corresponding trainer to select him/her. A confirm message will be generated
once you intend to do your selection. Note that if you are a 'Common-type' user, you will not have the access to select a trainer.
(4) You can also see your chatting messages with your trainer. Enter something you want to say to the trainer in the textfield and click the 'send'
button, then the new message will send to the trainer and be displayed in the chatbox.
(5) You can see your booked live sessions with your trainer. If you want to cancel the live session, click 'cancel' button on the right. After that, a
message will be sent to you to confirm your operation.
(6) If you want to book live session, you can click the button 'book live session' at the bottom. A form will be generated for you to enter the 
exact date, time slot and platform of the live session. Once you submit your booking, you have to wait for your trainer to confirm this booking.
(7) You can see the videos that your trainer recommends to you, including the frequency and period that he/she suggests you to do the workout.
    Note that you can play the video directly by click the video image.

6. User Homepage-Discover
(1) Some activity posters for the gym company's promotion are displayed here. You can click the activity image to see detailed information.

----------------------------------------------------------------Instructions for Trainer----------------------------------------------------------------
0. Start up
(1) Open the terminal in this directory.
(2) Enter 'javac *.java' in the command line to compile all the .java files in this directory. 
If no error, there will be .class files generated for each .java file.
Note: There may be some warnings, just ignore them.
(3) Enter 'java Login' in the command line to start up the platform. Then you can see the login page.

1. Login
(1) As a trainer, enter account: t85277, password: 567890, identity choose 'Trainer', then click login button and you can go to the homepage.
(2) We've created several user demos in Trainer.csv files. You can check and pick any available one to login.
(3) Or you can click register button to create your own account, which is demonstrated in 2. Register.
(4) Note that your password has been encrypted in MD5, which guarantees the security.

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

3. Trainer Homepage-Account
(1) When you enter your homepage, if you have an on-going live session, the system will send you a message to remind you.
(2) Detailed account information are displayed in the account page, including your name, focus, number of courses, etc.
(3) You can view your time schedule in 'My Schedule' module, which includes the detailed information of all you live sessions.
(4) You can view your notifications, including new users that have picked you, live sessions booked by users for you to confirm
     (click the 'confirm' button to confirm the live session, and you can see the live session in your schedule)
(5) You can view your own courses (videos) in 'My Courses' module.

4. Trainer Homepage-Workout
(1) Various workout videos are displayed here, including there names and types. Click the video image to start playing.
ATTENTION: 
 -This version only support Windows OS.
 -Make sure you have the system media player located at C:\Program Files\Windows Media Player\wmplayer.exe
 -Otherwise the video cannot be played!
(2) You can also search videos of certain type by entering keywords in the searching textfield, then click 'search' button.
(3) Click the '^' button on the right of the type label to recommend the corresponding video to certain user. Once you click the button,
     a checkbox will be generated for you to select the user you want to recommend the video to, as well as the frequency&period that you suggest
     him/her to do the workout.

5. Trainer Homepage-User
(1) You can see your own users, with his/her detailed information. (If you do not have a user, then nothing will be displayed)
     If you have more than one user, click 'view next user' button to view other users.
(2) You can see your chatting messages with your user. Enter something you want to say to the user in the textfield and click the 'send'
     button, then the new message will send to the user and be displayed in the chatbox.
(4) You can see your live sessions with your user in 'Live Session' module.
(5) You can see the body insight data (i.e. height, weight, BMI, etc) along with his/her requirements in 'User Body Info' module.

---------------------------------------------------------Instructions for Administrator---------------------------------------------------------
0. Start up
(1) Open the terminal in this directory.
(2) Enter 'javac *.java' in the command line to compile all the .java files in this directory. 
If no error, there will be .class files generated for each .java file.
Note: There may be some warnings, just ignore them.
(3) Enter 'java Login' in the command line to start up the platform. Then you can see the login page.

1. Login
(1) As an administrator, enter account: m60001, password: 123987, identity choose 'Administrator', 
     then click login button and you can go to the user homepage.
(2) Note that your password has been encrypted in MD5, which guarantees the security.

2. Administrator Homepage
You can view the overall statistics summary of the gym, including:
 -total number of users, trainers, videos and live sessions
 -proportion of users of each type
 -proportion of users of each rank
 -Top 5 users with longest workout duration
 -Top 5 trainers with longest training duration
 -Top 5 videos that have been played most
which are references for you to refine the operation of the gym.

Finally, hope you enjoy GyMe!