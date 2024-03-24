# Making a Contribution

### 1. Create a New Branch

Always create a new branch for your work. Do not work directly on the main branch. Name your branch appropriately based on the feature or fix you are working on.

   ```bash
   git checkout -b feature/your-feature-name
   ```

Or if you're fixing a bug:

   ```bash
   git checkout -b fix/your-fix-name
   ```

### 2. Make Your Changes

Work on your feature or fix on your newly created branch. Remember to keep your changes as focused as possible. If you're adding a new feature, don't fix unrelated bugs in the same branch.

### 3. Commit Your Changes

Once you're satisfied with your changes, it's time to commit them. Make sure your commit messages are clear and descriptive.

   ```bash
   git add .
   git commit -m "A descriptive message explaining your change"
   ```

### 4. Push Your Branch

After committing your changes, push your branch to the GitHub repository.

   ```bash
   git push origin feature/your-feature-name
   ```

### 5. Create a Pull Request

Go to the [MusicalFestivalApp repository](https://github.com/kevinrutledge/MusicalFestivalApp) on GitHub, and you'll see an option to "Compare & pull request" for your branch. Click on it, fill in the details of your contribution, explaining the changes you made, and then submit the pull request.