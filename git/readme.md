# Git

## Commands to remember

### Basic

``` shell
git config --global user.name "Your git username"
git config --global user.email "Your git email"
git config --global --list
```

### SSH

``` shell
git config --global gpg.format ssh
git config --global user.signingkey ~/path/to/key.pub
```

#### SSH test

``` shell
ssh -T git@github.com
```

Expected Output

``` text
Hi dualcells! You've successfully authenticated, but GitHub does not provide shell access.
```

### URL (Examples)

``` shell
git remote -v
```

#### URL (git)

``` shell
origin	git@github.com:dualcells/hello-world.git (fetch)
origin	git@github.com:dualcells/hello-world.git (push)
```

#### Creating a GitHub Release with Version Tag

> In simple terms, please describe how I should create a version tag in the web gui and from a terminal.
> Copilot repled:

``` shell
Web GUI:

  - Go to your repository → Click "Releases" (right sidebar)
  - Click "Create a new release" or "Draft a new release"
  - Click "Choose a tag" → Type your version (e.g., v1.0.0) → Click "Create new tag"
  - Fill in release title and description
  - Click "Publish release"

Terminal (Git):

  # Create and push a tag
  git tag v1.0.0
  git push origin v1.0.0

  # Then go to GitHub web GUI to create the release from that tag
  # OR use GitHub CLI:
  gh release create v1.0.0 --title "Version 1.0.0" --notes "Release notes here"
```

``` text
The key difference: Tags are just Git markers at a specific commit, while 
Releases are GitHub's feature that bundles the tag with downloadable archives
and release notes. You can create a tag first via terminal, then turn it into a
release via the web GUI.
```
