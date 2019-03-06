# Android News Feed Mobile Application


### Purpose 
To provide users with updated news from https://newsapi.org/ in a readable and organized format. 

### Features 
- **Firebase Job Service** - Syncs the database with the news api.
- **Firebase Job Dispatcher** - Updates in the news every 10 seconds.
- **News API** - Retrieves and displays news information 

### Implementation
News data is fetched from the API located at https://newsapi.org/. Articles are refreshed every 10 seconds or manually by clicking the refresh button. The data is saved into a local repository in order to preserve application integrity. Lost data connection or if the device enters "airplane mode" will have no effect on the current display of news.

### Screen Shots 

![NewsApp!](https://github.com/RYin4/NewsApp/blob/master/app/src/main/res/drawable/newsAppScreenShot1.PNG "News App"
[NewsApp!](https://github.com/RYin4/NewsApp/blob/master/app/src/main/res/drawable/newsAppScreenShot3.PNG "News App")
