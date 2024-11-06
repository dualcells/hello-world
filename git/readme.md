# Git

## Commands to remember

### Basic

``` sh
git config --global user.name "Your git username"
git config --global user.email "Your git email"
git config --global --list
```

### SSH

``` sh
git config --global gpg.format ssh
git config --global user.signingkey ~/path/to/key.pub
```

### URL (Examples)

``` bash
$ git remote -v
```

#### URL (git)

``` text
origin	git@github.com:dualcells/hello-world.git (fetch)
origin	git@github.com:dualcells/hello-world.git (push)
```