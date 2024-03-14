# Welcome to Git and GitHub!

This README aims to guide you through the basics of setting up and using GitHub for our project. GitHub is a platform for version control and collaboration. It lets you and your team work together on projects from anywhere.

## First Steps on GitHub

Before you can start using GitHub, you need an account.

### Sign Up for GitHub

1. Go to [GitHub](https://github.com/).
2. Click on **Sign up** in the top right corner.
3. Follow the instructions to create your account.

### Set Up Git on Your Local Machine

Git is the version control system that GitHub is built upon. Here's how you can set it up:

1. Download and install Git from [git-scm.com](https://git-scm.com/downloads).
2
2. Open your terminal (Command Prompt or PowerShell on Windows, Terminal on macOS or Linux).
3. Configure Git with your username and email address:

   ```bash
   git config --global user.name "Your Name"
   git config --global user.email "your_email@example.com"
   ```
   
4. Confirm that Git is configured by checking the version:

    ```
    git --version
    ```

### Cloning Our Project Repository

To start working on the project, you need to have a copy of the repository on your local machine. This is called "cloning."

1. On GitHub, navigate to the main page of the repository.
2. Above the file list, click on Code.
3. To clone the repository using HTTPS, under "Clone with HTTPS", click the clipboard icon. To clone the repository using an SSH key, including a certificate issued by your organization's SSH certificate authority, click Use SSH, then click the clipboard icon.
4. Open your terminal.
5. Change the current working directory to the location where you want the cloned directory.
6. Type git clone, and then paste the URL you copied earlier:

    ```
    git clone https://github.com/kevinrutledge/MusicalFestivalApp.git
    ```
    
7. Press **Enter** to create your local clone.

### Making Changes and Committing

When you make changes to a file in your repository, you'll want to save those changes. This process is called "committing."

1. Open your terminal.
2. Change the directory to your repository.
3. Use git status to see which files have changed.
4. Use git add to stage the files you want to commit.
5. Commit the files with a message:

    ```
    git commit -m "A brief description of the changes"
    ```

### Pushing Changes to GitHub

To make your local commits available to the team on GitHub, you need to "push" the changes.

1. In your terminal, make sure you are in the repository directory.
2. Type git push origin main (or git push origin master depending on your default branch name).
3. Enter your GitHub credentials if prompted.

### Pulling the Latest Changes

To get the latest changes that your teammates have made, you will need to "pull" the changes from GitHub.

1. In your terminal, navigate to the repository directory.
2. Type git pull origin main to pull the latest changes.
