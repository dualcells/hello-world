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
