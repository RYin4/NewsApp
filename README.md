### News Mobile Application For Android Devices 

News data is fetched from the API located at https://newsapi.org/. Articles are refreshed every 10 seconds or manually by clicking the refresh button. The data is saved into a local repository in order to preserve application integrity. Lost data connection or if the device enters "airplane mode" will have no effect on the current display of news.

Features:
Room ORM and the MVVM architecture
FirebaseJobService that uses repository methods to sync the database with the news api
FirebaseJobDispatcher that sends a notification every 10 seconds


